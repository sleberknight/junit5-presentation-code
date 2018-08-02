package com.fortitudetec.junit.jupiter.extensions;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Copied from JUnit 5 test source code; added logging. Not sure why they didn't include
 * this as an out-of-box extension, as it seems pretty useful to me given that I used the
 * TemporaryFolder rule often.
 */
@Slf4j
public class TempDirectory implements AfterEachCallback, ParameterResolver {

    @Target(ElementType.PARAMETER)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface Root {
    }

    private static final String KEY = "tempDirectory";

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.isAnnotated(Root.class) && parameterContext.getParameter().getType() == Path.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext context) {
        return getLocalStore(context).getOrComputeIfAbsent(KEY, key -> createTempDirectory(context));
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Path tempDirectory = (Path) getLocalStore(context).get(KEY);
        if (tempDirectory != null) {
            delete(tempDirectory);
        }
    }

    private ExtensionContext.Store getLocalStore(ExtensionContext context) {
        return context.getStore(localNamespace(context));
    }

    private Namespace localNamespace(ExtensionContext context) {
        return Namespace.create(TempDirectory.class, context);
    }

    private Path createTempDirectory(ExtensionContext context) {
        try {
            String tempDirName;
            if (context.getTestMethod().isPresent()) {
                tempDirName = context.getTestMethod().get().getName();
            } else if (context.getTestClass().isPresent()) {
                tempDirName = context.getTestClass().get().getName();
            } else {
                tempDirName = context.getDisplayName();
            }

            return Files.createTempDirectory(tempDirName);
        } catch (IOException e) {
            throw new ParameterResolutionException("Could not create temp directory", e);
        }
    }

    private void delete(Path tempDirectory) throws IOException {
        Files.walkFileTree(tempDirectory, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return deleteAndContinue(file);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return deleteAndContinue(dir);
            }

            private FileVisitResult deleteAndContinue(Path path) throws IOException {
                Files.delete(path);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
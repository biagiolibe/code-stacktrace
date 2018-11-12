package com.biagiolibe.dev.codestacktrace.compiler;

import com.biagiolibe.dev.codestacktrace.compiler.model.JavaClassOutput;

import javax.tools.*;
import java.io.IOException;

public class MemoryClassManager extends ForwardingJavaFileManager<JavaFileManager> {

    private JavaClassOutput classOutput;

    MemoryClassManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        return classOutput = new JavaClassOutput(className);
    }

    public JavaClassOutput getClassOutput() {
        return classOutput;
    }

    public void setClassOutput(JavaClassOutput classOutput) {
        this.classOutput = classOutput;
    }
}

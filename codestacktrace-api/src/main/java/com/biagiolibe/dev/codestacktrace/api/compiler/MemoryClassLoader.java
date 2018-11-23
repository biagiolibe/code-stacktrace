package com.biagiolibe.dev.codestacktrace.api.compiler;

import com.biagiolibe.dev.codestacktrace.api.compiler.model.JavaClassOutput;

import java.util.HashMap;
import java.util.Map;

public class MemoryClassLoader extends ClassLoader {

    private Map<String, JavaClassOutput> compiledCode = new HashMap<>();

    public MemoryClassLoader(ClassLoader parent) {
        super(parent);
    }

    public void addCode(JavaClassOutput classOutput) {
        compiledCode.put(classOutput.getName(), classOutput);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        JavaClassOutput classOutput = compiledCode.get(name);
        if (classOutput == null) {
            return super.findClass(name);
        }
        byte[] byteCode = classOutput.getBytes();
        return defineClass(name, byteCode, 0, byteCode.length);
    }
}
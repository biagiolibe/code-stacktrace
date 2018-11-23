package com.biagiolibe.dev.codestacktrace.api.compiler.model;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class JavaClassOutput extends SimpleJavaFileObject {

    private ByteArrayOutputStream os = new ByteArrayOutputStream();
    private String name;

    public JavaClassOutput(String className) {
        super(URI.create(className), Kind.CLASS);
        this.name = className;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public byte[] getBytes() {
        return os.toByteArray();
    }

    @Override
    public OutputStream openOutputStream() {
        return os;
    }
}

package com.biagiolibe.dev.codestacktrace.compiler.model;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;
import java.net.URISyntaxException;

public class JavaSourceCode extends SimpleJavaFileObject {
    private String code;
    /**
     * Construct a JavaSourceCode of the given kind and with the
     * given URI.
     */
    public JavaSourceCode(String name, String code) throws URISyntaxException {
        super(buildURI(name), Kind.SOURCE);
        this.code = code;
    }

    private static URI buildURI(String name) throws URISyntaxException {
        final String path = '/' + name.replace('.', '/') + Kind.SOURCE.extension;
        return new URI("string", null, path, null);
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors){
        return code;
    }
}

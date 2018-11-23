package com.biagiolibe.dev.codestacktrace.api.compiler.model;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.lang.reflect.Method;

public class CompilationResponse {
    private Method[] compiledClasses;
    private DiagnosticCollector<JavaFileObject> diagnostics;

    public CompilationResponse() {}

    public CompilationResponse(Method[] compiledClasses, DiagnosticCollector<JavaFileObject> diagnostics) {
        this.compiledClasses = compiledClasses;
        this.diagnostics = diagnostics;
    }

    public Method[] getCompiledClasses() {
        return compiledClasses;
    }

    public void setCompiledClasses(Method[] compiledClasses) {
        this.compiledClasses = compiledClasses;
    }

    public DiagnosticCollector<JavaFileObject> getDiagnostics() {
        return diagnostics;
    }

    public void setDiagnostics(DiagnosticCollector<JavaFileObject> diagnostics) {
        this.diagnostics = diagnostics;
    }
}

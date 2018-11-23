package com.biagiolibe.dev.codestacktrace.api.compiler;

import com.biagiolibe.dev.codestacktrace.api.compiler.model.CompilationResponse;
import com.biagiolibe.dev.codestacktrace.api.compiler.model.JavaSourceCode;

import javax.tools.*;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MemoryCompiler {

    private static final String MAIN_CLASS_NAME = "CodeStackTraceMain";

    public static CompilationResponse compile(String source) {
        try {
            CompilationResponse compilationResponse = new CompilationResponse();
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

            // to collect errors, warnings etc.
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            MemoryClassManager memoryClassManager = new MemoryClassManager(fileManager);

            // prepare the compilation unit
            List<JavaFileObject> compUnits = new ArrayList<>(1);
            compUnits.add(new JavaSourceCode(MAIN_CLASS_NAME, source));
//            System.out.println(compUnits.get(0).getCharContent(false));
            JavaCompiler.CompilationTask task = compiler.getTask(new PrintWriter(System.out), memoryClassManager, diagnostics, null, null, compUnits);
            if (!task.call()) {
                compilationResponse.setDiagnostics(diagnostics);
                return compilationResponse;
            }

            MemoryClassLoader memoryClassLoader = new MemoryClassLoader(ClassLoader.getSystemClassLoader());
            memoryClassLoader.addCode(memoryClassManager.getClassOutput());
            Class clazz = memoryClassLoader.loadClass(memoryClassManager.getClassOutput().getName());

            final Method[] methods = clazz.getDeclaredMethods();
            compilationResponse.setCompiledClasses(methods);
            return compilationResponse;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}

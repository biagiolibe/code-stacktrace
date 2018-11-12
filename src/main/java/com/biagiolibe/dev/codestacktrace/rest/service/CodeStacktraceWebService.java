package com.biagiolibe.dev.codestacktrace.rest.service;

import com.biagiolibe.dev.codestacktrace.compiler.MemoryCompiler;
import com.biagiolibe.dev.codestacktrace.compiler.model.CompilationResponse;
import com.biagiolibe.dev.codestacktrace.printer.ConsoleOutputCapturer;
import com.biagiolibe.dev.codestacktrace.rest.model.SourceCodeObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestController
public class CodeStacktraceWebService {
    @RequestMapping("/")
    public String index() {
        return "This is the very first time!";
    }

    @PostMapping(path="/compile-source", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String compile(@RequestBody SourceCodeObject sourceCode) {
        String code = sourceCode.getCode();
        ConsoleOutputCapturer coc = new ConsoleOutputCapturer();
        coc.start();
        CompilationResponse compilationResponse = MemoryCompiler.compile(code);



        try {
            if(compilationResponse!=null){

                if(compilationResponse.getDiagnostics()==null) {
                    for (Method method : compilationResponse.getCompiledClasses()) {
                        method.setAccessible(true);
                        method.invoke(null);
                        //TODO optimize consoleOutputCapturer in order to capture data from all printer
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return coc.stop();
    }

    // final String source = "public final class "+MAIN_CLASS_NAME+" {\n"
    //                    + "public static void executeMe() {\n"
    //                    + "System.out.println(\"Hello World\";}\n}\n";
}

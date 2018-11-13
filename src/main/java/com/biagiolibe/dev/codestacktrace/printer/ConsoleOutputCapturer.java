package com.biagiolibe.dev.codestacktrace.printer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class ConsoleOutputCapturer {
    private ByteArrayOutputStream baos;
    private PrintStream previousOut;
    private PrintStream previousErr;
    private boolean capturing;

    public void start() {
        if (capturing) {
            return;
        }

        capturing = true;
        previousOut = System.out;
        previousErr = System.err;
        baos = new ByteArrayOutputStream();

        OutputStream outputStreamCombiner = new OutputStreamCombiner(Arrays.asList(previousOut, baos));
        OutputStream errorStreamCombiner = new OutputStreamCombiner(Arrays.asList(previousErr, baos));
        PrintStream customOut = new PrintStream(outputStreamCombiner);
        PrintStream customErr = new PrintStream(errorStreamCombiner);

        System.setOut(customOut);
        System.setErr(customErr);
    }

    public String stop() {
        if (!capturing) {
            return "";
        }

        System.setOut(previousOut);
        System.setErr(previousErr);

        String capturedValue = baos.toString();             

        baos = null;
        previousOut = null;
        previousErr = null;
        capturing = false;

        return capturedValue;
    }

    private static class OutputStreamCombiner extends OutputStream {
        private List<OutputStream> outputStreams;

        OutputStreamCombiner(List<OutputStream> outputStreams) {
            this.outputStreams = outputStreams;
        }

        public void write(int b) throws IOException {
            for (OutputStream os : outputStreams) {
                os.write(b);
            }
        }

        public void flush() throws IOException {
            for (OutputStream os : outputStreams) {
                os.flush();
            }
        }

        public void close() throws IOException {
            for (OutputStream os : outputStreams) {
                os.close();
            }
        }
    }
}
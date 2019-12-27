package com.alevel.module2.file;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

@Slf4j
public class BitWriter implements AutoCloseable {

    private OutputStream output;
    private int currentByte;
    private int numBitsFilled;

    public BitWriter(OutputStream out) {
        output = Objects.requireNonNull(out);
        currentByte = 0;
        numBitsFilled = 0;
    }

    public void write(int b) throws IOException {
        if (b != 0 && b != 1)
            throw new IllegalArgumentException("Argument must be 0 or 1");

        currentByte = (currentByte << 1) | b;
        numBitsFilled++;
        if (numBitsFilled == 8) {
            output.write(currentByte);
            currentByte = 0;
            numBitsFilled = 0;
        }
    }

    public void close() throws IOException {
        while (numBitsFilled != 0)
            write(0);
        output.close();
        log.debug("Closing Bit Writer Stream success.");
    }

}

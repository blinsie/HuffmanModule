package com.alevel.module2.file;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class BitReader implements AutoCloseable {

    private InputStream input;
    private int currentByte;
    private int remainingBits;

    public BitReader(InputStream in) {
        input = Objects.requireNonNull(in);
        currentByte = 0;
        remainingBits = 0;
    }

    public int read() throws IOException {
        if (currentByte == -1)
            return -1;
        if (remainingBits == 0) {
            currentByte = input.read();
            if (currentByte == -1)
                return -1;
            remainingBits = 8;                                  //refreshing of new Byte
        }
        if (remainingBits <= 0)
            throw new AssertionError();

        remainingBits--;

        return (currentByte >>> remainingBits) & 1;
    }

    public int readNoEof() throws IOException {
        int result = read();
        if (result != -1)
            return result;
        else
            throw new EOFException();
    }

    public void close() throws IOException {
        input.close();
        currentByte = -1;
        remainingBits = 0;
    }

}

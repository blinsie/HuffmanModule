package com.alevel.module2.file;

import com.sun.org.apache.bcel.internal.generic.ISTORE;

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
        if (remainingBits == 0) {
            currentByte = input.read();
            remainingBits = 8;                                  //refreshing of new Byte
        }
        if (remainingBits <= 0)
            throw new IllegalStateException("read() ==> readed value is bound of byte-size");

        remainingBits--;
        //??????????????????????
        return currentByte >>> remainingBits;
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

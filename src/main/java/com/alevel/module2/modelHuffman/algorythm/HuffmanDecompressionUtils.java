package com.alevel.module2.modelHuffman.algorythm;

import com.alevel.module2.file.BitReader;
import com.alevel.module2.modelTree.PrefixTree;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

@Slf4j
public class HuffmanDecompressionUtils {

    public PrefixTree readCodeLengthTable(BitReader in) throws IOException {
        int[] codeLengths = new int[257];
        for (int i = 0; i < codeLengths.length; i++) {
            int readBit = 0;
            for (int j = 0; j < 8; j++)
                readBit = (readBit << 1) | in.readNoEof();
            codeLengths[i] = readBit;
        }
        log.debug("readCodeLengthTable() ==> returns PrefixTree with code length " + Arrays.toString(codeLengths));
        return new PrefixTree(codeLengths);
    }

    public void decompress(PrefixTree code, BitReader in, OutputStream out) throws IOException {
        HuffmanDecoder dec = new HuffmanDecoder(in);
        dec.setPrefixTree(code);
        while (true) {
            int symbol = dec.read();
            if (symbol == 256)              // EOF symbol
                break;
            out.write(symbol);
        }
        log.debug("Finish decompress() method");
    }

}

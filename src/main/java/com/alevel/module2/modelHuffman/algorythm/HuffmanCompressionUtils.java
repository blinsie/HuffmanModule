package com.alevel.module2.modelHuffman.algorythm;

import com.alevel.module2.file.BitWriter;
import com.alevel.module2.modelTree.PrefixTree;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class HuffmanCompressionUtils {

    public FrequencyTable getFrequencies(File file) throws IOException {
        FrequencyTable frequencies = new FrequencyTable(new int[257]);
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            while (true) {
                int b = input.read();
                if (b == -1)
                    break;
                frequencies.increaseFrequency(b);
            }
        }
        log.debug("getFrequencies() ==> Getting frequencies table");
        return frequencies;
    }

    public void writeCodeLengthTable(BitWriter out, PrefixTree prefixTree) throws IOException {
        for (int i = 0; i < prefixTree.getBinaryCodeSize(); i++) {
            int tmp = prefixTree.getBinaryCodeLength(i);

            if (tmp >= 256)
                throw new RuntimeException("The code for a symbol is too long");

            // Write as BYTE
            for (int j = 7; j >= 0; j--) {
                out.write((tmp >>> j) & 1);
            }
        }
    }

    public void compress(PrefixTree code, InputStream in, BitWriter out) throws IOException {
        HuffmanEncoder encoder = new HuffmanEncoder(out);
        encoder.setPrefixTree(code);
        log.debug("\n------------------------------------COMPRESSING RESULT-------------------------------------");
        while (true) {
            int b = in.read();
            if (b == -1)
                break;
            encoder.write(b);
        }
        encoder.write(256);  // EOF
    }
}

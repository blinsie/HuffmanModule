package com.alevel.module2.modelHuffman;

import com.alevel.module2.file.BitWriter;
import com.alevel.module2.modelTree.PrefixTree;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class HuffmanEncoder {

    private PrefixTree prefixTree;
    private BitWriter bitWriter;

    public HuffmanEncoder(BitWriter bos) {
        this.bitWriter = Objects.requireNonNull(bos);
    }

    public void write(int symbol) throws IOException {
        if (prefixTree == null) {
            log.error("write() ==> There is nullable Prefix Tree");
            throw new NullPointerException("There is no Prefix Tree");
        }
        List<Integer> bits = prefixTree.getBinaryCodeOf(symbol);
        for (int b : bits)
            bitWriter.write(b);
    }

    public void setPrefixTree(PrefixTree prefixTree) {
        this.prefixTree = prefixTree;
    }
}

package com.alevel.module2.modelHuffman.algorythm;

import com.alevel.module2.file.BitReader;
import com.alevel.module2.modelTree.Node;
import com.alevel.module2.modelTree.PrefixTree;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class HuffmanDecoder {

    private BitReader bitReader;
    private PrefixTree prefixTree;

    public HuffmanDecoder(BitReader in) {
        this.bitReader = Objects.requireNonNull(in);
    }

    public void setPrefixTree(PrefixTree prefixTree) {
        this.prefixTree = prefixTree;
    }

    public int read() throws IOException {
        if (prefixTree == null) {
            log.error("read() ==> Prefix tree is nullable");
            throw new NullPointerException("Prefix tree is nullable");
        }

        Node currentNode = prefixTree.getRoot();

        //todo: check if the leaf

        while (true) {
            int readValue = bitReader.readNoEof();
            Node nextNode;

            if (readValue == 0)
                nextNode = currentNode.leftChild;
            else if (readValue == 1)
                nextNode = currentNode.rightChild;
            else {
                log.error("read() ==> Invalid value from readNoEof() --> " + readValue);
                throw new IllegalStateException("Invalid value from readNoEof() --> " + readValue);
            }
            if (nextNode.isLeaf())
                return nextNode.getLetter();
            else
                currentNode = nextNode;
        }
    }

}

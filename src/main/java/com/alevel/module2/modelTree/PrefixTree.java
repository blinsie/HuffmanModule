package com.alevel.module2.modelTree;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Getter
public class PrefixTree {

    private Node root;
    private List<List<Integer>> binaryCodes;

    public PrefixTree(Node root, int frequenciesLength) {
        this.root = Objects.requireNonNull(root);
        this.binaryCodes = new ArrayList<>();

        for (int i = 0; i < frequenciesLength; i++)
            binaryCodes.add(null);
        buildBinaryCodeList(root, new ArrayList<>());
    }

    public PrefixTree(int[] codeLengths) {
        if (codeLengths == null) {
            log.error("PrefixTree() ==> There is nullable code lengths for building prefix tree");
            throw new NullPointerException();
        }
        if (codeLengths.length < 2) {
            log.error("PrefixTree() ==> Incorrect number of codes. Required at least 2.");
            throw new IllegalArgumentException("Incorrect number of codes. Required at least 2.");
        }

//        for (int i = 0; i < codeLengths.length; i++) {
//            System.out.print(codeLengths[i] + " - ");
//        }

    }

    private void buildBinaryCodeList(Node node, List<Integer> prefix) {
        if (!node.isLeaf()) {
            prefix.add(0);
            buildBinaryCodeList(node.leftChild, prefix);
            prefix.remove(prefix.size() - 1);

            prefix.add(1);
            buildBinaryCodeList(node.rightChild, prefix);
            prefix.remove(prefix.size() - 1);

        } else {
            if (binaryCodes.get(node.getLetter()) != null) {
                log.error("buildBinaryCodeList() ==> Symbol has more than one code ");
                throw new IllegalArgumentException("Symbol has more than one code: "
                        + node.getLetter() + " has code" + binaryCodes.get(node.getLetter()));
            }
            binaryCodes.set(node.getLetter(), new ArrayList<>(prefix));
            log.debug("PREFIX of " + node.getLetter() + ": " + binaryCodes.get(node.getLetter()).toString());
        }
    }

    public int getBinaryCodeLength(int symbol) {
        if (symbol < 0 && symbol >= binaryCodes.size()) {
            log.error("getBinaryCodeLength() ==> Index {} is bound of array", symbol);
            throw new IllegalArgumentException("Index " + symbol + " is bound of array");
        }
        if (binaryCodes.get(symbol) != null) {
            return binaryCodes.get(symbol).size();
        } else
            return 0;
    }

    public List<Integer> getBinaryCodeOf(int symbol) {
        if (symbol < 0 && symbol >= binaryCodes.size()) {
            log.error("getBinaryCodeOf() ==> Unreachable index of code by symbol {}", symbol);
            throw new IllegalArgumentException("Unreachable index of code by symbol " + symbol);
        }
        if (binaryCodes.get(symbol) == null) {
            log.error("getBinaryCodeOf() ==> There is no code for symbol {}", symbol);
            throw new IllegalArgumentException("There is no code for symbol " + symbol);
        }
        return binaryCodes.get(symbol);
    }

    public int getBinaryCodeSize() {
        return binaryCodes.size();
    }

}

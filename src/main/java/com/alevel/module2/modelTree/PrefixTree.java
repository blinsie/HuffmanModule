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
}

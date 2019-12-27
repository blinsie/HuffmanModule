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

}

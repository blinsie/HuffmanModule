package com.alevel.module2.modelTree;

import lombok.Getter;

/**
 * NodeComparable needs for PriorityQueue
 */

@Getter
public class NodeComparable extends Node implements Comparable<NodeComparable> {

    private int codeSymbol;

    public NodeComparable(char letter, int frequency, Node leftChild, Node rightChild, int codeSymbol) {
        super(letter, frequency, leftChild, rightChild);
        this.codeSymbol = codeSymbol;
    }

    @Override
    public int compareTo(NodeComparable second) {
        if (codeSymbol > second.codeSymbol) {
            return 1;
        } else if (getFrequency() > second.getFrequency()) {
            return 1;
        } else if (codeSymbol < second.codeSymbol) {
            return -1;
        } else if (getFrequency() < second.getFrequency()) {
            return -1;
        } else
            return 0;
    }
}

package com.alevel.module2.modelTree;

import lombok.Getter;

@Getter
public class Node {

    public final Node leftChild;
    public final Node rightChild;
    private final char letter;
    private final int frequency;

    public Node(char letter, int frequency, Node leftChild, Node rightChild) {
        this.letter = letter;
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

}
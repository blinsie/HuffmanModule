package com.alevel.module2.modelHuffman.algorythm;

import com.alevel.module2.modelTree.Node;
import com.alevel.module2.modelTree.NodeComparable;
import com.alevel.module2.modelTree.PrefixTree;
import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;

@Slf4j
public class FrequencyTable {

    private int[] frequencies;

    public FrequencyTable(int[] frequencies) {

        if (frequencies == null) {
            log.error("Nullable frequencies in FrequencyTable()");
            throw new NullPointerException("Nullable frequencies");
        }
        if (frequencies.length < 2) {
            log.error("Should be at least 2 frequencies for compression in FrequencyTable()");
            throw new IllegalArgumentException("Should be at least 2 frequencies for compression");
        }

        this.frequencies = frequencies;
    }

    public void increaseFrequency(int symbol) {
        checkSymbol(symbol);
        frequencies[symbol]++;
    }

    private void checkSymbol(int symbol) {
        if (symbol < 0 || symbol >= frequencies.length) {
            log.error("checkSymbol() ==> Symbol '" + symbol + "' out of range - " + frequencies.length);
            throw new IllegalArgumentException("Symbol '" + symbol + "' out of range - " + frequencies.length);
        }
    }


    //-------------------------------------PRIORITY QUEUE-----------------------------------------

    public PrefixTree buildPrefixTreeByFrequencies() {

        Queue<Node> queue = new PriorityQueue<Node>();

        // add LEAVES for symbols appropriate to frequencies statistics
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0)
                queue.add(new NodeComparable((char) i, frequencies[i], null, null, 0));
        }

        // queue should have at least 2 Nodes, step skips if queue already have 2 elements
        for (int i = 0; i < frequencies.length && queue.size() < 2; i++) {
            if (frequencies[i] == 0)
                queue.add(new NodeComparable((char) i, 0, null, null, 0));
        }

        if (queue.size() < 2) {
            log.error("buildPrefixTreeByFrequencies() ==> FrequencyTable: Queue size less then 2.");
            throw new IllegalStateException("FrequencyTable: Queue size less then 2.");
        }

        // Connect together two nodes with the lowest frequency
        while (queue.size() > 1) {
            Node x = queue.remove();
            Node y = queue.remove();
            NodeComparable left = (NodeComparable) (x.getFrequency() < y.getFrequency() ? x : y);
            NodeComparable right = (NodeComparable) (x.getFrequency() >= y.getFrequency() ? x : y);
            queue.add(new NodeComparable(
                    '`',
                    x.getFrequency() + y.getFrequency(),
                    left,
                    right,
                    Math.min(left.getCodeSymbol(), right.getCodeSymbol())));
        }
        log.debug("buildPrefixTreeByFrequencies() ==> Building new Prefix Tree");
        return new PrefixTree(queue.remove(), frequencies.length);
    }

}

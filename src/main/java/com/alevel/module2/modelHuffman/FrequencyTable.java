package com.alevel.module2.modelHuffman;

import lombok.extern.slf4j.Slf4j;

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
}

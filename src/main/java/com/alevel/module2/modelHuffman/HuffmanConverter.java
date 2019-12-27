package com.alevel.module2.modelHuffman;

/**
 * Describes type of conversion: encoding or decoding. Needs to choose what type
 * of conversion need to be performed in main-method appropriate to args-files.
 */

public class HuffmanConverter {

    private final HuffmanConverterType type;

    public HuffmanConverter(HuffmanConverterType type) {
        this.type = type;
    }

    public HuffmanConverterType getType() {
        return type;
    }

    public enum HuffmanConverterType {
        ENCODER,
        DECODER
    }

}

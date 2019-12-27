package com.alevel.module2.modelHuffman;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.alevel.module2.modelHuffman.HuffmanConverter.HuffmanConverterType.DECODER;
import static com.alevel.module2.modelHuffman.HuffmanConverter.HuffmanConverterType.ENCODER;

public class HuffmanConverterPool {

    public final Map<String, HuffmanConverter> pool;

    public HuffmanConverterPool() {
        Map<String, HuffmanConverter> pool = new HashMap<>();
        pool.put(".hf", new HuffmanConverter(DECODER));
        pool.put(".txt", new HuffmanConverter(ENCODER));
        pool.put(".jpg", new HuffmanConverter(ENCODER));
        this.pool = Collections.unmodifiableMap(pool);
    }

}

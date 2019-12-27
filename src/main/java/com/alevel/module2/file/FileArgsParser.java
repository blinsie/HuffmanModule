package com.alevel.module2.file;

import com.alevel.module2.modelHuffman.HuffmanConverter;
import com.alevel.module2.modelHuffman.HuffmanConverterPool;
import lombok.extern.slf4j.Slf4j;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class FileArgsParser implements FileParser {

    HuffmanConverterPool converterPool = new HuffmanConverterPool();

    @Override
    public List<File> parse(String... fileName) {
        throw new NotImplementedException();
    }

    public void checkFile(File file) {
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    log.debug("checkFiles() ==> Unable to create file!");
                    throw new RuntimeException("Unable to create file!");
                }
            }
        } catch (IOException e) {
            log.debug("checkFiles() ==> IOException found");
            throw new RuntimeException(e);
        }
    }

    public HuffmanConverter getConverterType(String str) {
        log.debug("Getting converter type in getConverterType()");
        if (str.endsWith(".hf")) {
            return converterPool.pool.get(".hf");
        } else if (str.endsWith(".txt")) {
            return converterPool.pool.get(".txt");
        } else if (str.endsWith(".jpg")) {
            return converterPool.pool.get(".jpg");
        } else {
            throw new IllegalArgumentException("Unknowing type of file extension to convert");
        }
    }

}

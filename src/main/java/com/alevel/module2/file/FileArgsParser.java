package com.alevel.module2.file;

import com.alevel.module2.modelHuffman.HuffmanConverter;
import com.alevel.module2.modelHuffman.HuffmanConverterPool;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileArgsParser implements FileParser {

    HuffmanConverterPool converterPool = new HuffmanConverterPool();

    @Override
    public List<File> parse(String... fileName) {
        if (fileName == null) {
            log.error("parse() ==> Nullable arguments for main program");
            throw new NullPointerException("Nullable arguments for main program");
        }
        if (fileName.length < 1) {
            log.error("parse() ==> There is nothing to parse");
            throw new IllegalArgumentException("There is nothing to parse");
        }

        List<File> args = new ArrayList<>();

        if (fileName.length == 1) {
            if (fileName[0].endsWith(".hf")) {
                args.add(new File(fileName[0]));
                String output = fileName[0].replace(".hf", "");
                args.add(new File(output));
            } else {
                args.add(new File(fileName[0]));
                args.add(new File(fileName[0] + ".hf"));
            }
        } else if (fileName.length >= 2) {
            args.add(new File(fileName[0]));
            args.add(new File(fileName[1]));
        }

        for (File file : args) {
            log.debug("Checking file " + file.getPath());
            checkFiles(file);
        }
        return args;
    }

    private void checkFiles(File file) {
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

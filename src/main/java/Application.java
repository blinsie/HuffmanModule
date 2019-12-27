import com.alevel.module2.file.BitWriter;
import com.alevel.module2.file.FileArgsParser;
import com.alevel.module2.modelHuffman.HuffmanConverter;
import com.alevel.module2.modelHuffman.algorythm.FrequencyTable;
import com.alevel.module2.modelHuffman.algorythm.HuffmanCompressionUtils;
import com.alevel.module2.modelTree.PrefixTree;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

@Slf4j
public class Application {

    //Launch the program
    public static void main(String[] args) throws IOException {
        log.info("-------------------------------STARTING APPLICATION-------------------------------");

        FileArgsParser parser = new FileArgsParser();
        List<File> files = parser.parse(args);

        HuffmanConverter converter = parser.getConverterType(files.get(0).getPath());

        switch (converter.getType()) {
            case DECODER: {
                doDecompression(files);
                break;
            }
            case ENCODER: {
                doCompression(files);
                break;
            }
            default:
                throw new IllegalStateException("Unknown type of conversion");
        }

        log.info("-------------------------------FINISHING APPLICATION-------------------------------");
    }

    private static void doCompression(List<File> files) throws IOException {
        log.debug("doCompression() ==> Creating HuffmanCompressionUtils variable - compress");
        HuffmanCompressionUtils compress = new HuffmanCompressionUtils();

        log.debug("doCompression() ==> Creating FrequencyTable variable - frequencies");
        FrequencyTable frequencies = compress.getFrequencies(files.get(0));
        frequencies.increaseFrequency(256);                                            // EOF have frequency of 1

        log.debug("doCompression() ==> Creating new Prefix Tree");
        PrefixTree prefixTree = frequencies.buildPrefixTreeByFrequencies();

        log.debug("doCompression() ==> Try to write table and binary code to file");
        try (InputStream in = new BufferedInputStream(new FileInputStream(files.get(0)))) {
            try (BitWriter out = new BitWriter(new BufferedOutputStream(new FileOutputStream(files.get(1))))) {
                compress.writeCodeLengthTable(out, prefixTree);
                compress.compress(prefixTree, in, out);
            }
        }
    }

    private static void doDecompression(List<File> files) throws IOException {


    }
}

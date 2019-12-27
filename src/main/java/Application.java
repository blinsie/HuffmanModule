import com.alevel.module2.file.FileArgsParser;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class Application {

    //Launch the program
    public static void main(String[] args) {
        log.info("-------------------------------STARTING APPLICATION-------------------------------");

        FileArgsParser parser = new FileArgsParser();
        List<File> files = parser.parse(args);


        log.info("-------------------------------FINISHING APPLICATION-------------------------------");
    }

    private static void doCompression(List<File> files) throws IOException {

    }

    private static void doDecompression(List<File> files) throws IOException {


    }
}

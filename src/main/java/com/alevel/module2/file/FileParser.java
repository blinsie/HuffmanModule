package com.alevel.module2.file;

import java.io.File;
import java.util.List;

public interface FileParser {

    List<File> parse(String... fileName);

}

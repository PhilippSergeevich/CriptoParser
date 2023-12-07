package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandler {
    public void quotesWriter(String currentPriceWithData) {
        Path absolutePath = Path.of("quotes");
        String dataWithNewLine = currentPriceWithData + "\n";
        byte[] bytesToWrite = dataWithNewLine.getBytes();
        try {
            Files.write(absolutePath, bytesToWrite, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
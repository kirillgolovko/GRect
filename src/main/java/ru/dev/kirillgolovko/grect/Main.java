package ru.dev.kirillgolovko.grect;

import ru.dev.kirillgolovko.grect.gcode.processor.StringProcessor;
import ru.dev.kirillgolovko.grect.gcode.processor.StringProcessorFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Path pathFromGCode = new File(args[0]).toPath();
        Path pathMatrix = new File(args[1]).toPath();

        StringProcessorFactory factory = new StringProcessorFactory(Utils
                .readMatrixFromFile(pathMatrix)
                .orElseThrow(IllegalArgumentException::new));

        Scanner scanner = new Scanner(pathFromGCode);

        File resultGCode = new File(args[2]);
        PrintWriter printWriter = new PrintWriter(resultGCode);

        while (scanner.hasNextLine())
        {
           String current = scanner.next();
           StringProcessor stringProcessor = factory.getStringProcessor(current);
           printWriter.println(stringProcessor.processString());
        }
    }
}

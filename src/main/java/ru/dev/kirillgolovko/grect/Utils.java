package ru.dev.kirillgolovko.grect;

import ru.dev.kirillgolovko.grect.math.Matrix3D;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Utils {
    public static <U> List<U> repeat(U x, int times) {
        ArrayList<U> arrayList = new ArrayList<>();
        for (int i = 0; i < times; ++i) {
            arrayList.add(x);
        }
        return arrayList;
    }

    public static double dot(List<Double> vector, double a) {
        return vector.stream().collect(Collectors.summarizingDouble(v -> v * a)).getSum();
    }

    public static Optional<Matrix3D> readMatrixFromFile(Path path) {
        try {
            List<String> rows = Files.readAllLines(path);
            return Optional.of(Matrix3D.fromString(rows));
        } catch (IOException ex) {
            return Optional.empty();
        }
    }
}

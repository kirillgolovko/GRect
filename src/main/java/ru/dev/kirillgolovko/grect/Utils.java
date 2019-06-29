package ru.dev.kirillgolovko.grect;

import ru.dev.kirillgolovko.grect.gcode.parcer.GcodeVectorTypes;
import ru.dev.kirillgolovko.grect.math.Matrix3D;
import ru.dev.kirillgolovko.grect.math.Vector3D;

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

    private static String coordinateToString(double a, char c)
    {
        return a == 0 ? "" : String.format("%c%.4f ", c, a);
    }

    public static String Vector3DtoString(Vector3D vector3D, GcodeVectorTypes type)
    {
        StringBuilder sb = new StringBuilder()
                .append(coordinateToString(vector3D.x, type.equals(GcodeVectorTypes.XYZ) ? 'X' : 'I'))
                .append(coordinateToString(vector3D.y, type.equals(GcodeVectorTypes.XYZ) ? 'Y' : 'J'))
                .append(coordinateToString(vector3D.z, type.equals(GcodeVectorTypes.XYZ) ? 'Z' : 'K'));

        return sb.toString();
    }
}

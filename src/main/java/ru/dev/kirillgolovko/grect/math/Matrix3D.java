package ru.dev.kirillgolovko.grect.math;

import ru.dev.kirillgolovko.grect.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Matrix3D {

    public final List<List<Double>> matrix;

    public final List<List<Double>> matrixT;

    public Matrix3D(List<List<Double>> matrix) {
        assert matrix.size() == 3 && matrix.stream().anyMatch(row -> row.size() == 3);
        this.matrix = zerosInternal();
        matrixT = zerosInternal();
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                this.matrix.get(row).set(col, matrix.get(row).get(col));
            }
        }

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                this.matrix.get(col).set(row, matrix.get(row).get(col));
            }
        }
    }

    private static List<List<Double>> zerosInternal() {
        List<List<Double>> zeros = new ArrayList<>();
        zeros.add(Utils.repeat(0.0, 3));
        zeros.add(Utils.repeat(0.0, 3));
        zeros.add(Utils.repeat(0.0, 3));
        return zeros;
    }

    public Matrix3D fromString(String matrix) {
        List<List<Double>> mat = new ArrayList<>();
        for (String row : matrix.split("\n")) {
            mat.add(Arrays.stream(row.split(" ")).map(Double::parseDouble).collect(Collectors.toList()));
        }
        return new Matrix3D(mat);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        matrix.forEach(row -> stringBuilder
                .append(String.join(
                        " ",
                        row.stream().map(val -> Double.toString(val)).collect(Collectors.toList())))
                .append("\n"));
        return stringBuilder.toString();
    }

    public Vector3D convertBasis(Vector3D vectorInOldBazis) {
        return new Vector3D(
                Utils.dot(matrixT.get(0), vectorInOldBazis.x),
                Utils.dot(matrixT.get(1), vectorInOldBazis.y),
                Utils.dot(matrixT.get(2), vectorInOldBazis.z));
    }
}

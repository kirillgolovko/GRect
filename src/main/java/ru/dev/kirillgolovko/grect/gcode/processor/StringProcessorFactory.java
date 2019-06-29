package ru.dev.kirillgolovko.grect.gcode.processor;

import ru.dev.kirillgolovko.grect.math.Matrix3D;

public class StringProcessorFactory {
    private final Matrix3D rectificationMatrix;

    public StringProcessorFactory(Matrix3D rectificationMatrix) {
        this.rectificationMatrix = rectificationMatrix;
    }

    public StringProcessor getStringProcessor(String string) {
        if (string.startsWith("G")) {
            return new RectifierProcessor(string, rectificationMatrix);
        } else {
            return () -> string;
        }
    }
}

package ru.dev.kirillgolovko.grect.gcode.processor;

import ru.dev.kirillgolovko.grect.math.Matrix3D;

public class RectifierProcessor implements StringProcessor {

    private final String originalString;

    private final Matrix3D rectificationMatrix;

    public RectifierProcessor(String originalString, Matrix3D rectificationMatrix) {
        this.originalString = originalString;
        this.rectificationMatrix = rectificationMatrix;
    }

    @Override
    public String processString() {
        return originalString;
    }
}

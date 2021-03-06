package ru.dev.kirillgolovko.grect.gcode.processor;

import java.util.*;
import java.util.stream.Collectors;

import ru.dev.kirillgolovko.grect.Utils;
import ru.dev.kirillgolovko.grect.gcode.parcer.GcodeCoordinates;
import ru.dev.kirillgolovko.grect.gcode.parcer.GcodeVectorTypes;
import ru.dev.kirillgolovko.grect.gcode.parcer.Token;
import ru.dev.kirillgolovko.grect.gcode.parcer.VectorFromTokensBuilder;
import ru.dev.kirillgolovko.grect.math.Matrix3D;
import ru.dev.kirillgolovko.grect.math.Vector3D;

public class RectifierProcessor implements StringProcessor {

    private final String originalString;

    private final Matrix3D rectificationMatrix;

    public RectifierProcessor(String originalString, Matrix3D rectificationMatrix) {
        this.originalString = originalString;
        this.rectificationMatrix = rectificationMatrix;
    }

    @Override
    public String processString() {
        List<Token> tokens = splitStringToTokens();
        VectorFromTokensBuilder vectorFromTokensBuilder = new VectorFromTokensBuilder();
        tokens.forEach(token -> GcodeCoordinates.parceNextCoordinateToken(token, vectorFromTokensBuilder));
        Map<GcodeVectorTypes, Vector3D> vectors = vectorFromTokensBuilder.bake();
        if (vectors.isEmpty()) {
            return originalString;
        }
        Map<GcodeVectorTypes, Vector3D> rectifiedVectors = rectifyVectors(vectors);

        return buildUpString(rectifiedVectors, tokens);
    }

    private List<Token> splitStringToTokens() {
        return Arrays
                .stream(originalString.split(" "))
                .map(Token::getTokenForSubstr)
                .collect(Collectors.toList());
    }

    private Map<GcodeVectorTypes, Vector3D> rectifyVectors(Map<GcodeVectorTypes, Vector3D> oldVectors) {
        Map<GcodeVectorTypes, Vector3D> vectors = new HashMap<>();
        oldVectors.forEach((key, value) -> vectors.put(key, rectificationMatrix.convertBasis(value)));
        return vectors;
    }

    private String buildUpString(Map<GcodeVectorTypes, Vector3D> rectifiedVectors, List<Token> tokens) {
        List<String> result = new LinkedList<>();
        Set<GcodeVectorTypes> printedVectors = new HashSet<>();
        tokens.forEach(token -> {
            switch (token.tokenType) {
                case XYZ_COORDINATE:
                    if (!printedVectors.contains(GcodeVectorTypes.XYZ)) {
                        result.add(Utils.Vector3DtoString(rectifiedVectors.get(
                                GcodeVectorTypes.XYZ), GcodeVectorTypes.XYZ));
                        printedVectors.add(GcodeVectorTypes.XYZ);
                    }
                    break;
                case IJK_COORDINATE:
                    if (!printedVectors.contains(GcodeVectorTypes.IJK)) {
                        result.add(Utils.Vector3DtoString(rectifiedVectors.get(
                                GcodeVectorTypes.IJK), GcodeVectorTypes.IJK));
                        printedVectors.add(GcodeVectorTypes.IJK);
                    }
                    break;
                default:
                    result.add(token.originalString);
                    break;
            }
        });
        return String.join(" ", result);
    }
}

package ru.dev.kirillgolovko.grect.gcode.parcer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


public enum TokenTypes {
    IJK_COORDINATE(tokenString -> TokenTypes.IjkPrefixes.stream().anyMatch(tokenString::startsWith)),
    NOT_COORDINATE(tokenString -> !(
            TokenTypes.XyzPrefixes.stream().anyMatch(tokenString::startsWith)
                    || TokenTypes.IjkPrefixes.stream().anyMatch(tokenString::startsWith))),
    XYZ_COORDINATE(tokenString -> TokenTypes.XyzPrefixes.stream().anyMatch(tokenString::startsWith));

    private final static List<String> XyzPrefixes = Arrays.asList("X", "Y", "Z");
    private final static List<String> IjkPrefixes = Arrays.asList("I", "J", "K");

    private Function<String, Boolean> tokenTypeChecker;

    private TokenTypes(Function<String, Boolean> tokenTypeChecker) {
        this.tokenTypeChecker = tokenTypeChecker;
    }

    public static TokenTypes classifyToken(String token) {
        return Arrays.stream(TokenTypes.values())
                .filter(tokenType -> tokenType.tokenTypeChecker.apply(token))
                .findFirst()
                .orElse(NOT_COORDINATE);
    }
}

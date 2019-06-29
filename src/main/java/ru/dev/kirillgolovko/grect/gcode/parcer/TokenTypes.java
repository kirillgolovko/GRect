package ru.dev.kirillgolovko.grect.gcode.parcer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


public enum TokenTypes {
    IJK_COORDINATE(tokenString -> TokenPrefixes.ijkPrefixes.stream().anyMatch(tokenString::startsWith)),
    NOT_COORDINATE(tokenString -> !(
            TokenPrefixes.xyzPrefixes.stream().anyMatch(tokenString::startsWith)
                    || TokenPrefixes.ijkPrefixes.stream().anyMatch(tokenString::startsWith))),
    XYZ_COORDINATE(tokenString -> TokenPrefixes.xyzPrefixes.stream().anyMatch(tokenString::startsWith));

    private Function<String, Boolean> tokenTypeChecker;

    TokenTypes(Function<String, Boolean> tokenTypeChecker) {
        this.tokenTypeChecker = tokenTypeChecker;
    }

    public static TokenTypes classifyToken(String token) {
        return Arrays.stream(TokenTypes.values())
                .filter(tokenType -> tokenType.tokenTypeChecker.apply(token))
                .findFirst()
                .orElse(NOT_COORDINATE);
    }

    private static class TokenPrefixes {
        public final static List<String> xyzPrefixes = Arrays.asList("X", "Y", "Z");
        public final static List<String> ijkPrefixes = Arrays.asList("I", "J", "K");
    }
}

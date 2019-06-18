package ru.dev.kirillgolovko.grect.gcode.parcer;

public class Token {

    public final String originalString;

    public final TokenTypes tokenType;

    private Token(String originalString, TokenTypes tokenType) {
        this.originalString = originalString;
        this.tokenType = tokenType;
    }

    public static Token getTokenForSubstr(String originalString) {
        TokenTypes type = TokenTypes.classifyToken(originalString);
        return new Token(originalString, type);
    }
}

package ru.dev.kirillgolovko.grect.gcode.parcer;

        import java.util.function.BiConsumer;

public enum GcodeCoordinates {
    X((val, builder) -> builder.addX(val)),
    Y((val, builder) -> builder.addY(val)),
    Z((val, builder) -> builder.addZ(val)),
    I((val, builder) -> builder.addI(val)),
    J((val, builder) -> builder.addJ(val)),
    K((val, builder) -> builder.addK(val));

    private BiConsumer<Double, VectorFromTokensBuilder> addToBuilder;

    private GcodeCoordinates(BiConsumer<Double, VectorFromTokensBuilder> addToBuilder) {
        this.addToBuilder = addToBuilder;
    }

    public static void parceNextCoordinateToken(Token token, VectorFromTokensBuilder builder) {
        if (!token.tokenType.equals(TokenTypes.XYZ_COORDINATE) && !token.tokenType.equals(TokenTypes.IJK_COORDINATE)) {
            throw new IllegalArgumentException("Token must be coordinate, got " + token.tokenType.toString());
        }
        String coordinate = token.originalString.substring(0, 1);
        double value = Double.parseDouble(token.originalString.substring(1));
        valueOf(coordinate).addToBuilder.accept(value, builder);
    }
}

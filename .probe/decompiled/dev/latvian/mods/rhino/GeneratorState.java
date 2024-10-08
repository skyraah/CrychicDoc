package dev.latvian.mods.rhino;

public class GeneratorState {

    public static final int GENERATOR_SEND = 0;

    public static final int GENERATOR_THROW = 1;

    public static final int GENERATOR_CLOSE = 2;

    int operation;

    Object value;

    RuntimeException returnedException;

    GeneratorState(int operation, Object value) {
        this.operation = operation;
        this.value = value;
    }

    public static class GeneratorClosedException extends RuntimeException {

        private static final long serialVersionUID = 2561315658662379681L;
    }
}
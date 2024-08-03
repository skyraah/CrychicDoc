package me.shedaniel.cloth.clothconfig.shadowed.org.yaml.snakeyaml.constructor;

import me.shedaniel.cloth.clothconfig.shadowed.org.yaml.snakeyaml.error.Mark;

public class DuplicateKeyException extends ConstructorException {

    protected DuplicateKeyException(Mark contextMark, Object key, Mark problemMark) {
        super("while constructing a mapping", contextMark, "found duplicate key " + String.valueOf(key), problemMark);
    }
}
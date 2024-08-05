package snownee.kiwi.shadowed.org.yaml.snakeyaml.parser;

import snownee.kiwi.shadowed.org.yaml.snakeyaml.error.Mark;
import snownee.kiwi.shadowed.org.yaml.snakeyaml.error.MarkedYAMLException;

public class ParserException extends MarkedYAMLException {

    private static final long serialVersionUID = -2349253802798398038L;

    public ParserException(String context, Mark contextMark, String problem, Mark problemMark) {
        super(context, contextMark, problem, problemMark, null, null);
    }
}
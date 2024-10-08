package snownee.kiwi.shadowed.org.yaml.snakeyaml.tokens;

import snownee.kiwi.shadowed.org.yaml.snakeyaml.error.Mark;

public final class DocumentStartToken extends Token {

    public DocumentStartToken(Mark startMark, Mark endMark) {
        super(startMark, endMark);
    }

    @Override
    public Token.ID getTokenId() {
        return Token.ID.DocumentStart;
    }
}
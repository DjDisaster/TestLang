package parser;

import syntax.Syntax;

import java.util.List;

public class MatchedSyntax {

    private Syntax syntax;
    private List<Token> tokens;
    private String transpiledLine;
    public MatchedSyntax(Syntax syntax, List<Token> tokens, String transpiledLine) {
        this.syntax = syntax;
        this.tokens = tokens;
        this.transpiledLine = transpiledLine;
    }

    public Syntax getSyntax() {
        return syntax;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public String getTranspiledLine() {
        return transpiledLine;
    }

}

package syntax;

import parser.Token;

import java.util.List;

public class Syntax {

    private String syntax;
    private String transpiled;
    public Syntax(String syntax, String transpiled) {
        this.syntax = syntax;
        this.transpiled = transpiled;
    }

    public boolean isMatch(String input) {
        return input.equals(syntax);
    }

    public String getTranspiled(List<Token> tokens) {
        int n = 1;
        String transpiledCopy = transpiled;
        for (Token token : tokens) {
            if (!token.getMatcher().getIdentifier().equals("literal")) {
                transpiledCopy = transpiledCopy.replace("%expr-" + n + "%", token.getValue());
            }
        };

        return transpiledCopy;
    }

}

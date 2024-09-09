package parser;

import syntax.Syntax;
import syntax.SyntaxRegisterer;

import java.util.ArrayList;
import java.util.List;

public class SyntaxMatcher {

    private SyntaxRegisterer syntaxRegisterer = new SyntaxRegisterer();

    public List<MatchedSyntax> matchList(List<List<Token>> tokens) {
        List<MatchedSyntax> results = new ArrayList<>();
        for (List<Token> tokenList : tokens) {
            results.add(match(tokenList));
        }

        System.out.println("Results:");
        System.out.println(results.toString());
        for (MatchedSyntax syntax : results) {
            System.out.println("TP: " + syntax.getTranspiledLine());
        }
        return results;
    }

    public MatchedSyntax match(List<Token> tokens) {
        StringBuilder representation = new StringBuilder();
        for (Token token : tokens) {
            if (token.getMatcher().getIdentifier().equals("literal")) {
                representation.append(token.getValue());
            } else {
                representation.append("%").append(token.getMatcher().getIdentifier()).append("%");
            }
        }


        for (Syntax syntax : syntaxRegisterer.getEffectsSyntax()) {
            if (syntax.isMatch(representation.toString())) {
                return new MatchedSyntax(syntax, tokens, syntax.getTranspiled(tokens));
            }
        }


        return null;
    }



}

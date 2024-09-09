package parser;

import java.util.*;

public class Tokenizer {

    private HashMap<String, TokenMatcher> tokenMatchers = new HashMap<>();

    public Tokenizer() {
        tokenMatchers.put("number", new TokenMatcher("[0-9]", "number", (matcher, value) -> value + "D"));
        tokenMatchers.put("variable", new TokenMatcher("\\{[A-z]+\\}", "variable", (matcher, value) -> value.substring(1,value.length() - 1)));
        tokenMatchers.put("localvariable", new TokenMatcher("\\{_[A-z]+\\}", "localvariable", (matcher, value) -> value.substring(2,value.length() - 1)));
        tokenMatchers.put("literal", new TokenMatcher("$a", "literal", (matcher, value) -> value));
        tokenMatchers.put("string", new TokenMatcher("\"(?:[^\"]|\"{2})*\"", "string", (matcher, value) -> value.substring(1,value.length()-1).replace("\"\"", "\"")));
        tokenMatchers.get("string").setCustomMatcher((current, line, position, endPosition) -> {
            TokenMatcher matcher = tokenMatchers.get("string");
            if (!matcher.getPattern().matcher(current).matches()) {
                return false;
            }
            if (endPosition == line.length()) {
                return true;
            }
            char nextCharacter = line.charAt(line.length() - 1);
            if (nextCharacter == '"') {
                return false;
            }


            return true;
        });
    }

    public List<List<Token>> tokenize(String[] lines) {
        List<List<Token>> tokens = new ArrayList<>();

        for (String line : lines) {
            tokens.add(tokenize(line));
        }

        return tokens;
    }

    public List<Token> tokenize(String line) {

        List<Token> tokens = new ArrayList<>();
        int position = 0;
        int endPosition = 0;

        while (true) {
            // If we reach the end of the line, start checking from a new starting point
            if (endPosition > line.length()) {

                if (position == line.length()) {
                    break;
                }

                tokens.add(new Token(tokenMatchers.get("literal"), line.substring(position, position+1)));

                position = position + 1;
                endPosition = position;
                if (position == line.length()) {
                    break;
                }
            }

            String current = line.substring(position,endPosition);
            //System.out.println("Current: " + current);
            for (TokenMatcher matcher : tokenMatchers.values()) {

                if (matcher.matches(current, line, position, endPosition)) {
                    tokens.add(new Token(matcher, current));
                    position = endPosition;
                    break;
                }
            }

            endPosition++;


        }

        mergeTokens(tokens, 0);

        tokens.forEach(Token::tokenRunnableSetValue);

        for (Token token : tokens) {
            System.out.println("Token: " + token.getMatcher().getIdentifier() + ": " + token.getValue());
        }

        return tokens;
    }

    public void mergeTokens(List<Token> tokens, int point) {
        if (tokens.size() < 2 || (point + 1) == tokens.size()) {
            return;
        }

        Token token1 = tokens.get(point);
        Token token2 = tokens.get(point + 1);

        if (Objects.equals(token1.getMatcher().getIdentifier(), token2.getMatcher().getIdentifier())) {
            tokens.remove(point + 1);
            token1.setValue(token1.getValue() + token2.getValue());
            mergeTokens(tokens, point);
        } else {
            mergeTokens(tokens, point + 1);
        }
    }


}

package parser;

public class Token {

    private TokenMatcher matcher;
    private String value;
    public Token(TokenMatcher matcher, String value) {
        this.matcher = matcher;
        this.value = value;
    }

    public void tokenRunnableSetValue() {
        this.value = matcher.getTokenRunnable().run(matcher, value);
    }

    public TokenMatcher getMatcher() {
        return matcher;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String newValue) {
        value = newValue;
    }



}

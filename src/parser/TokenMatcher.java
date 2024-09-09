package parser;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TokenMatcher {

    private Pattern pattern;
    private String identifier;
    private TokenRunnable tokenRunnable;
    private CustomMatcher matcher;

    public TokenMatcher(String regex, String identifier, TokenRunnable runnable) {
        this.pattern = Pattern.compile(regex);
        this.identifier = identifier;
        this.tokenRunnable = runnable;
        this.matcher = (current, line, position, endPosition) -> pattern.matcher(current).matches();
    }

    public void setCustomMatcher(CustomMatcher matcher) {
        this.matcher = matcher;
    }


    public boolean matches(String current, String line, int position, int endPosition) {
        return matcher.matches(current, line, position, endPosition);
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getIdentifier() {
        return identifier;
    }

    public TokenRunnable getTokenRunnable() {
        return tokenRunnable;
    }




}

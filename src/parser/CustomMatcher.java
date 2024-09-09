package parser;

public interface CustomMatcher {

    boolean matches(String current, String line, int position, int endPosition);

}

package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private Tokenizer tokenizer = new Tokenizer();
    private SyntaxMatcher syntaxMatcher = new SyntaxMatcher();
    public Parser() {
        ParseResult result = loadFile("/home/djdisaster/IdeaProjects/TestLang/src/resources/test");
        System.out.println("result: " + result.isSuccessful());
        System.out.println("time: " + result.getTimeTakenMilliseconds() + "ms");
    }

    public ParseResult loadFile(String path) {
        double start = System.nanoTime();

        File file = new File(path);
        if (!file.exists()) {
            return new ParseResult(false, false, true);
        }

        String[] lines = readFile(file);
        if (lines.length == 0) {
            return new ParseResult(false, true, true);
        }

        List<List<Token>> tokens = tokenizer.tokenize(lines);
        List<MatchedSyntax> parsedLines = syntaxMatcher.matchList(tokens);
        double end = System.nanoTime();


        return new ParseResult(true, ((end - start) / 1000000));
    }

    public String[] readFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            return reader.lines().toArray(String[]::new);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new String[0];
    }



}

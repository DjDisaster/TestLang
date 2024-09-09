package parser;

public class ParseResult {

    private boolean fileFound = true;
    private boolean isEmpty = false;
    private final boolean isSuccessful;
    private double timeTaken = -1;

    public ParseResult(boolean successful, double milliseconds) {
        this.isSuccessful = successful;
        this.timeTaken = milliseconds;
    }

    public ParseResult(boolean successful, boolean fileFound, boolean isEmpty) {
        this.isSuccessful = successful;
        this.fileFound = fileFound;
        this.isEmpty = isEmpty;
    }


    public boolean isFileFound() {
        return fileFound;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public double getTimeTakenMilliseconds() {
        return timeTaken;
    }



}

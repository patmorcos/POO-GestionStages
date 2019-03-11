package exceptions;

/**
 * Represente une exception pour tout probleme relatif a un stage.
 */
public class StageException extends Exception {

    public StageException(String s) {
        super(s);
    }

    public StageException(String message, Throwable cause) {
        super(message, cause);
    }
}

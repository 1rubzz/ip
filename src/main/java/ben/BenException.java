package ben;

/**
 * Represents an exception specific to the Ben chatbot.
 */
public class BenException extends Exception{

    /**
     * Creates a BenException with the specified error message.
     *
     * @param message Error message describing the exception.
     */
    public BenException(String message){
        super(message);
    }
}

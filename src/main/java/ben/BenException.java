package ben;

/**
 * Represents an exception specific to the Ben chatbot.
 *
 */
public class BenException extends Exception{
    public BenException(String message){
        super(message);
    }
}

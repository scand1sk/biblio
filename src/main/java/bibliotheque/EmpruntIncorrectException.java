package bibliotheque;

@SuppressWarnings("serial")
public class EmpruntIncorrectException extends Exception {
	public EmpruntIncorrectException(String message) {
		super(message);
	}
}

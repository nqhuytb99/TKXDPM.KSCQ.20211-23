package common.exception;;

public class EcoException extends RuntimeException {

    public EcoException() {

	}

	public EcoException(String message) {
		super(message);
	}
}
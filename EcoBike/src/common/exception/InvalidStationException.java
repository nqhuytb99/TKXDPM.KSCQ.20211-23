package common.exception;

public class InvalidStationException extends EcoException {
	private static final long serialVersionUID = 1091337136123906298L;
	
	public InvalidStationException() {

	}
	
	public InvalidStationException(String message) {
		super(message);
	}

}

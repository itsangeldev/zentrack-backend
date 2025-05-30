package remzen.zentrack.security.config;

public class CustomException extends RuntimeException {
    public CustomException(String errorCode) {
        super(errorCode);
    }
}


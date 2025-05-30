package remzen.zentrack.security.controller.dto;



public class RegisterRequest {
    private String username;
    private String password;
    private String secretWord;

    // Constructor vacío
    public RegisterRequest() {}

    // Constructor con parámetros
    public RegisterRequest(String username, String password, String secretWord) {
        this.username = username;
        this.password = password;
        this.secretWord = secretWord;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }
}
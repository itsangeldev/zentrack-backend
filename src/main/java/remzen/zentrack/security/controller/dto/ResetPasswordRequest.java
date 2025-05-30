package remzen.zentrack.security.controller.dto;


public class ResetPasswordRequest {
    private String username;
    private String secretWord;
    private String newPassword;

    // Constructor vacío
    public ResetPasswordRequest() {}

    // Constructor con parámetros
    public ResetPasswordRequest(String username, String secretWord, String newPassword) {
        this.username = username;
        this.secretWord = secretWord;
        this.newPassword = newPassword;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public void setSecretWord(String secretWord) {
        this.secretWord = secretWord;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
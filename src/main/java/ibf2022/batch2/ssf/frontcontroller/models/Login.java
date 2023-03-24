package ibf2022.batch2.ssf.frontcontroller.models;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import jakarta.validation.constraints.Size;

public class Login {
    
    @Size(min=2, message="Must be at least 2 characters")
    private String username;

    @Size(min=2, message="Must be at least 2 characters")
    private String password;

    public int answer;

    private int firstNum;

    private int secondNum;

    private String operator;

    public Login(String username) {
        this.username = username;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(int secondNum) {
        this.secondNum = secondNum;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

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

    @Override
    public String toString() {
        return "Login{username=%s, password=%s}".formatted(username, password);
    }

    public int generateFirstNum() {
        Random rand = new Random();
        this.firstNum = rand.nextInt(50) + 1;

        return firstNum;
    }

    public int generateSecondNum() {
        Random rand = new Random();
        this.secondNum = rand.nextInt(50) + 1;

        return secondNum;
    }

    public String generateOperator() {
        String[] arr = {"+", "-", "*", "/"};
        int randIndex = ThreadLocalRandom.current().nextInt(arr.length);
        this.operator = arr[randIndex];
        return operator;
    }
}

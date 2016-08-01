package com.twu.biblioteca.router;

/**
 * Created by chenzhou on 8/1/16.
 */
public class RouterMessage {
    private String  userInput;

    public RouterMessage(String  userInput) {
        this.userInput = userInput;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

}

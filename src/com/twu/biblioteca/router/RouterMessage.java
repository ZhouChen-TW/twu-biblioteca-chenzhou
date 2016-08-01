package com.twu.biblioteca.router;

/**
 * Created by chenzhou on 8/1/16.
 */
public class RouterMessage {
    private String  userInput;
    private boolean exit;

    public RouterMessage(String  userInput,boolean exit) {
        this.userInput = userInput;
        this.exit = exit;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}

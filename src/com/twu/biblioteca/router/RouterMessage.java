package com.twu.biblioteca.router;

/**
 * Created by chenzhou on 8/1/16.
 */
public class RouterMessage {
    private String  userInput;
    private boolean exit;
    private boolean waitForInput;

    public RouterMessage(String  userInput,boolean exit,boolean waitForInput) {
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

    public boolean isWaitForInput() {
        return waitForInput;
    }

    public void setWaitForInput(boolean waitForInput) {
        this.waitForInput = waitForInput;
    }
}

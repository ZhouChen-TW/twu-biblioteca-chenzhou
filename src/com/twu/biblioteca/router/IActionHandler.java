package com.twu.biblioteca.router;

/**
 * Created by chenzhou on 8/1/16.
 */
public interface IActionHandler {
    RouterMessage Handle(String userInput);
}

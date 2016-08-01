package com.twu.biblioteca.router.impl;

import com.twu.biblioteca.router.RouterMessage;

/**
 * Created by chenzhou on 8/1/16.
 */
public interface IActionHandler {
    RouterMessage Handle(String userInput);
}

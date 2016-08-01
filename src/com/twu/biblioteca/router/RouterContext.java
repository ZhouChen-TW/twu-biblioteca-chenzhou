package com.twu.biblioteca.router;

import com.twu.biblioteca.model.RouterState;

public class RouterContext {
    private RouterState currentState ;

    RouterContext(RouterState m_routeState) {
        this.currentState = m_routeState;
    }

    RouterState getCurrentState() {
        return currentState;
    }

    public void setNestState(RouterState state) {
        this.currentState = state;
    }
}

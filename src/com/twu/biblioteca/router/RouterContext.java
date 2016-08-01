package com.twu.biblioteca.router;

import com.twu.biblioteca.model.RouterState;

/**
 * Created by chenzhou on 8/1/16.
 */
public class RouterContext {
    private RouterState currentState ;

    public RouterContext(RouterState m_routeState) {
        this.currentState = m_routeState;
    }

    public RouterState getCurrentState() {
        return currentState;
    }

    public void setNestState(RouterState state) {
        this.currentState = state;
    }
}

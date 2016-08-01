package com.twu.biblioteca.router;

import com.twu.biblioteca.model.RouterState;

/**
 * Created by chenzhou on 8/1/16.
 */
public class RouterContext {
    private RouterState m_routeState;
    private RouterState currentState ;

    public RouterContext(RouterState m_routeState) {
        this.m_routeState = m_routeState;
        this.currentState = m_routeState;
    }

    public RouterState getM_routeState() {
        return m_routeState;
    }

    public void setM_routeState(RouterState m_routeState) {
        this.m_routeState = m_routeState;
    }

    public RouterState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(RouterState currentState) {
        this.currentState = currentState;
    }
}

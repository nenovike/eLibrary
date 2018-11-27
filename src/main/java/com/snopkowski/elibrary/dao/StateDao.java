package com.snopkowski.elibrary.dao;

public enum StateDao {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted"),
    LOCKED("Locked");

    private String state;

    private StateDao(final String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return this.state;
    }


    public String getName() {
        return this.name();
    }


}

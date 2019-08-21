package com.za5groszy.foundation.sharedkernel;

public class UserId {
    private int id;

    public UserId(int id) {
        this.id = id;
    }

    private UserId() {}

    public static UserId nullInstance() {
        return new UserId();
    }

    public int getId() {
        return id;
    }
}

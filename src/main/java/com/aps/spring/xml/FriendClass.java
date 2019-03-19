package com.aps.spring.xml;

public class FriendClass {

    public FriendClass() {
        System.out.println("You have created a friend class object");
    }

    @Override
    public String toString() {
        return String.format("You have created a friend as a member object (%s)",super.toString());
    }
}

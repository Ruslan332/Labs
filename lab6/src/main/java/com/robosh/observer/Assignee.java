package com.robosh.observer;

public interface Assignee {
    void addAssignee(Observer observer);
    void removeAssignee(Observer observer);
    void notifyAssignees();
}

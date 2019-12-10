package com.robosh.observer;

import com.robosh.entity.Developer;

import java.util.ArrayList;
import java.util.List;

public class Git implements Assignee {

    List<Observer> assignees = new ArrayList<>();
    List<String> request = new ArrayList<>();

    public void addRequest(String merge){
        request.add(merge);
        notifyAssignees();
    }


    @Override
    public void addAssignee(Observer observer) {
        assignees.add(observer);
    }

    @Override
    public void removeAssignee(Observer observer) {
        assignees.remove(observer);
    }

    @Override
    public void notifyAssignees() {
        for (Observer observer: assignees) {
            observer.mergeRequest(request);
        }
    }

}

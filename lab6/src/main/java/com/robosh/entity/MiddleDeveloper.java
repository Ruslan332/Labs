package com.robosh.entity;

import com.robosh.decorator.DeveloperDecorator;
import com.robosh.observer.Assignee;
import com.robosh.observer.Observer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MiddleDeveloper extends DeveloperDecorator implements Observer {

    public MiddleDeveloper(Developer developer) {
        super(developer);
    }

    public String addNewFunctional() {
        return "Add new functional";
    }

    @Override
    public String doWork() {
        return super.doWork() + "; " + addNewFunctional();
    }

    @Override
    public void mergeRequest(List<String> requests) {
        log.info("We have new merge requests : " + requests);
    }
}

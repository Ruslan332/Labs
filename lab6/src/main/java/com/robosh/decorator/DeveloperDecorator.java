package com.robosh.decorator;

import com.robosh.entity.Developer;

public class DeveloperDecorator implements Developer {
    private Developer developer;

    public DeveloperDecorator(Developer developer) {
        this.developer = developer;
    }

    @Override
    public String doWork() {
        return developer.doWork();
    }
}

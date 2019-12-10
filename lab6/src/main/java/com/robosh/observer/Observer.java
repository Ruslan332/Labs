package com.robosh.observer;

import java.util.List;

public interface Observer {
    void mergeRequest(List<String> requests);
}

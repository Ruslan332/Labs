package com.robosh;

import com.robosh.entity.Developer;
import com.robosh.entity.Employee;
import com.robosh.entity.JuniorDeveloper;
import com.robosh.entity.MiddleDeveloper;
import com.robosh.entity.SeniorDeveloper;
import com.robosh.observer.Git;
import com.robosh.observer.Observer;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static void main(String[] args) {
    Developer developer = getEmployee(Employee.SENIOR);
    Developer developer1 = getEmployee(Employee.JUNIOR);
    Developer developer2 = getEmployee(Employee.MIDDLE);
    log.info(Objects.requireNonNull(developer).doWork());
    log.info(Objects.requireNonNull(developer1).doWork());
    log.info(Objects.requireNonNull(developer2).doWork());

    Git git = new Git();
    git.addRequest("ST-1 merge");
    git.addRequest("ST-2 merge");

    Observer mainAssignee = (Observer) getEmployee(Employee.SENIOR);
    Observer additionalAssignee = (Observer) getEmployee(Employee.MIDDLE);

    git.addAssignee(mainAssignee);

    git.addRequest("St-3_BE merge");
    git.addAssignee(additionalAssignee);
    git.addRequest("St-1_FE merge");

  }

  private static Developer getEmployee(Employee employee) {
    switch (employee) {
      case JUNIOR:
        return new JuniorDeveloper();
      case MIDDLE:
        return new MiddleDeveloper(new JuniorDeveloper());
      case SENIOR:
        return new SeniorDeveloper(new MiddleDeveloper(new JuniorDeveloper()));
      default:
        return null;
    }
  }
}

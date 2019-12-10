package com.laba2.entities;

import com.laba2.annotations.MyAnnotation;
import com.laba2.annotations.TestAnnotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MyAnnotation
public class Tablet extends Computer {

  @MyAnnotation
  private OS os;
  private float diagonal;
  private int batteryCapacity;

  @Builder
  public Tablet(String name, float frequency, int cores, int ram, int memory,
      OS os, float diagonal, int batteryCapacity) {
    super(name, frequency, cores, ram, memory);
    this.os = os;
    this.diagonal = diagonal;
    this.batteryCapacity = batteryCapacity;
  }

  @TestAnnotation
  private void annotatedMethod() {
    os = OS.ANDROID;
  }
}

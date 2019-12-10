package com.laba2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Computer {

  protected String name;
  private float frequency;
  private int cores;
  private int ram;
  protected int memory;

}

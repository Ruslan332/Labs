package com.robosh.entity;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    @Override
    public String toString() {
        return "QuadraticEquation = {" +
                a + "x^2 + " + b + "x + " + c +
                '}';
    }
}

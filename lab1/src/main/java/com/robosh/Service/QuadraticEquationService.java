package com.robosh.Service;

import com.robosh.entity.QuadraticEquation;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


@Setter
@Getter
public class QuadraticEquationService {
    private QuadraticEquation quadraticEquation;

    public QuadraticEquationService(QuadraticEquation quadraticEquation) {
        this.quadraticEquation = quadraticEquation;
    }

    public QuadraticEquationService() {
    }


    public double[] getResult() {
        if (getDescriminator() < 0) {
            return new double[]{};
        }
        if (getDescriminator() == 0) {
            return new double[]{
                    getX1()
            };
        }
        return new double[]{
                getX1(),
                getX2()
        };
    }

    private double getDescriminator() {
        return Math.pow(quadraticEquation.getB(), 2)
                - 4 * quadraticEquation.getA() * quadraticEquation.getC();
    }

    private double getX1() {
        return (-quadraticEquation.getB() + Math.sqrt(getDescriminator()))
                / (2 * quadraticEquation.getA());
    }

    private double getX2() {
        return (-quadraticEquation.getB() - Math.sqrt(getDescriminator()))
                / (2 * quadraticEquation.getA());
    }

    @Override
    public String toString() {
        return quadraticEquation +
                "\nResult: " +
                "Descriminant = " + getDescriminator() +
                "\nResult: " + Arrays.toString(getResult());
    }

}

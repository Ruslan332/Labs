package com.robosh.Service;

import com.robosh.entity.QuadraticEquation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuadraticEquationServiceTest {
    private QuadraticEquationService quadraticEquationService;

    @BeforeAll
    public void init() {
        quadraticEquationService = new QuadraticEquationService();
    }

    @Test
    public void ShouldReturnTwoSolutions() {
        QuadraticEquation quadraticEquation = new QuadraticEquation();

        double[][] value = new double[][]{
                {2, 5, -3},
                {-1, 5, 0},
                {3, -14, -5},
                {1, -70, 600}
        };

        double[][] expected = new double[][]{
                {0.5, -3.0},
                {-0., 5.},
                {5, -1d / 3d},
                {60, 10}
        };

        for (int i = 0; i < value.length; i++) {
            quadraticEquation.setA(value[i][0]);
            quadraticEquation.setB(value[i][1]);
            quadraticEquation.setC(value[i][2]);
            quadraticEquationService.setQuadraticEquation(quadraticEquation);
            assertArrayEquals(expected[i], quadraticEquationService.getResult());
        }

    }

    @Test
    public void ShouldReturnOneSolution() {
        QuadraticEquation quadraticEquation = new QuadraticEquation();

        double[][] value = new double[][]{
                {3, -18, 27},
                {4, 4, 1},
                {3, 6, 3}
        };

        double[][] expected = new double[][]{
                {3},
                {-0.5},
                {-1},
        };

        for (int i = 0; i < value.length; i++) {
            quadraticEquation.setA(value[i][0]);
            quadraticEquation.setB(value[i][1]);
            quadraticEquation.setC(value[i][2]);
            quadraticEquationService.setQuadraticEquation(quadraticEquation);
            assertArrayEquals(expected[i], quadraticEquationService.getResult());
        }
    }

    @Test
    public void shouldReturnEmptyArray() {
        QuadraticEquation quadraticEquation = new QuadraticEquation();

        double[][] value = new double[][]{
                {-9, -1, -1},
                {2, -1, 3.5},
                {2, -6, 6},
                {2, -7, 7}
        };

        double[] expected = new double[]{};

        for (double[] doubles : value) {
            quadraticEquation.setA(doubles[0]);
            quadraticEquation.setB(doubles[1]);
            quadraticEquation.setC(doubles[2]);
            quadraticEquationService.setQuadraticEquation(quadraticEquation);
            assertArrayEquals(expected, quadraticEquationService.getResult());
        }
    }
}
package ru.spbau.mit.kazakov.SpiralMatrix;

import java.util.Arrays;
import java.util.Comparator;


/**
 * Stores transpose square matrix with odd dimensions, sorts matrix's columns by first elements, prints matrix spirally.
 */
public class SpiralMatrix {
    private int[][] matrix;

    private enum Direction {RIGHT, UP, LEFT, DOWN}

    /**
     * Initialize matrix with given data.
     *
     * @param data initializing matrix
     */
    public SpiralMatrix(int[][] data) {
        matrix = new int[data.length][data.length];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                matrix[i][j] = data[j][i];
            }
        }
    }

    /**
     * Prints matrix: each element separated by space symbol, each row separated by new line symbol.
     */
    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[j][i] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Sorts columns by first elements.
     */
    public void sortMatrix() {
        Arrays.sort(matrix, Comparator.comparingInt(col -> col[0]));
    }

    /**
     * Prints matrix in spiral order. Elements separated by space symbol.
     */
    public void printSpiralMatrix() {
        int steps = 0;
        int iCurrent = matrix.length / 2;
        int jCurrent = matrix.length / 2;

        while (true) {
            for (Direction direction : Direction.values()) {
                switch (direction) {
                    case RIGHT:
                        steps++;
                        for (int step = 1; step <= steps; step++) {
                            System.out.print(matrix[iCurrent][jCurrent] + " ");
                            iCurrent++;
                        }

                        if (steps == matrix.length) {
                            return;
                        }
                        break;

                    case UP:
                        for (int step = 1; step <= steps; step++) {
                            System.out.print(matrix[iCurrent][jCurrent] + " ");
                            jCurrent--;
                        }
                        break;

                    case LEFT:
                        steps++;
                        for (int step = 1; step <= steps; step++) {
                            System.out.print(matrix[iCurrent][jCurrent] + " ");
                            iCurrent--;
                        }

                        if (steps == matrix.length) {
                            return;
                        }
                        break;

                    case DOWN:
                        for (int step = 1; step <= steps; step++) {
                            System.out.print(matrix[iCurrent][jCurrent] + " ");
                            jCurrent++;
                        }
                        break;
                }
            }
        }
    }
}

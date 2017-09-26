package ru.spbau.mit.kazakov.SpiralMatrix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpiralMatrixTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testConstructor() {
        int[][] matrix = new int[][]{{3, 1, 5},
                {54, 43, 32},
                {21, 53, 76}};
        SpiralMatrix spiral = new SpiralMatrix(matrix);
    }

    @Test
    public void testPrintMatrix3x3() {
        int[][] matrix = new int[][]{{3, 1, 5},
                {54, 43, 32},
                {21, 53, 76}};

        SpiralMatrix spiral = new SpiralMatrix(matrix);
        spiral.printMatrix();

        assertEquals("3 1 5 \n54 43 32 \n21 53 76", outContent.toString().trim());
    }

    @Test
    public void testPrintMatrix15x5() {
        int[][] matrix = new int[][]{{3, 1, 5, -1, 0},
                {54, 43, 32, 3, 2},
                {21, 53, 76, 7, 2},
                {21, 87, 32, 98, 23},
                {1, 4, 6, 4, 3}};

        SpiralMatrix spiral = new SpiralMatrix(matrix);
        spiral.printMatrix();

        assertEquals("3 1 5 -1 0 \n" +
                "54 43 32 3 2 \n" +
                "21 53 76 7 2 \n" +
                "21 87 32 98 23 \n" +
                "1 4 6 4 3", outContent.toString().trim());
    }

    @Test
    public void testPrintMatrix1x1() {
        int[][] matrix = new int[][]{{3}};

        SpiralMatrix spiral = new SpiralMatrix(matrix);
        spiral.printMatrix();

        assertEquals("3", outContent.toString().trim());
    }

    @Test
    public void testSortMatrix3x3() {
        int[][] matrix = new int[][]{{3, 1, 5},
                {54, 43, 32},
                {21, 53, 76}};

        SpiralMatrix spiral = new SpiralMatrix(matrix);
        spiral.sortMatrix();
        spiral.printMatrix();

        assertEquals("1 3 5 \n43 54 32 \n53 21 76", outContent.toString().trim());
    }

    @Test
    public void testPrintSortMatrix15x5() {
        int[][] matrix = new int[][]{{3, 1, 5, -1, 0},
                {54, 43, 32, 3, 2},
                {21, 53, 76, 7, 2},
                {21, 87, 32, 98, 23},
                {1, 4, 6, 4, 3}};

        SpiralMatrix spiral = new SpiralMatrix(matrix);
        spiral.sortMatrix();
        spiral.printMatrix();

        assertEquals("-1 0 1 3 5 \n" +
                "3 2 43 54 32 \n" +
                "7 2 53 21 76 \n" +
                "98 23 87 21 32 \n" +
                "4 3 4 1 6", outContent.toString().trim());
    }

    @Test
    public void testSortMatrix1x1() {
        int[][] matrix = new int[][]{{3}};

        SpiralMatrix spiral = new SpiralMatrix(matrix);
        spiral.sortMatrix();
        spiral.printMatrix();

        assertEquals("3", outContent.toString().trim());
    }

    @Test
    public void testPrintSpiralMatrix13x3() {
        int[][] matrix = new int[][]{{3, 1, 5},
                {54, 43, 32},
                {21, 53, 76}};

        SpiralMatrix spiral = new SpiralMatrix(matrix);
        spiral.printSpiralMatrix();

        assertEquals("43 32 5 1 3 54 21 53 76", outContent.toString().trim());
    }

    @Test
    public void testPrintSpiralMatrix15x5() {
        int[][] matrix = new int[][]{{3, 1, 5, 6, 7},
                {54, 43, 32, 3, 2},
                {21, 53, 76, 7, 2},
                {21, 87, 32, 98, 23},
                {1, 4, 6, 4, 3}};

        SpiralMatrix spiral = new SpiralMatrix(matrix);
        spiral.printSpiralMatrix();

        assertEquals("76 7 3 32 43 53 87 32 98 23 2 2 7 6 5 1 3 54 21 21 1 4 6 4 3", outContent.toString().trim());
    }

    @Test
    public void testPrintSpiralMatrix1x1() {
        int[][] matrix = new int[][]{{3}};

        SpiralMatrix spiral = new SpiralMatrix(matrix);
        spiral.printSpiralMatrix();

        assertEquals("3", outContent.toString().trim());
    }
}
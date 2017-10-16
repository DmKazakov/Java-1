package ru.spbau.mit.kazakov.Maybe;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

public class MainTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void testMain() throws IOException, getNothingException {
        File input = testFolder.newFile("input.txt");
        File output = testFolder.newFile("output.txt");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(input))) {
            bufferedWriter.write("1\n0\n-3\nj\njava\n1b\n11 22\n");
        }

        Main.main(new String[]{input.getAbsolutePath(), output.getAbsolutePath()});


        assertEquals("1\n0\n9\nnull\nnull\nnull\nnull\n", new String(Files.readAllBytes(Paths.get(output.getAbsolutePath()))));
    }

    @Test
    public void testMainEmpty() throws IOException, getNothingException {
        File input = testFolder.newFile("input.txt");
        File output = testFolder.newFile("output.txt");

        Main.main(new String[]{input.getAbsolutePath(), output.getAbsolutePath()});

        assertEquals("", new String(Files.readAllBytes(Paths.get(output.getAbsolutePath()))));
    }

}
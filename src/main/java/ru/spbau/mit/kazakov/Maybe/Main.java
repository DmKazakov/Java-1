package ru.spbau.mit.kazakov.Maybe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads numbers from input file and writes squared values to output file. If read data isn't a number writes "null" instead.
 */
public class Main {
    /**
     * Primary method.
     *
     * @param args the first argument is input file and the second one is output file
     */
    public static void main(@NotNull String[] args) throws IOException, GetNothingException {
        ArrayList<Maybe<Integer>> readData = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            while (scanner.hasNextLine()) {
                Integer value = tryParse(scanner.nextLine());
                if (value == null) {
                    readData.add(Maybe.nothing());
                } else {
                    readData.add(Maybe.just(value));
                }
            }
        }

        ArrayList<Maybe<Integer>> squaredNumber = new ArrayList<>();
        for (Maybe<Integer> maybeInt : readData) {
            squaredNumber.add(maybeInt.map(value -> value * value));
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(args[1]))) {
            for (Maybe<Integer> maybeInt : squaredNumber) {
                if (maybeInt.isPresent()) {
                    bufferedWriter.write(maybeInt.get().toString());
                } else {
                    bufferedWriter.write("null");
                }
                bufferedWriter.newLine();
            }
        }
    }

    /**
     * Parses specified String to Integer.
     *
     * @return Integer if String was a number, and null otherwise
     */
    @Nullable
    private static Integer tryParse(@NotNull String parsingString) {
        try {
            return Integer.parseInt(parsingString);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}

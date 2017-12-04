package ru.spbau.mit.kazakov.Streams;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.*;


public class SecondPartTasksTest {
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    /**
     * Writes specified string to specified file.
     *
     * @param file    specified file
     * @param content specified string
     */
    private void writeToFile(File file, String content) throws IOException {
        try (Writer fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
        }
    }

    @Test
    public void testFindQuotes() throws IOException {
        File testFile1 = testFolder.newFile("test_file1");
        writeToFile(testFile1, "Some text\nNew line text\nEOF\n");
        File testFile2 = testFolder.newFile("test_file2");
        writeToFile(testFile2, "Some line\ntext to match\nBye\n");
        File testFile3 = testFolder.newFile("test_file3");
        writeToFile(testFile3, "No text\n");

        List<String> paths = Arrays.asList(testFile1.getAbsolutePath(), testFile2.getAbsolutePath(), testFile3.getAbsolutePath());
        List<String> quote = SecondPartTasks.findQuotes(paths, "text");
        assertEquals(Arrays.asList("Some text", "New line text", "text to match", "No text"), quote);
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(0.785, SecondPartTasks.piDividedBy4(), 0.001);
    }

    @Test
    public void testFindPrinter() {
        Map<String, List<String>> scraps = new HashMap<>();
        scraps.put("Nathaniel Hawthorne", Arrays.asList("In the depths of every heart there is a tomb and a dungeon,though the lights, "
                        + "the music and revelry, above may cause us to forget their existence and the buried ones or prisoners whom they hide."
                        + "But sometimes, and oftenest at midnight, those dark receptacles are flung wide open.",
                "What a singular moment is the first one, when you have hardly begun to recollect yourself, "
                        + "after starting from midnight slumber!"
                        + "By unclosing your eyes so suddenly you seem to have surprised the personages of your dream in full convocation round your bed, "
                        + "and catch one broad glance at them before they can flit into obscurity."));
        scraps.put("O. Henry", Collections.singletonList("Night had fallen on that great and beautiful city known as Bagdad-on-the-Subway."
                + "And with the night came the enchanted glamour that belongs not to Arabia alone. In different masquerade the streets, "
                + "bazaars and walled houses of the occidental city of romance were filled with the same kind of folk that so much interested our interesting old friend, "
                + "the late Mr. H. A. Rashid. They wore clothes eleven hundred years nearer to the latest styles than H. A. saw in old Bagdad; "
                + "but they were about the same people underneath. With the eye of faith, you could have seen the Little Hunchback, "
                + "Sinbad the Sailor, Fitbad the Tailor, the Beautiful Persian, the one-eyed Calenders, Ali Baba and Forty Robbers on every block, "
                + "and the Barber and his Six Brothers, and all the old Arabian gang easily. But let us revenue to our lamb chops. Old Tom Crowley was a caliph."
                + "He had $42,000,000 in preferred stocks and bonds with solid gold edges. In these times, to be called a caliph you must have money."
                + "The old-style caliph business as conducted by Mr. Rashid is not safe. If you hold up a person nowadays in a bazaar or a Turkish bath or a side street, "
                + "and inquire into his private and personal affairs, the police court'll get you."));
        scraps.put("Jack London", Collections.singletonList("Day had broken cold and grey, exceedingly cold and grey, "
                + "when the man turned aside from the main Yukon trail and climbed the high earth- bank, "
                + "where a dim and little-travelled trail led eastward through the fat spruce timberland. It was a steep bank, and he paused for breath at the top, "
                + "excusing the act to himself by looking at his watch. It was nine o'clock."));

        assertEquals("O. Henry", SecondPartTasks.findPrinter(scraps));
    }

    @Test
    public void testCalculateGlobalOrder() {
        Map<String, Integer> order1 = new HashMap<>();
        order1.put("Darkness", 3);
        order1.put("Dreadful fate", 888);
        order1.put("Despair", 42);

        Map<String, Integer> order2 = new HashMap<>();
        order2.put("Coldness", 32);
        order2.put("Blackened", 97);
        order2.put("Despair", 32);

        Map<String, Integer> order3 = new HashMap<>();
        order3.put("Helplessness", 565);
        order3.put("Powerlessness", 7);
        order3.put("Lifeless", 9898);
        order3.put("Darkness", 9);
        order3.put("Dreadful fate", 8);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("Darkness", 12);
        expected.put("Dreadful fate", 896);
        expected.put("Despair", 74);
        expected.put("Coldness", 32);
        expected.put("Blackened", 97);
        expected.put("Helplessness", 565);
        expected.put("Powerlessness", 7);
        expected.put("Lifeless", 9898);

        Map<String, Integer> order = SecondPartTasks.calculateGlobalOrder(Arrays.asList(order1, order2, order3));
        assertEquals(expected, order);
    }
}
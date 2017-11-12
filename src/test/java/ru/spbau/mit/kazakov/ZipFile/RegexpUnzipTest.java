package ru.spbau.mit.kazakov.ZipFile;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class RegexpUnzipTest {
    private final static String ABSOLUTE_PATH_TO_RESOURCES
            = new File("src" + File.separator + "test" + File.separator + "resources").getAbsolutePath();
    @Before
    public void clearTestingFolder() throws IOException {
        File testingFolder = new File(ABSOLUTE_PATH_TO_RESOURCES + File.separator + "testing folder");
        FileUtils.cleanDirectory(testingFolder);

        File testingFiles = new File(ABSOLUTE_PATH_TO_RESOURCES + File.separator + "testing files");
        FileUtils.copyDirectory(testingFiles, testingFolder);
    }

    private ArrayList<String> getTestingFolderFileNames() {
        ArrayList<String> fileNamesInTestingFolder = new ArrayList<>();
        File testingFolder = new File(ABSOLUTE_PATH_TO_RESOURCES + File.separator + "testing folder");

        Collection<File> filesInTestingFolder = FileUtils.listFiles(testingFolder, null, true);
        for (File file : filesInTestingFolder) {
            fileNamesInTestingFolder.add(file.getName());
        }

        return fileNamesInTestingFolder;
    }

    @Test(expected = NotExistingDirectoryException.class)
    public void testUnzipMatchedFilesInPathNotExistingFileThrowsNotExistingDirectoryException() throws Exception {
        String wrongDirectory = ABSOLUTE_PATH_TO_RESOURCES + File.separator + "testing folder" + File.separator + "not existing file";
        RegexpUnzip.unzipMatchedFilesInPath(wrongDirectory, ".*");
    }

    @Test(expected = NotExistingDirectoryException.class)
    public void testUnzipMatchedFilesInPathNotDirectoryThrowsNotExistingDirectoryException() throws Exception {
        String wrongDirectory = ABSOLUTE_PATH_TO_RESOURCES + File.separator + "testing folder" + File.separator + "Some archive.zip";
        RegexpUnzip.unzipMatchedFilesInPath(wrongDirectory, ".*");
    }

    @Test
    public void testUnzipMatchedFilesInPathNoMatchings() throws Exception {
        RegexpUnzip.unzipMatchedFilesInPath(ABSOLUTE_PATH_TO_RESOURCES + File.separator + "testing folder", "");
        ArrayList<String> fileNamesInTestingFolder = getTestingFolderFileNames();
        assertEquals(5, fileNamesInTestingFolder.size());
    }

    @Test
    public void testUnzipMatchedFilesInPathOneArchiveMatchings() throws Exception {
        RegexpUnzip.unzipMatchedFilesInPath(ABSOLUTE_PATH_TO_RESOURCES + File.separator + "testing folder", ".*file.*");
        ArrayList<String> fileNamesInTestingFolder = getTestingFolderFileNames();
        assertEquals(8, fileNamesInTestingFolder.size());
        assertTrue(fileNamesInTestingFolder.contains("Some file.txt"));
        assertTrue(fileNamesInTestingFolder.contains("Another file"));
        assertTrue(fileNamesInTestingFolder.contains("Some file in a directory.txt"));
    }

    @Test
    public void testUnzipMatchedFilesInPathBothArchiveMatchings() throws Exception {
        RegexpUnzip.unzipMatchedFilesInPath(ABSOLUTE_PATH_TO_RESOURCES + File.separator + "testing folder", ".*txt.*");
        ArrayList<String> fileNamesInTestingFolder = getTestingFolderFileNames();
        assertEquals(9, fileNamesInTestingFolder.size());
        assertTrue(fileNamesInTestingFolder.contains("Some file.txt"));
        assertTrue(fileNamesInTestingFolder.contains("Regular.txt"));
        assertTrue(fileNamesInTestingFolder.contains("Some file in a directory.txt"));
        assertTrue(fileNamesInTestingFolder.contains("It isn't a folder.txt"));
    }

    @Test
    public void testUnzipMatchedFilesInPathAllMatchings() throws Exception {
        RegexpUnzip.unzipMatchedFilesInPath(ABSOLUTE_PATH_TO_RESOURCES + File.separator + "testing folder", ".*");
        ArrayList<String> fileNamesInTestingFolder = getTestingFolderFileNames();
        assertEquals(11, fileNamesInTestingFolder.size());
        assertTrue(fileNamesInTestingFolder.contains("Some file.txt"));
        assertTrue(fileNamesInTestingFolder.contains("Another file"));
        assertTrue(fileNamesInTestingFolder.contains("Regular.txt"));
        assertTrue(fileNamesInTestingFolder.contains("Some file in a directory.txt"));
        assertTrue(fileNamesInTestingFolder.contains("It isn't a folder.txt"));
        assertTrue(fileNamesInTestingFolder.contains("Unknown"));
    }
}

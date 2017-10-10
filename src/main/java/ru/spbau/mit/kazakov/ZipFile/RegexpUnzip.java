package ru.spbau.mit.kazakov.ZipFile;


import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Class for extracting files with names satisfying specified regular expression from archives located in specified directory.
 * Extracted files with their folders will be located in their archive directory.
 */
public class RegexpUnzip {
    /**
     * Finds archives in specified directory and extract files that satisfies specified regular expression from them.
     *
     * @param path   to directory
     * @param regexp to satisfy
     * @throws NotExistingDirectoryException if path doesn't specify a directory
     */
    public static void unzipMatchedFilesInPath(@NotNull String path, @NotNull String regexp) throws NotExistingDirectoryException, IOException {
        File directory = new File(path);
        if (!directory.isDirectory()) {
            throw new NotExistingDirectoryException();
        }

        ArrayList<File> zipFiles = new ArrayList<>();
        File[] filesInDirectory = directory.listFiles();
        if (filesInDirectory == null) {
            return;
        }

        for (File file : filesInDirectory) {
            if (isZipFile(file)) {
                zipFiles.add(file);
            }
        }

        for (File zipFile : zipFiles) {
            unzipMatchedFiles(zipFile.getAbsolutePath(), path, regexp);
        }
    }

    /**
     * Unzips files with names satisfying regular expression from specified archive.
     *
     * @param zipFile for files to extract
     * @param path    to archive
     * @param regexp  to satisfy
     */
    private static void unzipMatchedFiles(String zipFile, String path, String regexp) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();

            while (zipEntry != null) {
                String fileNameToExtract = zipEntry.getName();

                if (fileNameToExtract.matches(regexp) && !zipEntry.isDirectory()) {
                    File fileToExtract = new File(path + File.separator + fileNameToExtract);
                    new File(fileToExtract.getParent()).mkdirs();
                    extract(fileToExtract, zipInputStream);
                }

                zipEntry = zipInputStream.getNextEntry();
            }
        }
    }

    /**
     * Extracts a zip entry.
     *
     * @param fileToExtract  extracting file
     * @param zipInputStream zip stream to extract from
     */
    private static void extract(final File fileToExtract, final ZipInputStream zipInputStream) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileToExtract)) {
            byte[] buffer = new byte[1024];
            int readBytes;

            while ((readBytes = zipInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, readBytes);
            }
        }
    }

    /**
     * Determines whether a file is a zip file.
     *
     * @param file to check
     * @return true if file is a zip file, and false otherwise
     */
    private static boolean isZipFile(final File file) throws IOException {
        if (file.isDirectory()) {
            return false;
        }

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            long n = randomAccessFile.readInt();
            return n == 0x504B0304;
        }
    }

}

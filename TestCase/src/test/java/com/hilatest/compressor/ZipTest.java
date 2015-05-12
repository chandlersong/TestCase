package com.hilatest.compressor;

import java.io.IOException;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.junit.Test;

public class ZipTest {

    @Test
    public void testBasic() throws ArchiveException, IOException {

        ZipFile in = new ZipFile(ZIP_FILE);
        Iterable<ZipArchiveEntry> entries = in.getEntries("spring/aoparg.xml");
        for (ZipArchiveEntry entry : entries) {
            System.out.println(entry.getName());

        }

        in.close();
    }

    @Test
    public void testBasic2() throws ArchiveException, IOException {

        ZipFile in = new ZipFile(ZIP_FILE);
        Enumeration<ZipArchiveEntry> entries = in.getEntries();
        while (entries.hasMoreElements()) {
            ZipArchiveEntry entry = entries.nextElement();
            System.out.println(entry.getName());
            if (entry.isDirectory()) {
                System.out.println(entry.getName() + " is a directory");
                ZipExtraField[] fields = entry.getExtraFields();

                for (ZipExtraField field : fields) {
                    System.out.println(field);
                }
            }

        }
        in.close();
    }

    private final String ZIP_FILE = "src/main/resources/files/spring.zip";
}

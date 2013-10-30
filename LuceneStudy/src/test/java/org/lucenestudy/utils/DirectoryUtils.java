
package org.lucenestudy.utils;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class DirectoryUtils {

    public static Directory getRAMDirectory() {
        return new RAMDirectory();
    }
}


package org.lucenestudy.chapter2Index;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lucenestudy.utils.DirectoryUtils;

public class BasicTest {

    @Test
    public void testDeleteBeforeOptimize() throws IOException {
        IndexWriter writer = this.getWriter();
        Assert.assertEquals(2, writer.numDocs());

        writer.deleteDocuments(new Term("id", "1"));
        writer.commit();

        Assert.assertTrue(writer.hasDeletions());
        Assert.assertEquals(2, writer.maxDoc());
        Assert.assertEquals(1, writer.numDocs());
        writer.close();
    }

    @Test
    public void testDeleteAfterOptimize() throws IOException {
        IndexWriter writer = this.getWriter();
        Assert.assertEquals(2, writer.numDocs());

        writer.deleteDocuments(new Term("id", "1"));

        /**
         * book says "we force Lucene to merge index seg-ments, after deleting one document, by optimizing the index"
         * Maybe optimize will do other thing, but merge index file is one of it.
         */
        writer.optimize();
        writer.commit();

        Assert.assertFalse(writer.hasDeletions());
        Assert.assertEquals(1, writer.maxDoc());
        Assert.assertEquals(1, writer.numDocs());
        writer.close();
    }

    @Test
    public void testIndexWriter() throws IOException {
        IndexWriter writer = getWriter();
        Assert.assertEquals(data.size(), writer.numDocs());
        writer.close();
    }

    @Test
    public void testIndexReader() throws IOException {
        IndexReader reader = IndexReader.open(dir);
        Assert.assertEquals(data.size(), reader.maxDoc());
        Assert.assertEquals(data.size(), reader.numDocs());
        reader.close();
    }

    static class DateEntry {
        String id;

        String unindex;

        String unstored;

        String text;

    }

    ArrayList<DateEntry> data = new ArrayList<DateEntry>();

    private Directory dir;

    @Before
    public void perpareIndex() throws IOException {
        dir = DirectoryUtils.getRAMDirectory();
        perpareData();

        // add data to index
        IndexWriter writer = getWriter();

        for (DateEntry entry : data) {
            Document doc = new Document();
            doc.add(new Field("id", entry.id, Field.Store.YES, Field.Index.NOT_ANALYZED));
            doc.add(new Field("country", entry.unindex, Field.Store.YES, Field.Index.NO));
            doc.add(new Field("contents", entry.unstored, Field.Store.NO, Field.Index.ANALYZED));
            doc.add(new Field("city", entry.text, Field.Store.YES, Field.Index.ANALYZED));
            writer.addDocument(doc);
        }

        writer.close();
    }

    private void perpareData() {
        DateEntry dataentry1 = new DateEntry();
        dataentry1.id = "1";
        dataentry1.unindex = "Netherlands";
        dataentry1.unstored = "Amsterdam has lots of bridges";
        dataentry1.text = "Amsterdam";
        data.add(dataentry1);

        DateEntry dataentry2 = new DateEntry();
        dataentry2.id = "2";
        dataentry2.unindex = "Italy";
        dataentry2.unstored = "Venice has lots of canals";
        dataentry2.text = "Venice";
        data.add(dataentry2);
    }

    private IndexWriter getWriter() throws IOException {
        return new IndexWriter(dir, new WhitespaceAnalyzer(), IndexWriter.MaxFieldLength.UNLIMITED);
    }
}

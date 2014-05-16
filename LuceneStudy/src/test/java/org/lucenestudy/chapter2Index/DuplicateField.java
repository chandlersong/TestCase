package org.lucenestudy.chapter2Index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.junit.Before;
import org.junit.Test;
import org.lucenestudy.utils.DirectoryUtils;

public class DuplicateField {
    private final static Logger logger = Logger.getLogger(DuplicateField.class);

    @Test
    public void testQuery() throws CorruptIndexException, IOException {

        Query query = NumericRangeQuery.newIntRange("city", 6, 6, true, true);
        IndexSearcher is = new IndexSearcher(dir);
        TopDocs hits = is.search(query, 10);
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            logger.info(doc.get("id"));
        }
        is.close();
    }

    static class DateEntry {
        String id;

        String unindex;

        String unstored;

        List<Integer> intergerList;

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

            Iterator<Integer> iter = entry.intergerList.iterator();

            while (iter.hasNext()) {
                NumericField field = new NumericField("city", Field.Store.NO, true);
                field.setIntValue(iter.next());
                doc.add(field);
            }
            writer.addDocument(doc);
        }

        writer.close();
    }

    private void perpareData() {
        DateEntry dataentry1 = new DateEntry();
        dataentry1.id = "1";
        dataentry1.unindex = "Netherlands";
        dataentry1.unstored = "Amsterdam has lots of bridges";

        List<Integer> intList1 = new ArrayList<Integer>();
        intList1.add(1);
        intList1.add(2);
        intList1.add(3);
        dataentry1.intergerList = intList1;
        data.add(dataentry1);

        DateEntry dataentry2 = new DateEntry();
        dataentry2.id = "2";
        dataentry2.unindex = "Italy";
        dataentry2.unstored = "Venice has lots of canals";
        List<Integer> intList2 = new ArrayList<Integer>();
        intList2.add(5);
        intList2.add(6);
        intList2.add(7);
        dataentry2.intergerList = intList2;
        data.add(dataentry2);

        DateEntry dataentry3 = new DateEntry();
        dataentry3.id = "3";
        dataentry3.unindex = "Shanghi";
        dataentry3.unstored = "China has lots of bridges";

        List<Integer> intList3 = new ArrayList<Integer>();
        intList3.add(2);
        intList3.add(5);
        intList3.add(9);
        dataentry3.intergerList = intList3;
        data.add(dataentry3);
    }

    private IndexWriter getWriter() throws IOException {
        return new IndexWriter(dir, new WhitespaceAnalyzer(), IndexWriter.MaxFieldLength.UNLIMITED);
    }
}

package org.lucenestudy.chapter1;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.lucenestudy.Constant;

public class HelloWorld {

	private final static Logger logger = Logger.getLogger(HelloWorld.class);
	private IndexWriter writer;

	@Test
	public void doWork() throws Exception {

		writer = new IndexWriter(this.getIndexFolder(),
		        new StandardAnalyzer(
		                Version.LUCENE_30),
		        true,
		        IndexWriter.MaxFieldLength.UNLIMITED);

		Document doc1 = this.generateDocument("hello world", "firstFile",
		        "\\temp\\firstFile");
		Document doc2 = this.generateDocument("22222222222", "2222222222",
		        "\\111111\\222222222");

		writer.addDocument(doc1);
		writer.addDocument(doc2);

		writer.commit();

		writer.close();

	}

	public Directory getIndexFolder() throws Exception {

		File IndexDir = new File(Constant.rootIndex,
		        "Chapter1_HelloWorld");
		Directory dir = FSDirectory.open(IndexDir);
		logger.info("index folder:" + IndexDir.getAbsolutePath());
		return dir;
	}

	public Document generateDocument(String contents, String fileName,
	        String filePath) throws Exception {
		Document doc = new Document();
		doc.add(new Field("contents", contents,
		        Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field("filename", fileName,
		        Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field("fullpath", filePath,
		        Field.Store.YES, Field.Index.NOT_ANALYZED));
		return doc;
	}
}

package com.dataprocess.twitter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
 
public class IndexCreater {

    public void createIndex(String filePath, String indexPath) throws IOException{

        DataLoad dl = new DataLoad(filePath);
        ArrayList<ArrayList<String>> tableData = dl.getTwitterData();
        tableData.remove(0);
        
        List<Document> documents = new ArrayList<Document>();
        for (ArrayList<String> twitterInfo : tableData) {
            Document document = new Document();
            document.add(new TextField("twitterID", twitterInfo.get(0) + "", Store.YES));
            document.add(new TextField("twitterContent", twitterInfo.get(1), Store.YES));
            documents.add(document);
        }

        Analyzer analyzer = new StandardAnalyzer();

        // IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LUCENE_7_7_1, analyzer);
        IndexWriterConfig indexConfig = new IndexWriterConfig(analyzer);

        Path path = Paths.get(indexPath);
        Directory directory = FSDirectory.open(path);

        IndexWriter indexWriter = new IndexWriter(directory, indexConfig);

        for (Document doc : documents) {

            indexWriter.addDocument(doc);
        }

        indexWriter.close();
    }
    
    public void searchIndexText(String indexPath, String searchStr) throws Exception {

        Analyzer analyzer = new StandardAnalyzer();

        QueryParser queryParser = new QueryParser("twitterContent", analyzer);

        Query query = queryParser.parse("twitterContent:" + searchStr);

        Path path = Paths.get(indexPath);
        Directory directory = FSDirectory.open(path);

        IndexReader indexReader = DirectoryReader.open(directory);

        IndexSearcher searcher = new IndexSearcher(indexReader);

        TopDocs topDocs = searcher.search(query, 10);

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            System.out.println("= = = = = = = = = = = = = = = = = = =");

            int docId = scoreDoc.doc;

            Document doc = searcher.doc(docId);
            System.out.println("ID: " + doc.get("twitterID"));
            System.out.println("Content: " + doc.get("twitterContent"));
        }

        indexReader.close();

    }
}

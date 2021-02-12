package com.javatechie.solar.api;

import com.javatechie.solar.api.model.BookBean;
import com.javatechie.solar.api.utils.ParseBooks;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;

import java.util.List;


public class AddingDocument {
    public static void main(String args[]) throws Exception {
        long heapSize = Runtime.getRuntime().totalMemory();

        //Print the jvm heap size.
        System.out.println("Heap Size = " + heapSize);

        HttpSolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/test").build();
        solrClient.setParser(new XMLResponseParser());

        System.out.println("start");
        List<BookBean> books = ParseBooks.getBooks("books_fb2");
        solrClient.addBeans(books);

        System.out.println(books.size());
        solrClient.commit();
        System.out.println("finish");
    }
}
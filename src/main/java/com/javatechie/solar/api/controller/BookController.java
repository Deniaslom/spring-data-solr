package com.javatechie.solar.api.controller;

import com.javatechie.solar.api.model.BookBean;
import com.javatechie.solar.api.repository.BookRepository;
import com.javatechie.solar.api.utils.ParseBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookRepository repository;

//    @PostConstruct
//    public void addBooks() {
//        try {
//            List<BookBean> books = ParseBooks.getBooks("books_fb2_1");
//            repository.saveAll(books);
//            books = ParseBooks.getBooks("books_fb2_2");
//            repository.saveAll(books);
//            books = ParseBooks.getBooks("books_fb2_3");
//            repository.saveAll(books);
//            books = ParseBooks.getBooks("books_fb2_4");
//            repository.saveAll(books);
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (XMLStreamException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @GetMapping("/getALL")
    public Iterable<BookBean> getBooks() {
        return repository.findAll();
    }

    @GetMapping("/getBook/{name}")
    public BookBean getBookByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @GetMapping("/getBook/{name}")
    public BookBean getBookByName(@PathVariable String name) {
        return repository.findByCustomQuery(name);
    }

}

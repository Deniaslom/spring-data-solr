package com.javatechie.solar.api.utils;

import com.javatechie.solar.api.model.BookBean;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParseBooks {
    private static final String BOOK_TITLE = "book-title";
    private static final String GENRE = "genre";
    private static final String PUBLISHER = "publisher";
    private static final String ISBN = "isbn";
    private static final String LANG = "lang";
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final String BODY = "body";
    private static final String YEAR = "year";
    private static final String AUTHOR = "author";
    private static final String TITLE_INFO = "title-info";

    public static List<BookBean> getBooks(String path) throws ParserConfigurationException, IOException, SAXException, ParseException, XMLStreamException {
        File[] files = new File(path).listFiles();
        ArrayList<BookBean> bookBeans = new ArrayList<>();
        if (files != null) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            int id = 0;

            for (File file : files) {
                Document doc = docBuilder.parse(file.getPath());
                String encoding = getEncoding(file.getPath());

                BookBean book = new BookBean();

                book.setId(UUID.randomUUID().toString());
                book.setName(getElementsByTagName(doc, BOOK_TITLE, encoding));
                book.setGenre(getElementsByTagName(doc, GENRE, encoding));
                book.setAuthors(getAuthors(doc, encoding));
                book.setPublisher(getElementsByTagName(doc, PUBLISHER, encoding));
                book.setIsbn(getElementsByTagName(doc, ISBN, encoding));
                book.setLang(getElementsByTagName(doc, LANG, encoding));
                book.setMainContent(getMainContent(doc, encoding));
                setYear(doc, book, encoding);

                bookBeans.add(book);
                id++;
                System.out.println(id);
            }
        }
        return bookBeans;
    }

    private static String getElementsByTagName(Document doc, String element, String encoding) throws NullPointerException {
        try {
            byte[] bytes = doc.getElementsByTagName(element).item(0).getTextContent().getBytes(encoding);
            return new String(bytes, UTF_8);
        } catch (NullPointerException | UnsupportedEncodingException e) {
            return null;
        }
    }

    private static String getEncoding(String path) throws FileNotFoundException, XMLStreamException {
        return XMLInputFactory.
                newInstance().
                createXMLStreamReader(new FileReader(path)).
                getCharacterEncodingScheme();
    }

    private static String getMainContent(Document doc, String encoding) throws UnsupportedEncodingException {

        NodeList nodeList = doc.getElementsByTagName(BODY);
        StringBuffer mainContent = new StringBuffer();
        for (int i = 0; i < nodeList.getLength(); i++) {
            nodeList.item(0);
            mainContent.append(nodeList.item(0).getTextContent());
        }
        return encodingToUTF(mainContent.toString(), encoding);
    }

    private static void setYear(Document doc, BookBean book, String encoding) {
        if (getElementsByTagName(doc, YEAR, encoding) != null) {
            book.setYear(Integer.parseInt(getElementsByTagName(doc, YEAR, encoding).trim()));
        }
    }

    private static List<String> getAuthors(Document document, String encoding) throws UnsupportedEncodingException {

        List<String> authors = new ArrayList<>();
        NodeList titleList = document.getElementsByTagName(TITLE_INFO);
        for (int i = 0; i < titleList.getLength(); i++) {
            Node node = titleList.item(i);

            if ((node.getNodeType() == node.ELEMENT_NODE)) {
                NodeList nodeList = node.getChildNodes();
                for (int z = 0; z < nodeList.getLength(); z++) {
                    if (nodeList.item(z).getNodeType() == node.ELEMENT_NODE && AUTHOR.equals(nodeList.item(z).getNodeName())) {
                        StringBuffer author = new StringBuffer();
                        NodeList nodeAuthors = nodeList.item(z).getChildNodes();
                        for (int j = 0; j < nodeAuthors.getLength(); j++) {
                            if (node.getNodeType() == node.ELEMENT_NODE && "first-name".equals(nodeAuthors.item(j).getNodeName())) {
                                author.append(nodeAuthors.item(j).getTextContent().trim());
                            }
                            if (node.getNodeType() == node.ELEMENT_NODE && "last-name".equals(nodeAuthors.item(j).getNodeName())) {
                                author.append(" " + nodeAuthors.item(j).getTextContent().trim());
                            }
                        }
                        authors.add(encodingToUTF(author.toString(), encoding));
                    }
                }
            }
        }

        return authors;
    }

    private static String encodingToUTF(String str, String encoding) throws UnsupportedEncodingException {
            return new String(str.getBytes(encoding), UTF_8);
    }
}

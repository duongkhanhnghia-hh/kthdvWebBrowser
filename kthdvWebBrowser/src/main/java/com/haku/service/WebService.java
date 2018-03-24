package com.haku.service;

import com.haku.model.Book;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.naming.spi.ObjectFactory;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.sql.SQLException;
import java.util.List;

@javax.jws.WebService
public interface WebService {


    /**
     *
     * @return
     *     returns java.util.List<client.Book>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllBooks", targetNamespace = "http://webservice.pnq.com/", className = "client.GetAllBooks")
    @ResponseWrapper(localName = "getAllBooksResponse", targetNamespace = "http://webservice.pnq.com/", className = "client.GetAllBooksResponse")
    @Action(input = "http://webservice.pnq.com/WebService/getAllBooksRequest", output = "http://webservice.pnq.com/WebService/getAllBooksResponse")
    public List<Book> getAllBooks();

    /**
     *
     * @param arg0
     * @return
     *     returns client.Book
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getBookByCode", targetNamespace = "http://webservice.pnq.com/", className = "client.GetBookByCode")
    @ResponseWrapper(localName = "getBookByCodeResponse", targetNamespace = "http://webservice.pnq.com/", className = "client.GetBookByCodeResponse")
    @Action(input = "http://webservice.pnq.com/WebService/getBookByCodeRequest", output = "http://webservice.pnq.com/WebService/getBookByCodeResponse")
    public Book getBookByCode(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     *
     * @param arg0
     * @return
     *     returns client.Book
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getBookRented", targetNamespace = "http://webservice.pnq.com/", className = "client.GetBookRented")
    @ResponseWrapper(localName = "getBookRentedResponse", targetNamespace = "http://webservice.pnq.com/", className = "client.GetBookRentedResponse")
    @Action(input = "http://webservice.pnq.com/WebService/getBookRentedRequest", output = "http://webservice.pnq.com/WebService/GetBookRentedResponse")
    public List<Book> getBookRented(
            @WebParam(name = "MSV", targetNamespace = "")
                    String arg0);

    /**
     *
     * @param arg0
     * @return
     *     returns client.Book
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "searchBooks", targetNamespace = "http://webservice.pnq.com/", className = "client.SearchBooks")
    @ResponseWrapper(localName = "searchBooksResponse", targetNamespace = "http://webservice.pnq.com/", className = "client.SearchBooksResponse")
    @Action(input = "http://webservice.pnq.com/WebService/searchBooksRequest", output = "http://webservice.pnq.com/WebService/searchBooksResponse")
    public List<Book> searchBooks(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     *
     * @param arg0
     * @return
     *     returns client.Book
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rentBooks", targetNamespace = "http://webservice.pnq.com/", className = "client.RentBooks")
    @ResponseWrapper(localName = "rentBooksResponse", targetNamespace = "http://webservice.pnq.com/", className = "client.RentBooksResponse")
    @Action(input = "http://webservice.pnq.com/WebService/rentBooksRequest", output = "http://webservice.pnq.com/WebService/rentBooksResponse")
    public Boolean rentBooks(
            @WebParam(name = "bookCode", targetNamespace = "")
                    String bookCode, @WebParam(name = "MSV", targetNamespace = "") String MSV);
}
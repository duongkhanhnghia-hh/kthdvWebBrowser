package com.wtah.book;

import com.wtah.Config;
import com.wtah.model.Book;
import com.wtah.service.WebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user/")
public class BookController {
    @RequestMapping(value = "/books")
    public String getAllBooks(ModelMap modelMap){
        WebService webService = connectToService();
        List<Book> bookList = webService.getAllBooks();
        modelMap.addAttribute("bookList", bookList);
        return "home";
    }
    @RequestMapping(value = "/mybooks")
    public String getMyBooks(ModelMap modelMap){
        WebService webService = connectToService();
        List<Book> bookList = webService.getBookRented(Config.MSV);
        modelMap.addAttribute("bookList", bookList);
        return "my_book";
    }

    @RequestMapping(value = "/searchbooks_tmp")
    @ResponseBody
    public String searchBooks_tmp(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        return String.format("/user/searchbooks/code-%s/name-%s/author-%s", code, name, author);
    }

    @RequestMapping(value = "/searchbooks/code-{code}/name-{name}/author-{author}", method = RequestMethod.GET)
    public String searchBooks(@PathVariable("code") String code, @PathVariable("name") String name,
                              @PathVariable("author") String author,ModelMap modelMap){
        WebService webService = connectToService();
        Book book = null;
        List<Book> bookList = new ArrayList<>();
        Boolean isCode = code.equals("");
        Boolean isName = name.equals("");
        Boolean isAuthor = author.equals("");
        if(isCode && isName && isAuthor){
            bookList = webService.searchBooksByName(name);
        }else if (isCode && isName && !isAuthor){
            bookList = webService.searchBooksByAuthor(author);
        }
        else if(isCode && !isName && isAuthor){
            bookList = webService.searchBooksByName(name);
        }
        else if (isCode && !isName && !isAuthor){
            List<Book> bookList_tmp = webService.searchBooksByName(name);
            bookList = new ArrayList<>();
            for (int i = 0; i < bookList_tmp.size(); i++){
                book = bookList_tmp.get(i);
                if(book != null && book.getAuthor()!= null ) {
                    if (book != null && book.getAuthor().contains(author)) {
                        bookList.add(book);
                    }
                }
            }
        }
        else if(!isCode && isName && isAuthor){
            book = webService.getBookByCode(code);
            if(book.getCode() != null) {
                bookList.add(book);
            }
        }else if (!isCode && isName && !isAuthor){
            book = webService.getBookByCode(code);
            if(book != null && book.getAuthor()!= null ) {
                if (book != null && book.getAuthor().contains(author)) {
                    bookList.add(book);
                }
            }
        }
        else if(!isCode && !isName && isAuthor){
            book = webService.getBookByCode(code);
            if(book != null && book.getName()!= null ) {
                if (book.getName().contains(name)) {
                    bookList.add(book);
                }
            }
        }
        else if (!isCode && !isName && !isAuthor) {
            book = webService.getBookByCode(code);
            if(book != null && book.getAuthor()!= null && book.getName()!= null  )
                if (book.getAuthor().contains(author) && book.getName().contains(name)) {
                    bookList.add(book);
                }
        }
        modelMap.addAttribute("bookList", bookList);
        return "search_book";
    }

    @RequestMapping(value = "/rentbooks")
    @ResponseBody
    public String rentBooks(HttpServletRequest request, ModelMap modelMap){

        String bookCode = request.getParameter("bookCode");
        WebService webService = connectToService();
        Boolean rentBooks = webService.rentBooks(bookCode, Config.MSV);
        if (rentBooks){
            return String.format("/user/mybooks");
        }else {
            return "/user/stww";
        }
    }

    @RequestMapping(value = "/stww")
    public String stww(){
        return "stww";
    }

    @RequestMapping(value = "/searchByName")
    public String searchByName(){

        return "search_book";
    }

    @RequestMapping(value = "/searchBooks")
    public String searchByAuthor(){
        return "search_book";
    }

    public WebService connectToService(){
        URL url = null;
        try {
            url = new URL(Config.URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName(Config.name_space_URI, Config.localPartQname);
        Service service = Service.create(url, qname);
        QName port_name = new QName(Config.name_space_URI, Config.localPartPortname);
        WebService webService = service.getPort(port_name,WebService.class);
        return webService;
    }
}

package com.haku.book;

import com.haku.Config;
import com.haku.model.Book;
import com.haku.service.WebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping(value = "/user/")
public class BookAPI {
    @RequestMapping(value = "/books")
    public String getAllBooks(ModelMap modelMap){
        WebService webService = connectToService();
        List<Book> bookList = webService.getAllBooks();
        System.out.println(bookList);
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).getName());
        }
        modelMap.addAttribute("bookList", bookList);
        return "home";
    }
    @RequestMapping(value = "/mybooks")
    public String getMyBooks(ModelMap modelMap){

        WebService webService = connectToService();


        List<Book> bookList = webService.getBookRented(Config.MSV);
        System.out.println(bookList);
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).getName());
        }
        modelMap.addAttribute("bookList", bookList);
        return "my_book";
    }

    @RequestMapping(value = "/searchbooks")
    public String searchBooks(ModelMap modelMap){
        WebService webService = connectToService();
        List<Book> bookList = webService.searchBooks("kiến");
        System.out.println(bookList);
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i).getName());
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

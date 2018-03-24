package com.haku.getService;

import com.haku.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    List<Book> getAllBooks() throws SQLException, ClassNotFoundException;

    Book getBookByCode(String codeBook) throws SQLException, ClassNotFoundException;
}

package com.wtah.getService;

import com.wtah.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    List<Book> getAllBooks() throws SQLException, ClassNotFoundException;

    Book getBookByCode(String codeBook) throws SQLException, ClassNotFoundException;
}

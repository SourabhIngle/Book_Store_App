package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.DTO.BookDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.BookModel;

import java.util.List;

public interface BookServiceInterface  {


    boolean checkingBookAvailability(String bookName);

    ResponseDTO add(BookDTO bookDTO);

   BookModel getById(long id);

    List<BookModel> getAll();

    BookModel update(Integer id, BookDTO bookDTO);

    void delete(long id);

    BookModel changeBookQty(String token, int bookId, int quantity);

    BookModel changeBookPrice(String token, int bookId, double price);
}

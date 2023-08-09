package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.DTO.CartDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.CartModel;
import com.bridgelabz.bookstoreapp.model.UserModel;

public interface CartServiceInterface {

//    CartModel addToCart(String token,long bookId,long quality);

    CartModel addToCart(CartDTO cartDTO);

    UserModel getById(long cartId);
//
//    void removeById(long id);
//
//    void removeByToken(String token);

//    CartModel updateQty(String token, long cartId, double quantity);
}

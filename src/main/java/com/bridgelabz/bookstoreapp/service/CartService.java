package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.DTO.CartDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.exception.CustomException;
import com.bridgelabz.bookstoreapp.model.BookModel;
import com.bridgelabz.bookstoreapp.model.CartModel;
import com.bridgelabz.bookstoreapp.model.UserModel;
import com.bridgelabz.bookstoreapp.repository.BookRepository;
import com.bridgelabz.bookstoreapp.repository.CartRepository;
import com.bridgelabz.bookstoreapp.repository.UserRepository;
import com.bridgelabz.bookstoreapp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements CartServiceInterface {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTToken jwtToken;
//
//    @Override
//    public CartModel addToCart(String token, long bookId, long quality) {
//
//    }

    @Override
    public CartModel addToCart( CartDTO cartDTO) {
        UserModel userModel = userRepository.findById(cartDTO.getUser_Id()).orElseThrow(() -> new CustomException("User id " + cartDTO.getUser_Id() + " not found "));
        BookModel bookModel = bookRepository.findById(cartDTO.getBook_Id()).orElseThrow(() -> new CustomException("Book id " + cartDTO.getBook_Id() + " not found "));

        CartModel cartModel = new CartModel();
        cartModel.setUser_Id(userModel);
        cartModel.setBook_Id(bookModel);
        cartModel.setQuantity(cartDTO.getQuantity());

        long price = (long) (bookModel.getBookPrice() * bookModel.getQuantity());
        cartModel.setTotalPrice(price);
        cartRepository.save(cartModel);
        return cartModel;
    }

    @Override
    public UserModel getById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException("User id " + id + " not found "));
    }
//
//    @Override
//    public void removeById(long id) {
//       cartRepository.findById(id).orElseThrow(()-> new CustomException("Id not found"));
//        cartRepository.deleteById(id);
//    }
//
//    @Override
//    public void removeByToken(String token) {
//        long id = jwtToken.decodeToken(token);
//       removeById(id);
//    }

//    @Override
//    public CartModel updateQty(String token, long cartId, double quantity) {
//        long id = jwtToken.decodeToken(token);
//
//    }


}

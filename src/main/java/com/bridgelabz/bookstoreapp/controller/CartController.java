package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.DTO.CartDTO;
import com.bridgelabz.bookstoreapp.DTO.response.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.BookModel;
import com.bridgelabz.bookstoreapp.model.CartModel;
import com.bridgelabz.bookstoreapp.service.CartServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/cart")
public class CartController {

    @Autowired
    CartServiceInterface cartServiceInterface;


    @PostMapping(name = "/addtocart")
    public ResponseEntity<ResponseDTO> addToCart( @RequestBody CartDTO cartDTO) {
        CartModel cartModel = cartServiceInterface.addToCart(cartDTO);

        ResponseDTO responseDTO = new ResponseDTO("Book added on your cart",cartModel );
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(name = "/getbyid")
    public ResponseEntity<ResponseDTO> getById(@PathVariable long cartId){
        ResponseDTO responseDTO = new ResponseDTO("This book already in your",cartServiceInterface.getById(cartId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
//
//    @DeleteMapping(name = "/removebyid/{id}")
//    public ResponseEntity<String> removeById(@PathVariable long id){
//        cartServiceInterface.removeById(id);
//        return ResponseEntity.ok("Book remove from your cart");
//    }
//
//    @DeleteMapping(name = "/removebytoken")
//    public ResponseEntity<String> removeById(@RequestHeader String token){
//        cartServiceInterface.removeByToken(token);
//        return ResponseEntity.ok("Book remove from your cart using user Id");
//    }

//    @PatchMapping("/updatequantity") //  RequestParam => chengebookqty?bookId=1&quantity=12
//    public ResponseEntity<ResponseDTO> changeBookQuantity(@RequestHeader String token,
//                                                          @RequestParam long cartId,
//                                                          @RequestParam double quantity) {
//        CartModel cartModel = cartServiceInterface.updateQty(token, cartId, quantity);
//        ResponseDTO responseDTO = new ResponseDTO("Book quantity has been updated", bookModel);
//        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
//    }
}

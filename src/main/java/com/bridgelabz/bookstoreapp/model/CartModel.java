package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.DTO.CartDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
//OneToMany, ManyToMany, and ElementCollection within the class.
//One to One ,Many to One for diff. classes.
@Entity
@Data
@Table(name = "cart_data")
@NoArgsConstructor
//@AllArgsConstructor
public class CartModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cart_Id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user_Id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookModel book_Id;
    private Long quantity;
    private Long totalPrice;

//    public CartModel(CartDTO cartDTO) {
//        this.user_Id = cartDTO.getUser_Id();
//        this.book_Id = book_Id;
//        this.quantity = quantity;
//        this.totalPrice = totalPrice;
//    }

//    public CartModel(UserModel userModel,BookModel bookModel,CartDTO cartDTO){
//        if(this.user_Id.equals(userModel)&&this.book_Id.equals(bookModel)){
//            this.updataCart(cartDTO);
//        }

//    }
//public void updateCart(CartDTO cartDTO){
//        this.setQuantity(cartDTO.getQuantity());
//        this.setTotalPrice((long) (this.getQuantity() * this.getBook_Id().getBookPrice()));
//}
    public CartModel(UserModel user_Id, BookModel book_Id, Long quantity, Long totalPrice) {
        this.user_Id = user_Id;
        this.book_Id = book_Id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}

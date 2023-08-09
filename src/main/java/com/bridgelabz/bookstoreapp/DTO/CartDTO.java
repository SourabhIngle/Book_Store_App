package com.bridgelabz.bookstoreapp.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long user_Id;
    private Long book_Id;
    private Long quantity;
    private Long totalPrice;
}

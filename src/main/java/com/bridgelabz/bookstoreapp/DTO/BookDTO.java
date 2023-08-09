package com.bridgelabz.bookstoreapp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.awt.print.Book;
@Data
public class BookDTO {

  @NotEmpty(message = "Please write the book name")
  private String bookName;
  @NotBlank(message = "Write the Author name.")
  private String authorName;
  @NotEmpty
  @Pattern(regexp = "^([A-Za-z]{2,}\\s?)+$",message = "Enter a valid name (minimum 2 letters for each word)")
  private String description;
  @NotEmpty(message = "This filed can not be empty")
  private String logo;
  @NotNull
  @Range(min = 80,message = "Book price should be greater than 80 rupee ")
  private double bookPrice;
  @NotNull
  @Range(min = 1,message = "Quantity should be 1 ot more")
  private int quantity;


}

package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartModel,Long> {
}

package com.codegym.demo.repository;

import com.codegym.demo.dto.ProductDto;
import com.codegym.demo.dto.ShoppingCartDto;
import com.codegym.demo.entity.Product;
import com.codegym.demo.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM shoppingcarts s " +
                    "WHERE s.is_deleted = false")
    Iterable<ShoppingCart> findShoppingCartByIs_Deleted();

    @Query(nativeQuery = true,
            value = "UPDATE shoppingcarts " +
                    "SET is_deleted = true " +
                    "WHERE id = :id"
    )
    @Modifying
    @Transactional
    void softDelete(@Param("id") Integer id);
//    @Query(nativeQuery = true,
//            value = "UPDATE shoppingcart " +
//                    "SET is_deleted = true " +
//                    "WHERE id = :id"
//    )
//    @Modifying
//    @Transactional
//    void softDelete(@Param("id") Integer id);

}

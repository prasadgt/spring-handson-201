package com.shopping.shoppingCartApp.repositories;

import com.shopping.shoppingCartApp.models.Cart;
import com.shopping.shoppingCartApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    Product getProductDetailsByProductCode(final Long cartId, final String productCode);

    Product getProductDetailsByProductName(final Long cartId, final String productName);

    @Modifying(clearAutomatically = true)
    void deleteAllProductsFromCart(final Long cartId);

    @Modifying(clearAutomatically = true)
    void deleteSpecificProductFromCart(final Long cartId, final String productCode);

    @Modifying(clearAutomatically = true)
    void updateProductQuantityInCart(final Long cartId, final String productCode, final Integer productQuantity);

    List<Product> getBookDetailsByProductCategory(final Long cartId);

    List<Product> getApparalDetailsByProductCategory(final Long cartId);
}

package com.shopping.shoppingCartApp.service;

import com.shopping.shoppingCartApp.models.Cart;
import com.shopping.shoppingCartApp.models.Product;
import com.shopping.shoppingCartApp.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Optional<Cart> getAllProductDetailsFromCart(final Long cartId) {
        return cartRepository.findById(cartId);
    }

    public void removeAllProductsFromCart(final Long cartId) {
        cartRepository.deleteAllProductsFromCart(cartId);
    }

    public Product getProductDetailsByProductCode(final Long cartId, final String productCode){
        return cartRepository.getProductDetailsByProductCode(cartId, productCode);
    }

    public Product getProductDetailsByProductName(final Long cartId, final String productName) {
        return cartRepository.getProductDetailsByProductName(cartId, productName);
    }

    public void removeSpecificProductFromCart(final Long cartId, final String productCode) {
        cartRepository.deleteSpecificProductFromCart(cartId, productCode);
    }

    public List<Product> getBookDetailsByProductCategory(final Long cartId) {
        return cartRepository.getBookDetailsByProductCategory(cartId);
    }

    public List<Product> getApparalDetailsByProductCategory(final Long cartId) {
        return cartRepository.getApparalDetailsByProductCategory(cartId);
    }

    public void updateProductQuantityInCart(final Long cartId, final String productCode, final Integer productQuantity) {
        cartRepository.updateProductQuantityInCart(cartId, productCode, productQuantity);
    }
}

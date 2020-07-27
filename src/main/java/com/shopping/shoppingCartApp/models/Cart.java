package com.shopping.shoppingCartApp.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "cart")
@NamedQueries(value = {
        @NamedQuery(name = "Cart.getProductDetailsByProductCode",query = "SELECT prod FROM product prod JOIN cart crt on prod.cart.cartId=" +
                "crt.cartId where prod.cart.cartId=?1 and prod.productCode=?2"),
        @NamedQuery(name = "Cart.getProductDetailsByProductName",query = "SELECT prod FROM product prod JOIN cart crt on prod.cart.cartId=" +
                "crt.cartId where prod.cart.cartId=?1 and prod.productName=?2"),
        @NamedQuery(name = "Cart.getBookDetailsByProductCategory",query = "SELECT prod FROM book prod JOIN cart crt on prod.cart.cartId=" +
                "crt.cartId where prod.cart.cartId=?1"),
        @NamedQuery(name = "Cart.getApparalDetailsByProductCategory",query = "SELECT prod FROM apparal prod JOIN cart crt on prod.cart.cartId=" +
                "crt.cartId where prod.cart.cartId=?1"),
        @NamedQuery(name = "Cart.deleteAllProductsFromCart",query = "DELETE FROM product prod " + "WHERE prod.cart.cartId=?1"),
        @NamedQuery(name = "Cart.deleteSpecificProductFromCart",query = "DELETE FROM product prod " + "WHERE prod.cart.cartId=?1 AND prod.productCode=?2"),
        @NamedQuery(name = "Cart.updateProductQuantityInCart" ,query = "UPDATE product prod SET prod.productQuantity=?3" +
                "WHERE prod.cart.cartId=?1 AND prod.productCode=?2")
})
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;

    private String cartName;
    @Transient
    private Double totalPrice;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "cart",cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

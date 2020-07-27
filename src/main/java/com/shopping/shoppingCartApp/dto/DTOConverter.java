package com.shopping.shoppingCartApp.dto;

import com.shopping.shoppingCartApp.constants.CommonConstant;
import com.shopping.shoppingCartApp.models.Apparal;
import com.shopping.shoppingCartApp.models.Book;
import com.shopping.shoppingCartApp.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DTOConverter {

    public static List<Product> createListFromDTO(ProductDTO productDTO){
        List<Product> productList = new ArrayList<>();
        if(productDTO.getProductType().equalsIgnoreCase(CommonConstant.PRODUCT_CATEGORY_BOOK)) {
            Book book = new Book();
            book.setAuthor(productDTO.getBookAuthor());
            book.setGenre(productDTO.getBookGenre());
            book.setPublications(productDTO.getBookPublications());
            book.setProductName(productDTO.getProductName());
            book.setProductCode(productDTO.getProductCode());
            book.setProductQuantity(productDTO.getProductQuantity());
            book.setProductPrice(productDTO.getProductPrice());
            productList.add(book);
        }
        else if(productDTO.getProductType().equalsIgnoreCase(CommonConstant.PRODUCT_CATEGORY_APPARAL)){
            Apparal apparal = new Apparal();
            apparal.setBrand(productDTO.getApparalBrand());
            apparal.setDesign(productDTO.getApparalDesign());
            apparal.setType(productDTO.getApparalType());
            apparal.setProductName(productDTO.getProductName());
            apparal.setProductCode(productDTO.getProductCode());
            apparal.setProductQuantity(productDTO.getProductQuantity());
            apparal.setProductPrice(productDTO.getProductPrice());
            productList.add(apparal);
        }
        return productList;
    }
}

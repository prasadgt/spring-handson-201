package com.shopping.shoppingCartApp.dto;

public class ProductDTO {

    private int productId;
    private String productCode;
    private String productName;
    private double productPrice;
    private Integer productQuantity;
    private String productType;
    private String bookGenre;
    private String bookAuthor;
    private String bookPublications;
    private String apparalType;
    private String apparalBrand;
    private String apparalDesign;

    public ProductDTO(){}

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublications() {
        return bookPublications;
    }

    public void setBookPublications(String bookPublications) {
        this.bookPublications = bookPublications;
    }

    public String getApparalType() {
        return apparalType;
    }

    public void setApparalType(String apparalType) {
        this.apparalType = apparalType;
    }

    public String getApparalBrand() {
        return apparalBrand;
    }

    public void setApparalBrand(String apparalBrand) {
        this.apparalBrand = apparalBrand;
    }

    public String getApparalDesign() {
        return apparalDesign;
    }

    public void setApparalDesign(String apparalDesign) {
        this.apparalDesign = apparalDesign;
    }
}

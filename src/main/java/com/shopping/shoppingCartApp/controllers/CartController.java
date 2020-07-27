package com.shopping.shoppingCartApp.controllers;

import com.shopping.shoppingCartApp.constants.CommonConstant;
import com.shopping.shoppingCartApp.dto.DTOConverter;
import com.shopping.shoppingCartApp.dto.ProductDTO;
import com.shopping.shoppingCartApp.exceptions.ResourceNotFoundException;
import com.shopping.shoppingCartApp.models.Cart;
import com.shopping.shoppingCartApp.models.Product;
import com.shopping.shoppingCartApp.models.User;
import com.shopping.shoppingCartApp.service.CartService;
import com.shopping.shoppingCartApp.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cart")
@AllArgsConstructor
public class CartController {

    UserService userService;
    CartService cartService;

    private static final Logger logger = LogManager.getLogger(CartController.class);

    @PostMapping(value = "/addProducts")
    @ApiOperation(value = "add products in cart",notes = "provide whole data model object and user name",response = Product.class)
    public ResponseEntity<String> addProductsToCart(@RequestBody ProductDTO productDTO, @RequestParam("userName") final String UserName) throws Exception{
        User user = userService.getUserDetailByUserName(UserName);
        if(user == null){
            logger.error("User does not exist" + UserName);
            throw new ResourceNotFoundException(CommonConstant.USER_DOES_NOT_EXISTS);
        }

        if(productDTO != null && !(productDTO.getProductType().equalsIgnoreCase(CommonConstant.PRODUCT_CATEGORY_BOOK) || productDTO.getProductType().equalsIgnoreCase(CommonConstant.PRODUCT_CATEGORY_APPARAL))){
            logger.error("Product category not found" + productDTO.getProductType());
            throw new ResourceNotFoundException(CommonConstant.PRODUCT_CATEGORY_NOT_FOUND);
        }

        Cart cart = new Cart();
        cart.setCartId(user.getCart().getCartId());
        cart.setCartName(user.getUserName() + CommonConstant.CART_NAME);

        Product product = cartService.getProductDetailsByProductCode(cart.getCartId(),productDTO.getProductCode());
        if(product !=null){
            if (!product.getProductName().equalsIgnoreCase(productDTO.getProductName())){
                logger.error("Please provide a valid product code" + productDTO.getProductName());
                throw new ResourceNotFoundException(CommonConstant.PRODUCT_CODE_IS_INVALID);}
            cartService.updateProductQuantityInCart(cart.getCartId(),product.getProductCode(),product.getProductQuantity()+1);
        }
        else{
            List<Product> productList = DTOConverter.createListFromDTO(productDTO);
            if (productList == null || productList.isEmpty()){
                logger.error("Please provide a valid product name");
                throw new ResourceNotFoundException(CommonConstant.PRODUCT_NAME_IS_INVALID);}
            cart.setProductList(productList);
            cartService.saveCart(cart);
        }
        return new ResponseEntity(CommonConstant.PRODUCT_CREATED_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAllProductDetails")
    public ResponseEntity<Cart> getAllProductDetails(@RequestParam("userName") final String userName) throws Exception{
        User user = userService.getUserDetailByUserName(userName);
        if(user == null){
            logger.error("User does not exist" + userName);
            throw new ResourceNotFoundException(CommonConstant.USER_DOES_NOT_EXISTS);}
        Optional<Cart> cartList = cartService.getAllProductDetailsFromCart(user.getCart().getCartId());
        List<Product> productList = cartList.get().getProductList();

        if(productList.isEmpty() || productList == null){
            logger.error("Product details doesn't exists");
            throw new ResourceNotFoundException(CommonConstant.PRODUCT_DETAILS_DOES_NOT_EXISTS);}

        Double totalPrice;
        if(productList.size() == 1)
            totalPrice = productList.get(0).getProductPrice() * productList.get(0).getProductQuantity();
        else
            totalPrice = productList.stream().mapToDouble(prod -> prod.getProductPrice() * prod.getProductQuantity()).sum();

        Cart cart = cartList.get();
        cart.setTotalPrice(totalPrice);

        return new ResponseEntity(cart,HttpStatus.OK);
    }

    @GetMapping(value = "/getProductDetailsByCode")
    public ResponseEntity<Cart> getProductDetailsByCode(@RequestParam("userName") final String userName,@RequestParam("productCode") final String productCode) throws Exception{
        User user = userService.getUserDetailByUserName(userName);
        if(user == null){
            logger.error("User does not exist" + userName);
            throw new ResourceNotFoundException(CommonConstant.USER_DOES_NOT_EXISTS);}

        Product product = cartService.getProductDetailsByProductCode(user.getCart().getCartId(),productCode);
        if(product == null){
            logger.error("Product details doesn't exists");
            throw new ResourceNotFoundException(CommonConstant.PRODUCT_DETAILS_DOES_NOT_EXISTS);}

        Cart cart = new Cart();
        cart.setCartId(user.getCart().getCartId());
        cart.setCartName(user.getUserName() + CommonConstant.CART_NAME);
        cart.setProductList(Arrays.asList(product));
        cart.setTotalPrice(product.getProductQuantity() * product.getProductPrice());
        return new ResponseEntity(cart,HttpStatus.OK);
    }

    @GetMapping(value = "/getProductDetailsByName")
    public ResponseEntity<Cart> getProductDetailsByName(@RequestParam("userName") final String userName,@RequestParam("productName") final String productName) throws Exception{
        User user = userService.getUserDetailByUserName(userName);
        if(user == null){
            logger.error("User does not exist" + userName);
            throw new ResourceNotFoundException(CommonConstant.USER_DOES_NOT_EXISTS);}

        Product product = cartService.getProductDetailsByProductName(user.getCart().getCartId(),productName);
        if(product == null){
            logger.error("Product details doesn't exists");
            throw new ResourceNotFoundException(CommonConstant.PRODUCT_DETAILS_DOES_NOT_EXISTS);}

        Cart cart = new Cart();
        cart.setCartId(user.getCart().getCartId());
        cart.setCartName(user.getUserName() + CommonConstant.CART_NAME);
        cart.setProductList(Arrays.asList(product));
        cart.setTotalPrice(product.getProductQuantity() * product.getProductPrice());
        return new ResponseEntity(cart,HttpStatus.OK);
    }

    @DeleteMapping(value = "/removeAllProducts")
    public ResponseEntity<String> removeAllProductsFromCart(@RequestParam("userName") final String userName) throws Exception{
        User user = userService.getUserDetailByUserName(userName);
        if(user == null){
            logger.error("User does not exist" + userName);
            throw new ResourceNotFoundException(CommonConstant.USER_DOES_NOT_EXISTS);}

        cartService.removeAllProductsFromCart(user.getCart().getCartId());
        return new ResponseEntity(CommonConstant.PRODUCT_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/removeSpecificProducts")
    public ResponseEntity<String> removeSpecificProduct(@RequestParam("userName") final String userName,@RequestParam("productName") final String productCode) throws Exception {
        User user = userService.getUserDetailByUserName(userName);
        if(user == null){
            logger.error("User does not exist" + userName);
            throw new ResourceNotFoundException(CommonConstant.USER_DOES_NOT_EXISTS);}

        Product product = cartService.getProductDetailsByProductCode(user.getCart().getCartId(), productCode);
        if (product == null){
            logger.error("Product details doesn't exists");
            throw new ResourceNotFoundException(CommonConstant.PRODUCT_DETAILS_DOES_NOT_EXISTS);}

        cartService.removeSpecificProductFromCart(user.getCart().getCartId(), productCode);
        return new ResponseEntity(CommonConstant.PRODUCT_DELETED_SUCCESSFULLY, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/getProductDetailsByProductCategory")
    public ResponseEntity<Cart> getProductDetailsByProductCategory(@RequestParam("userName") final String userName,@RequestParam("productCategory") final String productCategory) throws Exception{
        User user = userService.getUserDetailByUserName(userName);
        if(user == null){
            logger.error("User does not exist" + userName);
            throw new ResourceNotFoundException(CommonConstant.USER_DOES_NOT_EXISTS);}

        List<Product> productList = null;
        if(productCategory != null && productCategory.equalsIgnoreCase(CommonConstant.PRODUCT_CATEGORY_BOOK)){
            productList = cartService.getBookDetailsByProductCategory(user.getCart().getCartId());
        }
        else if(productCategory != null && productCategory.equalsIgnoreCase(CommonConstant.PRODUCT_CATEGORY_APPARAL)){
            productList = cartService.getApparalDetailsByProductCategory(user.getCart().getCartId());
        }
        else {
            logger.error("Product category not found");
            throw new ResourceNotFoundException(CommonConstant.PRODUCT_CATEGORY_NOT_FOUND);
        }

        Double totalPrice;
        if(productList.size() == 1)
            totalPrice = productList.get(0).getProductPrice() * productList.get(0).getProductQuantity();
        else
            totalPrice = productList.stream().mapToDouble(prod -> prod.getProductPrice() * prod.getProductQuantity()).sum();

        Cart cart = new Cart();
        cart.setCartId(user.getCart().getCartId());
        cart.setCartName(user.getUserName() + CommonConstant.CART_NAME);
        cart.setTotalPrice(totalPrice);
        cart.setProductList(productList);
        return new ResponseEntity(cart,HttpStatus.OK);
    }

    @PutMapping(value = "/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestParam("userName") final String userName,@RequestParam("productCode") final String productCode,@RequestParam("productQuantity") final String productQuantity) throws Exception{
        User user = userService.getUserDetailByUserName(userName);
        if(user == null){
            logger.error("User does not exist" + userName);
            throw new ResourceNotFoundException(CommonConstant.USER_DOES_NOT_EXISTS);}

        if(productCode == null){
            logger.error("Please provide a valid product code" + productCode);
            throw new ResourceNotFoundException(CommonConstant.PRODUCT_CODE_IS_INVALID);}

        Product product = cartService.getProductDetailsByProductCode(user.getCart().getCartId(),productCode);
        if(product == null){
            logger.error("Product details doesn't exists");
            throw new ResourceNotFoundException(CommonConstant.PRODUCT_DETAILS_DOES_NOT_EXISTS);}

        String result = null;
        Integer prodQuantity = 0;
        Integer reqProdQuantity = Integer.parseInt(productQuantity);
        if(prodQuantity == reqProdQuantity){
            logger.error("No changes in quantity" + reqProdQuantity);
            throw new ResourceNotFoundException(CommonConstant.NO_CHANGES_IN_QUANTITY);}
        else if(reqProdQuantity <= 0)
            prodQuantity = 0;
        else
            prodQuantity = reqProdQuantity;

        if(prodQuantity != 0){
            cartService.updateProductQuantityInCart(user.getCart().getCartId(),product.getProductCode(),prodQuantity);
            result = CommonConstant.PRODUCT_UPDATED_SUCCESSFULLY;
        }
        else {
            cartService.removeSpecificProductFromCart(user.getCart().getCartId(), product.getProductCode());
            result = CommonConstant.PRODUCT_DELETED_SUCCESSFULLY;
        }
        return new ResponseEntity(result,HttpStatus.OK);
    }

}

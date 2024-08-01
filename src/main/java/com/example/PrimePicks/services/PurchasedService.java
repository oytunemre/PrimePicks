package com.example.PrimePicks.services;

import com.example.PrimePicks.models.*;
import com.example.PrimePicks.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchasedService {

    @Autowired
    private PurchasedProductsRepository purchasedRepository;

    @Autowired
    private ProductModelRepository productRepository;

    @Autowired
    private UserModelRepository userRepository;
    @Autowired
    private ShoppingCartModelRepository shoppingCartModelRepository;

    @Autowired
    HttpSession session;

    public void purchaseProduct(UserModel user, Long productId) {
        PurchasedProducts purchase = new PurchasedProducts();
        purchase.setUser(user);
        purchase.setProduct(new ProductModel());
        purchase.getProduct().setProductID(productId);
        purchase.setPurchaseDate(new Date());
        purchasedRepository.save(purchase);
    }


    public List<ProductModel> getPurchasedProducts(Long userId) {
        return purchasedRepository.findByUserUserId(userId).stream()
                .map(PurchasedProducts::getProduct)
                .collect(Collectors.toList());
    }


    @Autowired
    private SoldProductsRepository soldProductsRepository;

    public boolean processCheckout(UserModel user, ShoppingCartModel cart) {
        try {
            UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
            for (ProductModel product : cart.getProducts()) {
                PurchasedProducts purchasedProduct = new PurchasedProducts();
                purchasedProduct.setUser(user);
                purchasedProduct.setProduct(product);
                purchasedProduct.setPurchaseDate(new Date());
                purchasedRepository.save(purchasedProduct);

                UserModel seller = userRepository.findById(product.getUserId()).orElse(null);


                SoldProducts soldProduct = new SoldProducts();
                soldProduct.setSeller(seller);
                soldProduct.setBuyer(loggedInUser);
                soldProduct.setProduct(product);
                soldProduct.setSaleDate(new Date());
                soldProductsRepository.save(soldProduct);
            }


            cart.getProducts().clear();
            shoppingCartModelRepository.save(cart);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


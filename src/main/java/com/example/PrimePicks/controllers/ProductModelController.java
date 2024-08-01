package com.example.PrimePicks.controllers;

import com.example.PrimePicks.models.ProductModel;
import com.example.PrimePicks.models.ShoppingCartModel;
import com.example.PrimePicks.models.UserModel;
import com.example.PrimePicks.services.ProductModelImpl;
import com.example.PrimePicks.services.ProductModelService;
import com.example.PrimePicks.services.ShoppingCartModelServiceImpl;
import com.example.PrimePicks.services.UserModelServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductModelController {


    @Autowired
    private ProductModelService productModelService;

    @Autowired
    private ShoppingCartModelServiceImpl shoppingCartModelService;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ProductModelController.class);


    @PostMapping("/saveProduct")
    public String saveProduct(
            @RequestParam("productName") String productName,
            @RequestParam("stockStatus") int stockStatus,
            @RequestParam("brand") String brand,
            @RequestParam("color") String color,
            @RequestParam("size") String size,
            @RequestParam("weight") String weight,
            @RequestParam("category") String category,
            @RequestParam("image") MultipartFile image,
            @RequestParam("price") Double price,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/LoginPage";
        }

        if (image.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please select a file to upload.");
            return "CreateProductForm";
        }

        try {
            byte[] imageData = image.getBytes();

            ProductModel productModel = new ProductModel();
            productModel.setProductName(productName);
            productModel.setStockStatus(stockStatus);
            productModel.setBrand(brand);
            productModel.setColor(color);
            productModel.setSize(size);
            productModel.setCategory(category);
            productModel.setWeight(weight);
            productModel.setImage(imageData);
            productModel.setPrice(price);
            productModel.setUserId(loggedInUser.getUserId());
            productModelService.saveProduct(productModel);

            redirectAttributes.addFlashAttribute("message", "Product created successfully!");
            return "redirect:/userProfile";

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "There was an error uploading the file.");
            return "CreateProductForm";
        }
    }


    @GetMapping("/CreateProductForm")
    public String createProductForm(Model model,HttpSession session) {
        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        model.addAttribute("user", loggedInUser);
        model.addAttribute("product", new ProductModel());
        return "CreateProduct";
    }


    @GetMapping("/ViewProduct")
    public String viewProduct(@RequestParam("id") Long id, Model model) {
        ProductModel product = productModelService.getProductById(id);
        model.addAttribute("product", product);
        return "ProductView";
    }


    @GetMapping("/category/Electronics")
    public String showElectronics(Model model, HttpSession session) {
        List<ProductModel> electronics = productModelService.getProductsByCategory("Electronics");

        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            electronics = electronics.stream()
                    .filter(product -> product.getUserId() != null && !product.getUserId().equals(loggedInUser.getUserId()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("products", electronics);
        model.addAttribute("user", loggedInUser);

        int cartSize = 0;
        if (loggedInUser != null) {
            ShoppingCartModel cart = shoppingCartModelService.getCartByUserId(loggedInUser.getUserId());
            if (cart != null) {
                cartSize = cart.getProducts().size();
            }
        }
        model.addAttribute("cartSize", cartSize);
        return "Electronics";
    }



    @GetMapping("/category/Clothing")
    public String showClothing(Model model, HttpSession session) {
        List<ProductModel> clothing = productModelService.getProductsByCategory("Clothes");

        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            clothing = clothing.stream()
                    .filter(product -> !product.getUserId().equals(loggedInUser.getUserId()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("products", clothing);
        model.addAttribute("user", loggedInUser);
        int cartSize = 0;
        if (loggedInUser != null) {
            ShoppingCartModel cart = shoppingCartModelService.getCartByUserId(loggedInUser.getUserId());
            if (cart != null) {
                cartSize = cart.getProducts().size();
            }
        }
        model.addAttribute("cartSize", cartSize);
        return "Clothing";
    }

    @GetMapping("/category/HomeOffice")
    public String showHomeOffice(Model model, HttpSession session) {
        List<ProductModel> homeOffice = productModelService.getProductsByCategory("Office");

        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            homeOffice = homeOffice.stream()
                    .filter(product -> !product.getUserId().equals(loggedInUser.getUserId()))
                    .collect(Collectors.toList());
        }
        int cartSize = 0;
        if (loggedInUser != null) {
            ShoppingCartModel cart = shoppingCartModelService.getCartByUserId(loggedInUser.getUserId());
            if (cart != null) {
                cartSize = cart.getProducts().size();
            }
        }
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("products", homeOffice);
        model.addAttribute("user", loggedInUser);

        return "homeOffice";
    }

    @GetMapping("/category/SportsOutdoor")
    public String showSportsOutdoor(Model model, HttpSession session) {
        List<ProductModel> sportsOutdoor = productModelService.getProductsByCategory("Sports");

        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            sportsOutdoor = sportsOutdoor.stream()
                    .filter(product -> !product.getUserId().equals(loggedInUser.getUserId()))
                    .collect(Collectors.toList());
        }
        int cartSize = 0;
        if (loggedInUser != null) {
            ShoppingCartModel cart = shoppingCartModelService.getCartByUserId(loggedInUser.getUserId());
            if (cart != null) {
                cartSize = cart.getProducts().size();
            }
        }
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("products", sportsOutdoor);
        model.addAttribute("user", loggedInUser);

        return "sportsOutdoor";
    }

    @GetMapping("/category/BooksHobbies")
    public String showBooksHobbies(Model model, HttpSession session) {
        List<ProductModel> booksHobbies = productModelService.getProductsByCategory("Book");

        UserModel loggedInUser = (UserModel) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            booksHobbies = booksHobbies.stream()
                    .filter(product -> !product.getUserId().equals(loggedInUser.getUserId()))
                    .collect(Collectors.toList());
        }
        int cartSize = 0;
        if (loggedInUser != null) {
            ShoppingCartModel cart = shoppingCartModelService.getCartByUserId(loggedInUser.getUserId());
            if (cart != null) {
                cartSize = cart.getProducts().size();
            }
        }
        model.addAttribute("cartSize", cartSize);
        model.addAttribute("products", booksHobbies);
        model.addAttribute("user", loggedInUser);

        return "booksHobbies";
    }






}

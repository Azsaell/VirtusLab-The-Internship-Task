package com.virtuslab.internship;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@SpringBootApplication
@RestController
public class Shop {

    Basket basket = new Basket();
    ProductDb productDb = new ProductDb();
    ReceiptGenerator receiptGenerator = new ReceiptGenerator();
    TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();
    FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();


    public static void main(String[] args) {
        SpringApplication.run(Shop.class, args);
    }

    @GetMapping("/basketAdd")
    public Receipt addToBasket(@RequestParam(value = "product", defaultValue = "") String product){
        try {
            basket.addProduct(productDb.getProduct(product));
        }catch (NoSuchElementException e){
            return null;
        }
        Receipt receipt;
        receipt = receiptGenerator.generate(basket);
        receipt = fifteenPercentDiscount.apply(receipt);
        receipt = tenPercentDiscount.apply(receipt);
        return receipt;
    }

}
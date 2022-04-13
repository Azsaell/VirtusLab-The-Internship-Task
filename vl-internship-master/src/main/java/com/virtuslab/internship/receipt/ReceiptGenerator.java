package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        Map<Product, Integer> products = new HashMap<Product, Integer>();
        for (var product: basket.getProducts()){
            int number = 1;
            if(products.get(product)!=null){
                number = products.get(product) + 1;
            }
            products.put(product,number );
        }
        products.forEach((key, value) -> receiptEntries.add(new ReceiptEntry(key,value)));

        return new Receipt(receiptEntries);
    }
}

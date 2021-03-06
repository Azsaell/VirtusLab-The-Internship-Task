package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public class FifteenPercentDiscount {

    public static String NAME = "FifteenPercentDiscount";

    public Receipt apply(Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = receipt.totalPrice().multiply(BigDecimal.valueOf(0.85));
            var discounts = receipt.discounts();
            discounts.add(NAME);
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    private boolean shouldApply(Receipt receipt) {
        int numberOfGrainProducts = 0;
        for (var entry: receipt.entries()
             ) {
            if(entry.product().type().name().equals("GRAINS")){
                numberOfGrainProducts += entry.quantity();
                if (numberOfGrainProducts>=3){
                    return true;
                }
            }
        }
        return false;
    }
}

package org.example;

public class Discount {
    public float getDiscountPercentage(float price) {
        if (price >= 0 && price < 15000) {
            return 0;
        } else if (price >= 15000 && price <= 20000) {
            return 5;
        } else if (price > 20000 && price < 25000) {
            return 7;
        } else if (price >= 25000 && price <= 1000000) {
            return 8.5f;
        }
        return -1;
    }

    public static double getPajak(double salary){
        if (salary >= 0 && salary <= 4000000) {
            return 0;
        } else if (salary > 4000000 && salary <= 15000000) {
            return 10;
        } else if (salary > 15000000  && salary <= 40000000) {
            return 22;
        } else if (salary > 40000000 && salary <= 999999999999f) {
            return 40f;
        }
        return -1;
    }
}

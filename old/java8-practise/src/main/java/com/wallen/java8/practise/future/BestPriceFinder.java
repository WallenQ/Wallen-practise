package com.wallen.java8.practise.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * @author Wallen
 * @date 2023/2/21 18:44
 */
public class BestPriceFinder {
    List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"), new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"),
            new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"), new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"),
            new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"), new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"),
            new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"), new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"),
            new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"), new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"),
            new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"), new Shop("LetsSaveBig"),
            new Shop("BuyAll"));

    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });


    public List<String> findPrices(String product) {
        return shops.stream().map(shop -> shop.getPriceString(product)).map(Quote::parse).map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }
}

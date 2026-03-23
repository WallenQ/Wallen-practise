package com.wallen.java8.practise.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

/**
 * @author Wallen
 * @date 2023/2/8 18:55
 */
public class ShopTest {
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


    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ShopTest shopTest = new ShopTest();
        long start = System.nanoTime();
        //System.out.println(shopTest.findPrices("myPhone27S"));
        //System.out.println(shopTest.findPricesParallel("myPhone27S"));
        System.out.println(shopTest.findPricesAsync("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + "msecs");
    }

    public List<String> findPrices(String product) {
        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 并行流
     *
     * @param product
     * @return
     */
    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 异步
     *
     * @param product
     * @return
     */
    public List<String> findPricesAsync(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product),executor))
                .collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}

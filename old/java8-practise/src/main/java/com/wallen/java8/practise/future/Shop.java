package com.wallen.java8.practise.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Wallen
 * @date 2023/2/2 11:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    private String name;
    Random random = new Random();

    private final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

    /**
     * Common Thread Pool
     */
    private final ExecutorService executor = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public Shop(String name) {
        this.name = name;
        random = new Random((long) name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public Future<Double> getPriceAsync1(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        executor.submit(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception exception) {
                futurePrice.completeExceptionally(exception);
            }
        });
        return futurePrice;
    }

    public Future<Double> getPriceAsync2(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        executor.submit(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        });
        return futurePrice;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPriceString(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDiscountPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    public static void main(String[] args) {
        Shop shop = new Shop();
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite produce");
        long invocationTime = (System.nanoTime() - start) / 1_000_000L;
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        //TODO 执行其他操作

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000L;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}

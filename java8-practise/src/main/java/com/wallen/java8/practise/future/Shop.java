package com.wallen.java8.practise.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

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
public class Shop {

    public static void main(String[] args) {
        Shop shop = new Shop();
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("my favorite produce");
        long invocationTime = (System.nanoTime() - start) / 1_000_000L;
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        //TODO 执行其他操作

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n",price);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000L;
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public Future<Double> getPriceAsync(String product) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

        //Common Thread Pool
        ExecutorService executor = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

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

    private double calculatePrice(String product) {
        delay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package ru.perm.v.completablefeature_many_benchmark.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController

public class TestController {

    ExecutorService executorService = Executors.newCachedThreadPool();

    String getContext() {
        return StringUtils.repeat("-", 1000);
    }

    void sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String trivial() {
        sleep();
        return getContext();
    }

    @GetMapping("/simple")
    public CompletableFuture<String> simple() {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            return getContext();
        }, executorService);
    }

    @GetMapping("/nested")
    public CompletableFuture<String> nested() throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            return CompletableFuture.supplyAsync(() -> {
                        sleep();
                        return getContext();
                    },executorService
            );
        },executorService).get();
    }

    @GetMapping("/apply1")
    public CompletableFuture<String> apply1() throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            return getContext();
        },executorService).thenApply(s -> s);
    }

    @GetMapping("/apply3")
    public CompletableFuture<String> apply3() throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            return getContext();
        },executorService)
                .thenApply(s -> s)
                .thenApply(s -> s)
                .thenApply(s -> s);
    }
}

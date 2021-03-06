package ru.perm.v.completablefeature_many_benchmark.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.completablefeature_many_benchmark.beans.TestExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    TestExecutor testExecutor;

    public TestController(TestExecutor testExecutor) {
        this.testExecutor = testExecutor;
    }

    public ExecutorService getExecutorService() {
        return testExecutor.getExecutorService();
    }

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
        }, getExecutorService());
    }

    @GetMapping("/nested")
    public CompletableFuture<String> nested() throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> CompletableFuture.supplyAsync(() -> {
                    sleep();
                    return getContext();
                }, getExecutorService()
        ), getExecutorService()).get();
    }

    @GetMapping("/apply1")
    public CompletableFuture<String> apply1() {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            return getContext();
        }, getExecutorService()).thenApply(s -> s);
    }

    @GetMapping("/apply3")
    public CompletableFuture<String> apply3() {
        return CompletableFuture.supplyAsync(() -> {
            sleep();
            return getContext();
        }, getExecutorService())
                .thenApply(s -> s)
                .thenApply(s -> s)
                .thenApply(s -> s);
    }
}

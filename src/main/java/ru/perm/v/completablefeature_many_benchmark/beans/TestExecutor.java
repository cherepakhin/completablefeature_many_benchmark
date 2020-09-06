package ru.perm.v.completablefeature_many_benchmark.beans;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor {
    int threads;
    //    ExecutorService executorService = Executors.newFixedThreadPool(100);
    //    ExecutorService executorService = Executors.newCachedThreadPool();
    ExecutorService executorService;

    public TestExecutor(int threads) {
        this.threads = threads;
        executorService = Executors.newFixedThreadPool(threads);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }
}

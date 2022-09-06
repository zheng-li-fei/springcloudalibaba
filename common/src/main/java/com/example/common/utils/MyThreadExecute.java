package com.example.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程任务执行器
 */
@Slf4j
@Component
public class MyThreadExecute {
    /**
     * 任务队列
     */
    private final static BlockingQueue<Runnable> taskQueue = new LinkedBlockingDeque<>(127);

    /**
     * 线程池配置
     */
    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() * 2,
            Runtime.getRuntime().availableProcessors() * 4,
            1,
            TimeUnit.MINUTES,
            taskQueue,
            new ThreadFactory() {
                private final AtomicInteger atomicInteger = new AtomicInteger(0);
                @Override
                public Thread newThread(Runnable r) {
                    String name = String.format("javaShop-pool-%s", atomicInteger.getAndDecrement());
                    return new Thread(r, name);
                }
            },
            new ThreadPoolExecutor.AbortPolicy()
    );

    /**
     * 执行任务
     */
    public static void execute(Runnable task) {
        Map<String, String> previous = MDC.getCopyOfContextMap();
        threadPoolExecutor.submit(() -> {

            if (previous == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(previous);
            }
            try {
                task.run();
            } catch (Exception e) {
                log.error("任务执行出错", e);
            } finally {
                if (previous == null) {
                    MDC.clear();
                } else {
                    MDC.setContextMap(previous);
                }
            }
        });
    }

    @PreDestroy
    public void destroyThreadPool() {
        List<Runnable> runnables = new ArrayList<>();
        if (!threadPoolExecutor.isTerminated()) {
            //没有停止
            threadPoolExecutor.shutdown();
            try {
                //等待5秒
                log.info("threadPoolExecutor 线程池等待5秒后关闭");
                threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
                runnables = threadPoolExecutor.shutdownNow();
            }
        }
        log.info("threadPoolExecutor 线程池关闭,等待执行数量 {}", runnables.size());
    }

    /**
     * 执行任务
     */
    public static void executeCommon(Runnable task) {
        threadPoolExecutor.submit(task);
    }
}

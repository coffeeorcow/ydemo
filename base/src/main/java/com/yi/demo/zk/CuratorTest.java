package com.yi.demo.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class CuratorTest {

    public static void main(String[] args) {
        CuratorFramework client =
                CuratorFrameworkFactory.newClient("localhost:2181",
                        new ExponentialBackoffRetry(1000, 3));
        client.start();

        Runnable r = () -> {
            InterProcessLock lock = new InterProcessMutex(client, "/test/lock");
            try {
                if (lock.acquire(3, TimeUnit.SECONDS)) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        System.out.println(Thread.currentThread().getName() + " => Performed.");
                    } finally {
                        lock.release();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " => 未获取到锁");
                }
            } catch (Exception e) {
                System.err.println(Thread.currentThread().getName() + "获取 ZooKeeper 锁出错, 错误原因 => " + e.getCause());
            }
        };

        new Thread(r, "t-1").start();
        new Thread(r, "t-2").start();
        new Thread(r, "t-3").start();
    }

}

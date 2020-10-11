package com.yi.demo.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ZkTest {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 初始化客户端
        ZooKeeper zk = new ZooKeeper(
                "localhost:2181",
                20000,
                watchedEvent -> {
                    // 发生变更的节点路径
                    String path = watchedEvent.getPath();
                    System.out.println("path:" + path);

                    // 通知状态
                    Watcher.Event.KeeperState state = watchedEvent.getState();
                    System.out.println("KeeperState:" + state);

                    // 事件类型
                    Watcher.Event.EventType type = watchedEvent.getType();
                    System.out.println("EventType:" + type);
                }
        );

        // 添加节点
        zk.create("/test", "你好啊".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        // 查询节点
        Stat stat = new Stat();
        byte[] data = zk.getData("/test", false, stat);
        System.out.println(new String(data));
        System.out.println("length => " + stat.getDataLength());

        // 修改节点
        zk.setData("/test", "修改".getBytes(), -1);

        // 删除节点
//        zk.delete("/test", -1);

        TimeUnit.SECONDS.sleep(5);

        // 关闭客户端
        zk.close();
    }

}

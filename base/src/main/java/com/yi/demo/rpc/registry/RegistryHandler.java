package com.yi.demo.rpc.registry;

import com.google.common.collect.Maps;
import com.yi.demo.rpc.protocol.InvokerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class RegistryHandler extends ChannelInboundHandlerAdapter {

    // 所有可用服务
    public static ConcurrentHashMap<String, Object> registryMap = new ConcurrentHashMap<>();
    // 所有类
    private List<String> classNames = new ArrayList<>();

    public RegistryHandler() {
        scannerClass("com.yi.demo.rpc.provider");
        doRegistry();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("链接建立");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Object result = new Object();
        InvokerProtocol request = (InvokerProtocol) msg;
        if (registryMap.containsKey(request.getClassName())) {
            Object clz = registryMap.get(request.getClassName());
            Method method = clz.getClass().getMethod(request.getMethodName(), request.getParamTypes());
            System.out.println("方法调用 ing...");
            result = method.invoke(clz, request.getParamValues());
        } else {
            System.out.println("找不到类");
        }

        ctx.channel().writeAndFlush(result);
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void scannerClass(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File dir = new File(Objects.requireNonNull(url).getFile());
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                scannerClass(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    private void doRegistry() {
        if (classNames.size() == 0) return;

        for (String className : classNames) {
            try {
                Class<?> clz = Class.forName(className);
                Class<?> i = clz.getInterfaces()[0];
                registryMap.put(i.getName(), clz.newInstance());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}

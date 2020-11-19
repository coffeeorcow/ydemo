package com.yi.demo.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public class InvokerProtocol implements Serializable {

    private String className;
    private String methodName;
    private Class<?>[] paramTypes;
    private Object[] paramValues;

}

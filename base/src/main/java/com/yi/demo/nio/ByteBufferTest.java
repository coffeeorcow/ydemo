package com.yi.demo.nio;

import java.nio.IntBuffer;

public class ByteBufferTest {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(4);
        buffer.put(1);
        buffer.put(2);
        buffer.put(3);
        buffer.put(4);
        buffer.put(5);
        buffer.flip();
        buffer.mark();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }

}

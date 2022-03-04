package com.zbc.practise.netty;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author StormT1King
 */
@Slf4j
public class Channel {

    public static void main(String[] args) throws IOException{
        try{
            // Netty的FileChannel 输入输出流
            FileChannel channel = new FileInputStream("123.txt").getChannel();
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while(true) {
                // 从channel读取的数据写入buffer
                int len = channel.read(buffer);
                log.debug("读取到的字节数量{}", len);
                // 缓冲区无内存
                if(len == -1) {
                    break;
                }
                // 打印buffer的内存
                // 切换到读模式
                buffer.flip();
                //是否还有剩余内存
                while(buffer.hasRemaining()) {
                    byte b = buffer.get();
                    log.info("读取的字节是{}", (char)b);
                }

                buffer.clear(); // 切换为写模式
            }
        }catch(IOException e){
            log.error("buffer异常");
        }
    }

}

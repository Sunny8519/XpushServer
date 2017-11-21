package com.learning.sunny.xpushserver.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * Created by Sunny on 2017/11/21.
 */
public class TextLineDecoder implements ProtocolDecoder {
    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        //记录IoBuffer的当前位置
        int startPosition = in.position();
        //如果IoBuffer中还有bytes，返回true
        while (in.hasRemaining()) {
            //没get()一次，IoBuffer中的当前位置就会改变一次
            byte b = in.get();
            if (b == '\n') {
                //获取当前位置
                int currentPosition = in.position();
                //获取IoBuffer的总的大小
                int limit = in.limit();
                //定位到最开始的位置
                in.position(startPosition);
                //修改缓冲区大小为当前位置的大小
                in.limit(currentPosition);
                //截取IoBuffer
                IoBuffer ioBuffer = in.slice();
                byte[] dest = new byte[ioBuffer.limit()];
                //从IoBuffer中读出数据到字节数组中
                ioBuffer.get(dest);
                String str = new String(dest);
                //输出字符串
                out.write(str);
                //把位置定位到当前位置，以便一下次get()从当前位置开始
                in.position(currentPosition);
                in.limit(limit);
            }
        }
    }

    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {

    }

    @Override
    public void dispose(IoSession session) throws Exception {

    }
}

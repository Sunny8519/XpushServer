package com.learning.sunny.xpushserver.mina;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * Created by Sunny on 2017/11/21.
 */
public class TextLineEncoder implements ProtocolEncoder {

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        String msg = null;
        if (message instanceof String) {
            msg = (String) message;
        }
        if (msg != null) {
            CharsetEncoder charsetEncoder = (CharsetEncoder) session.getAttribute("encoder");
            if (charsetEncoder == null) {
                charsetEncoder = Charset.defaultCharset().newEncoder();
                session.setAttribute("encoder", charsetEncoder);
            }
            IoBuffer ioBuffer = IoBuffer.allocate(msg.length()).setAutoExpand(true);
            ioBuffer.putString(msg, charsetEncoder);
            ioBuffer.flip();
            out.write(ioBuffer);
        }
    }

    @Override
    public void dispose(IoSession session) throws Exception {

    }
}

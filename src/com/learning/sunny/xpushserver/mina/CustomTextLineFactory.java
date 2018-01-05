package com.learning.sunny.xpushserver.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * <pre>
 * author : Sunny
 * time : 2017/11/17
 * desc :
 * </pre>
 */
public class CustomTextLineFactory implements ProtocolCodecFactory {
    private final TextLineDecoder decoder;
    private final TextLineEncoder encoder;

    public CustomTextLineFactory() {
        this.decoder = new TextLineDecoder();
        this.encoder = new TextLineEncoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return this.encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return this.decoder;
    }
}

package com.learning.sunny.xpushserver.mina;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Sunny on 2017/11/21.
 */
public class MinaServer {

    public static void main(String[] args) {
        try {
            //第一步
            NioSocketAcceptor acceptor = new NioSocketAcceptor();
            //第二步
            acceptor.setHandler(new MessageManageHandler());
            //第三步
            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new CustomTextLineFactory()));

            //设置
            acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 5);

            //第四步
            acceptor.bind(new InetSocketAddress(9898));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

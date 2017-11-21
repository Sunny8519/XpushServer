package com.learning.sunny.xpushserver.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by Sunny on 2017/11/21.
 */
public class MessageManageHandler extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("sessionIdle");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = (String) message;
        System.out.println(msg);
        session.write(msg);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("messageSent");
    }
}

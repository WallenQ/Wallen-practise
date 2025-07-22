package com.wallen.practise.book1.webflux;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Author qianwenlong
 * @Date 2025/7/22 11:01
 */
@ClientEndpoint
public class SimpleTestClientEndpoint {
    @Getter
    private List<String> received = new ArrayList<>();
    private Session session;
    @Getter
    private CloseReason closeReason;
    @Getter
    private boolean closed = false;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnClose
    public void onClose(CloseReason closeReason) {
        this.closeReason = closeReason;
        this.closed = true;
    }

    @OnMessage
    public void onMessage(String message) {
        this.received.add(message);
    }

    public void sendTextAndWait(String text, long timeout) throws IOException {
        int current = received.size();
        session.getBasicRemote().sendText(text);
        //wait(() -> received.size() == current, timeout);
        long startTime = System.currentTimeMillis();
        while (received.size() == current) {
            if (System.currentTimeMillis() - startTime > timeout) {
                throw new RuntimeException("Wait for new message timed out after " + timeout + " ms");
            }
            try {
                Thread.sleep(100); // 每100毫秒检查一次
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for message", e);
            }
        }
    }

    public void closeAndWait(long timeout) throws IOException {
        if (session != null && !closed) {
            session.close();
        }
        //wait(() -> closeReason == null, timeout);
        long startTime = System.currentTimeMillis();
        while (closeReason == null) {
            if (System.currentTimeMillis() - startTime > timeout) {
                throw new RuntimeException("Wait for close reason timed out after " + timeout + " ms");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for close reason", e);
            }
        }
    }

    private void wait(Supplier<Boolean> condition, long timeout) throws InterruptedException {
        long waited = 0;
        while(condition.get() && waited < timeout){
            Thread.sleep(1);
            waited++;
        }
    }

}

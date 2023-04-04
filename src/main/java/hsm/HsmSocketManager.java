package hsm;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

@Log4j2
public class HsmSocketManager {

    private final HsmConfig config;
    private Socket socket;

    public HsmSocketManager() {
        this.config = new HsmConfig();
    }

    private Socket openConnection() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(config.getIp(), config.getPorta()), config.getTimeout());
            socket.setSoTimeout(config.getTimeout());
            log.info("Socket opened successfully on {}:{}", config.getIp(), config.getPorta());
        } catch (IOException e) {
            log.error("Failed to open socket", e);
        }
        return socket;
    }

    public Socket getSocket() {
        if (socket == null || socket.isClosed()) {
            socket = openConnection();
        }
        return socket;
    }

    public void close() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                socket = null;
                log.debug("Socket closed successfully");
            }
        } catch (IOException e) {
            log.error("Error closing socket: {}", e.getMessage());
        }
    }

    public boolean validateSocket() {
        return socket != null && !socket.isClosed();
    }
}
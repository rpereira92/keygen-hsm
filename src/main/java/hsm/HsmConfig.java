package hsm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Log4j2
public class HsmConfig {
    private final String ip;
    private final int porta;
    private final int timeout;
    private final int minPoolSessao;
    private final int maxPoolSessaoInativo;
    private final int maxPoolSessao;
    private final int maxFalhaSessao;
    private final int getSessionTimeout;

    public HsmConfig() {
        Properties properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("hsm.properties")) {
            properties.load(inputStream);

            ip = properties.getProperty("hsm.ip");
            porta = Integer.parseInt(properties.getProperty("hsm.porta"));
            timeout = Integer.parseInt(properties.getProperty("hsm.timeout"));
            minPoolSessao = Integer.parseInt(properties.getProperty("hsm.minPoolSessao"));
            maxPoolSessaoInativo = Integer.parseInt(properties.getProperty("hsm.maxPoolSessaoInativo"));
            maxPoolSessao = Integer.parseInt(properties.getProperty("hsm.maxPoolSessao"));
            maxFalhaSessao = Integer.parseInt(properties.getProperty("hsm.maxFalhaSessao"));
            getSessionTimeout = Integer.parseInt(properties.getProperty("hsm.getSessionTimeout"));
        } catch (IOException e) {
            log.error("Failed to load HSM configuration file", e);
            throw new RuntimeException("Failed to load HSM configuration file", e);
        }
    }
}

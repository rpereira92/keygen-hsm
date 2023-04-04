package hsm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
@Setter
public class HsmConfig {
    Properties properties;

    private String ip;
    private int porta;
    private int timeout;
    private int minPoolSessao;
    private int maxPoolSessaoInativo;
    private int maxPoolSessao;
    private int maxFalhaSessao;
    private int getSessionTimeout;


    public HsmConfig() {
        this.ip = ConfigProperties.getProperty("hsm.ip");
        this.porta = Integer.parseInt(ConfigProperties.getProperty("hsm.porta"));
        this.timeout = Integer.parseInt(ConfigProperties.getProperty("hsm.timeout"));
        this.minPoolSessao = Integer.parseInt(ConfigProperties.getProperty("hsm.minPoolSessao"));
        this.maxPoolSessaoInativo = Integer.parseInt(ConfigProperties.getProperty("hsm.maxPoolSessaoInativo"));
        this.maxPoolSessao = Integer.parseInt(ConfigProperties.getProperty("hsm.maxPoolSessao"));
        this.maxFalhaSessao = Integer.parseInt(ConfigProperties.getProperty("hsm.maxFalhaSessao"));
        this.getSessionTimeout = Integer.parseInt(ConfigProperties.getProperty("hsm.getSessionTimeout"));
    }

    static class ConfigProperties {
        private static final String PROPERTIES_FILE = "config.properties";
        private static final Properties properties = new Properties();

        {
            try {
                FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String getProperty(String key) {
            return properties.getProperty(key);
        }
    }
}

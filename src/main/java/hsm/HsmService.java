package hsm;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HsmService implements HSMCommands{

    Properties props;


    public HsmService() {
        try (InputStream is = new FileInputStream("hsm.properties")) {
            props.load(is);
        } catch (IOException e) {

        }
    }


    @Override
    public void connect() {

    }

    @Override
    public String generateRandomKey(EncryptionType type) {
        return null;
    }

    @Override
    public boolean testConnection() {
        return false;
    }

    @Override
    public void closeSocket() {

    }

    @Override
    public String command2GenerateAComponent(KeyType keyType) {
        return null;
    }
}

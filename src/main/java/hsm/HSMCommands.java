package hsm;

public interface HSMCommands {

    void connect();
    String generateRandomKey(EncryptionType type);
    boolean testConnection();
    void closeSocket();
    String command2GenerateAComponent(KeyType keyType);

}

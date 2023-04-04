package service;

import hsm.EncryptionType;
import hsm.HsmConfig;

import java.util.Map;
import java.util.Properties;

public class HsmService {

    Properties properties;
    HsmConfig hsmConfig;
    public HsmService() {
        loadHSMConfiguration();
    }

    private void loadHSMConfiguration(){

    }


    private void createConnection() {

    }

    private void closeConnection() {

    }

    public Map<String, String> generateComponentKey(EncryptionType type) {
        return null;
    }

    public static String getHsmInformations(){
        String versao = "Versao";
        return versao;
    }


}

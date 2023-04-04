package service;

import hsm.EncryptionType;
import hsm.HSMEndpointThales;
import hsm.HsmConfig;
import hsm.HsmSocketManager;

import java.util.Map;
import java.util.Properties;

public class HsmService {

    HSMEndpointThales hsmEndpointThales;

    public HsmService() {
        HSMEndpointThales hsmEndpointThales = new HSMEndpointThales();
    }

    public Map<String, String> generateComponentKey(EncryptionType type) {
        return null;
    }

    public String getHsmInformations(){
        return "";
        //TODO:
    }


}

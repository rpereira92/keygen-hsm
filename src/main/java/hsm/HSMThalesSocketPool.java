package hsm;

import lombok.extern.log4j.Log4j2;
import javax.annotation.PostConstruct;

@Log4j2
public class HSMThalesSocketPool extends HSMPool<PooledSocket> {
     HsmConfig hsmConfig;

     @PostConstruct
     public void init() throws Exception {
          carregarPropriedades();
     }

     @Override
     public PooledSocket create() throws Exception {

          return new PooledSocket(pool, hsmConfig.getIp(), hsmConfig.getPorta(), hsmConfig.getTimeout(), hsmConfig.getMaxFalhaSessao());
     }

     @Override
     public void carregarPropriedades(){
          this.hsmConfig = new HsmConfig();
     }
}

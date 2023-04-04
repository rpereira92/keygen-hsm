package hsm;

import lombok.extern.log4j.Log4j2;
import javax.annotation.PostConstruct;

@Log4j2
public class HSMThalesSocketPool extends HSMPool<PooledSocket> {
     HsmConfig hsmConfig;

     @PostConstruct
     public void init(){
          try {
               init(TipoHSM.HSMTHALES);
               carregarPropriedades();
          } catch (Exception e) {
               e.printStackTrace();
          }

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

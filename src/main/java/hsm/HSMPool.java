
package hsm;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.*;

import java.io.Closeable;
import java.util.NoSuchElementException;

@Log4j2
public abstract class HSMPool<T extends PooledHSMObject> extends BasePooledObjectFactory<T> implements Closeable{

     protected TipoHSM tipoHSM;
     protected long timeoutOnGetSession = Long.MAX_VALUE;
     protected int minSessionIdle;
     protected int maxSessionIdle;
     protected int maxSessionTotal;
     protected boolean isLIFO = false;
     protected boolean testWhileIdle = false;
     protected boolean logAbandoned;
     protected boolean removeAbandonedOnBorrow;
     protected boolean removeAbandonedOnMaintenance;
     protected GenericObjectPool<T> pool;


     /**
      * Obrigatorio para inicialização do pool. Deve ser chamado no contrutor ou no PosContructor
      * Enum tipo do HSM em uso.
      * @throws Exception
      */
     public void init() throws Exception {

          this.tipoHSM = TipoHSM.HSMTHALES;

          log.info("{}Pool - Iniciando pool de sockets...", tipoHSM);

          carregarPropriedades();

          if (!testePropriedades()) {
               throw new RuntimeException("Falha na inicializacao do " + this.tipoHSM.name() + "Pool");
          }

          this.pool = new GenericObjectPool<T>(this, carregarPoolConfig(), carregarAbandonedConfig());

          pool.preparePool();

          log.info("{}Pool - Pool de sockets iniciado com sucesso", tipoHSM);

          logState();
     }

     /**
      * Retorna a instancia do pool interno ao HSMPool
      * 
      * @return GenericObjectPool do tipo Pooled na declaração
      * 
      */
     public final GenericObjectPool<T> getPool() {

          return this.pool;
     }

     /**
      * Método que carrega as propriedades do arquivo de Propriedades. Deve chamar a implementação carregarPropriedades(IHSM hsm) da classe abstrata.
      */
     public abstract void carregarPropriedades();

     /**
      * Carrega as configurações do apache-commons-pool2
      * 
      * @return GenericObjectPoolConfig gerado com as configurações importadas no carregamento das propriedades.
      */
     public GenericObjectPoolConfig<T> carregarPoolConfig() {

          GenericObjectPoolConfig<T> config = new GenericObjectPoolConfig<T>();

          config.setMinIdle(this.minSessionIdle);
          config.setMaxIdle(this.maxSessionIdle);
          config.setMaxTotal(this.maxSessionTotal);
          config.setLifo(this.isLIFO);
          config.setTestWhileIdle(this.testWhileIdle);
          config.setEvictionPolicy(new DefaultEvictionPolicy<T>());

          return config;
     }

     /**
      * Carrega as configurações de verificação de conexões abandonadas.
      * 
      * 
      * @return AbandonedConfig gerado com as configurações importadas no carregamento das propriedades.
      */
     public AbandonedConfig carregarAbandonedConfig() {

          AbandonedConfig config = new AbandonedConfig();

          config.setLogAbandoned(this.logAbandoned);
          config.setRemoveAbandonedOnBorrow(this.removeAbandonedOnBorrow);
          config.setRemoveAbandonedOnMaintenance(this.removeAbandonedOnMaintenance);

          return config;

     }

     /**
      * A imlpementação desse método deve retornar uma instãncia do tipo PooledHSMObject que está sendo gerenciado no pool.
      */
     @Override
     public abstract T create() throws Exception;

     @Override
     public PooledObject<T> wrap(T obj) {

          return new DefaultPooledObject<T>(obj);
     }

     /**
      * 
      * @return Retorna um objeto disponível do pool.
      * 
      * @throws NoSuchElementException
      * caso não hajam mais objetos disponíveis no pool ou o tempo de espera exceda o atributo timeoutOnGetSession.
      */
     public T get() throws Exception {

          return pool.borrowObject(timeoutOnGetSession);
     }

     /**
      * Loga o estado atual do pool.
      */
     public void logState() {

          log.info("{}Pool - Pool State:"
                    + "\nQtdIddle:     [{}]"
                    + "\nQtdActive:    [{}]"
                    + "\nUsoTotal:     [{}]"
                    + "\nQtdDestruida: [{}]", tipoHSM, pool.getNumIdle(), pool.getNumActive(), pool.getBorrowedCount(), pool.getDestroyedCount());

     }

     /**
      * Limpa e fecha o HSLPool.
      */
     @Override
     public void close() {

          pool.clear();
          pool.close();
     }



     public enum TipoHSM {

          HSMTHALES
     }

     /**
      * 
      * Testa as propriedades carregadas antes da criação dos objetos do pool. Pode ser usada para validar a importação correta das propriedades.
      * 
      * Retorna true por padrão, deve ser reimplementado em caso de existência de regras de validação.
      * 
      * @return true caso seja validado corretamente, false em caso de falha.
      */
     public boolean testePropriedades() {

          return true;
     }


}

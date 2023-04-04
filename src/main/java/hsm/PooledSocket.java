
package hsm;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.pool2.ObjectPool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.Callable;

@Log4j2
@Getter
@Setter
public class PooledSocket implements PooledHSMObject{

     private String id;

     private Socket socket;

     private ObjectPool<PooledSocket> pool;

     private int failsCount = 0;

     private int maxFails = 0;

     private boolean markToRefactor = false;

     private Callable<Boolean> statusTest = null;

     public PooledSocket(ObjectPool<PooledSocket> pool, String ip, int porta, int timeout, int maxFails) throws IOException{

          this.id = UUID.randomUUID().toString();

          this.pool = pool;
          this.maxFails = maxFails;
          this.socket = new Socket();
          this.socket.setSoTimeout(timeout);
          this.socket.setSoLinger(false, timeout);
          this.socket.setReuseAddress(true);
          this.socket.connect(new InetSocketAddress(ip, porta));

     }

     @Override
     public void close() {
          try {
               pool.invalidateObject(this);
          } catch (Exception e) {
               log.error("Falha ao fechar sessao do HSMThales");
          }
     }

     /**
      * Oferece um proxy do getOutputStream do socket associado.
      * 
      * @return OutputStream do socket interno ao PooledSocket
      * @throws IOException
      */
     public OutputStream getOutputStream() throws IOException {

          try {

               return this.socket.getOutputStream();

          } catch (IOException e) {
               log.error("Falha ao obter outputStream do socket", e);
               throw e;
          }
     }

     /**
      * Oferece um proxy do getInputStream do socket associado.
      * 
      * @return InputStream do socket interno ao PooledSocket
      * @throws IOException
      */
     public InputStream getInputStream() throws IOException {

          try {
               
               return this.socket.getInputStream();
          } catch (IOException e) {
               log.error("Falha ao obter inputStream do socket", e);
               throw e;
          }
     }


}

package hsm;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@Log4j2
public class HSMEndpointThales{

     @Getter
     private HSMThalesSocketPool pool;

     public HSMEndpointThales(){
          try {
               pool.init();
          } catch (Exception e) {
               e.printStackTrace();
          }
     }


     public String enviarMensagem(String mensagemOut) {
          StringBuilder mensagemRetorno = new StringBuilder();
          try (PooledSocket newSocket = pool.get()) {
               int byteLido;

               String numeroHeader = gerarNumeroAleatorio();
               mensagemOut = numeroHeader + mensagemOut;
               mensagemOut = "" + 0 + ((char) mensagemOut.length()) + mensagemOut;

               log.debug("Mensagem enviada: '{}'", mensagemOut);

               PrintWriter writer = new PrintWriter(new OutputStreamWriter(newSocket.getOutputStream(), StandardCharsets.ISO_8859_1));
               InputStreamReader inputStreamReader = new InputStreamReader(newSocket.getInputStream(), StandardCharsets.ISO_8859_1);

               while (inputStreamReader.ready()) {
                    inputStreamReader.read();
               }

               writer.write(mensagemOut);
               writer.flush();

               while (true) {
                    byteLido = inputStreamReader.read();
                    if (byteLido != -1) {
                         mensagemRetorno.append((char) byteLido);
                    }

                    if (!inputStreamReader.ready()) {
                         break;
                    }
               }

               log.debug("Mensagem recebida: '{}'", mensagemRetorno.toString());

               if (!mensagemRetorno.toString().substring(2, 6).equals(numeroHeader)) {
                    throw new IOException("Header numérico não condiz com o enviado");
               }
          } catch (IOException e) {
               log.error(e.getMessage(), e);
          } catch (Throwable e) {
               log.error(e.getMessage(), e);
          }

          return mensagemRetorno.toString();
     }

     public boolean testarConexao() {

          String resultado = enviarMensagem("NO00");

          if (resultado.length() >= 10) {

               if (!resultado.substring(6, 8).equals("NP")) {
                    log.error("Header esperado 'NP', recebido " + resultado.substring(6, 8));
                    return false;
               }

               int codRetorno = Integer.parseInt(resultado.substring(8, 10));

               if (codRetorno == 0) {
                    return true;
               } else {
                    log.warn("Retorno HSM: {} - {}", codRetorno, HSMThalesResponse.obter(codRetorno));
               }
          }
          return false;
     }

     private static String formataLogHeader(String comandoThales, String resultado) {

          return "Header esperado ' " + comandoThales + " ', recebido " + resultado;
     }

     private static String formataLogRetornoHSM(int codRetorno) {

          return "Retorno HSM: " + codRetorno + " - " + HSMThalesResponse.obter(codRetorno);
     }

     public String gerarNumeroAleatorio() {

          Random random = new Random();
          byte bytes[] = new byte[20];
          random.nextBytes(bytes);
          int numeroHeaderFormatado = random.nextInt(9999);
          return String.format("%04d", numeroHeaderFormatado);
     }

}
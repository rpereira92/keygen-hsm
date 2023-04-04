package hsm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HSMThalesResponse {
     
     SUCESSO(0),
     
     FALHA_NA_VERIFICACAO(1),
     
     TAMANHO_DE_CHAVE_INVALIDO(2),
     
     TAMANHO_DO_TIPO_DE_CHAVE_INVALIDO(5),
     
     ERRO_DE_PARIDADE_DE_CHAVE(10),
     
     DESTINATION_KEY_PARITY_ERROR_OR_KEY_ALL_ZEROS(11),
     
     PIN_ENCRIPTADO_PELA_LMK_INVALIDO(14),
     
     ERRO_DE_FORMATO(15),
     
     HSM_NAO_AUTORIZADO_SEGURANCA(17),
     
     PINBLOCK_NAO_CONTEM_VALORES_VALIDOS(20),
     
     NUMERO_DA_CONTA_INVALIDO(22),
     
     TAMANHO_DO_PIN_INVALIDO(24),
     
     ID_ESQUEMA_INVALIDO(26),
     
     TIPO_DE_CHAVE_INVALIDO(28),
     
     ERRO_INTERNO_DE_HARDWARE_OU_SOFTWARE(41),
     
     ALGORTIMO_NAO_LICENCIADO(47),
     
     HEADER_INVALIDO(51),
     
     COMANDO_NAO_LICENCIADO(67),
     
     COMMAND_HAS_BEEN_DISABLED(68),
     
     PIN_BLOCK_FORMAT_HAS_BEEN_DISABLED(69),
     
     TAMANHO_DO_DADO_INVALIDO(80),
     
     ERRO_NAO_MAPEADO(999)
     
     ;
          
     private int codigo;
     
     public static HSMThalesResponse obter(int codigo){
          for (HSMThalesResponse e : HSMThalesResponse.values()) {
           if(e.getCodigo() == codigo) {
                return e;
           }
          }
          return ERRO_NAO_MAPEADO;
    }
     
     
}

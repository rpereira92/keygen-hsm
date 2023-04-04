package hsm;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ParametrosPool implements Serializable {

     /**
      *
      */
     private static final long serialVersionUID = 661404633915631220L;

     private int numMaxPool;

     private int numInstanciasOdin;
}

package hsm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class HSMThalesKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4514900376780604542L;
	
	private String key;
	
	private String kcv;
	
}

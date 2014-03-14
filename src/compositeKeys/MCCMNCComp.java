package compositeKeys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class MCCMNCComp implements Serializable{
	@Column(name="MCC")
	private int mcc;
	@Column(name="MNC")
	private int mnc;
	
	public int getMcc() {
		return mcc;
	}
	
	public int getMnc() {
		return mnc;
	}
	
	public MCCMNCComp(){
		
	}

	public MCCMNCComp(int mcc, int mnc){
		this.mcc = mcc;
		this.mnc = mnc;
	}
}

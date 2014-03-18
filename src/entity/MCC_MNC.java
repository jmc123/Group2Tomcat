package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import compositeKeys.MCCMNCComp;

@NamedQueries( {
	@NamedQuery(name = "MCC_MNC.findByMCCANDMNC",
				query = "SELECT o FROM MCC_MNC o WHERE o.id=:id"),
})

@Entity
public class MCC_MNC implements DatasetEntity{
	@EmbeddedId
	@Column(name="MCC_MNC_ID")
	private MCCMNCComp id;
	@Column(name="Country")
	private String country;
	@Column(name="Operator")
	private String operator;
	@OneToMany(mappedBy="mcc_mnc")
	private List<CallFailure> callFailures;

	public int getMcc() {
		return id.getMcc();
	}

	public int getMnc() {
		return id.getMnc();
	}

	public String getOperator() {
		return operator;
	}

	public MCC_MNC(){
		
	}
	
	public MCC_MNC(int mcc, int mnc, String country, String operator){
		super();
		this.id = new MCCMNCComp(mcc, mnc);
		this.country = country;
		this.operator = operator;
	}

	@Override
	public Object getPrimaryKey() {
		return id;
	}
}

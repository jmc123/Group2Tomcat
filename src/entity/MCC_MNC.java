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
public class MCC_MNC {
	@EmbeddedId
	@Column(name="MCC_MNC_ID")
	private MCCMNCComp id;
	@Column(name="Country")
	private String country;
	@Column(name="Operator")
	private String operator;
	@OneToMany(mappedBy="mcc_mnc")
	private List<ErrorEvent> errorEvents;

	public int getMcc() {
		return id.getMcc();
	}

	public void setMcc(int mcc) {
		this.id.setMcc(mcc);
	}

	public int getMnc() {
		return id.getMnc();
	}

	public void setMnc(int mnc) {
		this.id.setMnc(mnc);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public List<ErrorEvent> getErrorEvents() {
		return errorEvents;
	}

	public void setErrorEvents(List<ErrorEvent> errorEvents) {
		this.errorEvents = errorEvents;
	}

	public MCC_MNC(){
		
	}
	
	public MCC_MNC(int mcc, int mnc, String country, String operator){
		super();
		this.id = new MCCMNCComp(mcc, mnc);
		this.country = country;
		this.operator = operator;
	}
	
	public void print() {
		if(operator == null)
			System.out.println("No MCC_MNC found!");
		else
			System.out.println("MCC: " + id.getMcc() +
							   "\tMNC: " + id.getMnc() +
							   "\nCountry: " + country +
							   "\tOperator: " + operator);
	}
}

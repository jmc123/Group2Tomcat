package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InvalidCallFailure implements DatasetEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	public int id;
	@Column(name="Date")
	public Date date;
	@Column(name="Event_ID")
	public int eventId;
	@Column(name="Failure_Class")
	public String failureClass;
	@Column(name="UE_Type")
	public int ueType;
	@Column(name="Market")
	public int market;
	@Column(name="Operator")
	public int operator;
	@Column(name="Cell_ID")
	public int cellId;
	@Column(name="Duration")
	public int duration;
	@Column(name="Cause_Code")
	public String causeCode;
	@Column(name="NE_Version")
	public String neVersion;
	@Column(name="IMSI")
	public long imsi;
	@Column(name="HIER3_ID")
	public long hier3_id;
	@Column(name="HIER32_ID")
	public long hier32_id;
	@Column(name="HIER321_ID")
	public long hier321_id;
	
	public InvalidCallFailure(){
		
	}
	
	public InvalidCallFailure(Date date, int eventId, String failureClass, int ueType, int market, int operator, int cellId, int duration,
						String causeCode, String neVersion, long imsi, long hier3_id, long hier32_id, long hier321_id){
		super();
		this.date = date;
		this.eventId = eventId;
		this.failureClass = failureClass;
		this.ueType = ueType;
		this.market = market;
		this.operator = operator;
		this.cellId = cellId;
		this.duration = duration;
		this.causeCode = causeCode;
		this.neVersion = neVersion;
		this.imsi = imsi;
		this.hier3_id = hier3_id;
		this.hier32_id = hier32_id;
		this.hier321_id = hier321_id;
	}

	@Override
	public Object getPrimaryKey() {
		return id;
	}
}

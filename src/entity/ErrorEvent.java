package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@NamedQueries( {
//	@NamedQuery(name = "ErrorEvent.findAll", query = "SELECT o FROM ErrorEvent o"),
//	@NamedQuery(name = "ErrorEvent.findErrorEventByEventId", query = "SELECT o FROM ErrorEvent o WHERE o.eventId=:eventId"),
//	@NamedQuery(name = "ErrorEvent.findEventIdAndCauseCodeByIMSI", query = "SELECT o.eventId, o.causeCode FROM ErrorEvent o WHERE o.imsi=:imsi"),
//	@NamedQuery(name = "ErrorEvent.findEventIdAndCauseCodeByIMSIGroupByEventId", query = "SELECT o.eventId, o.causeCode FROM ErrorEvent o WHERE o.imsi=:imsi GROUP BY o.eventId"),
//	@NamedQuery(name = "ErrorEvent.findEventIdAndCauseCodeByIMSIGroupByCauseCode", query = "SELECT o.eventId, o.causeCode FROM ErrorEvent o WHERE o.imsi=:imsi GROUP BY o.causeCode"),
//	@NamedQuery(name = "ErrorEvent.findCauseCodeAndCountry", query = "SELECT o.causeCode, s.country FROM ErrorEvent o, MCC_MNC s WHERE o.market=s.mcc"),
//	@NamedQuery(name = "ErrorEvent.findCauseCodeAndCountryGroupByCountry", query = "SELECT o.causeCode, s.country FROM ErrorEvent o, MCC_MNC s WHERE o.market=s.mcc GROUP BY s.country, o.causeCode"),
//})

@Entity
public class ErrorEvent {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="Date")
	private Date date;
	@ManyToOne(optional=false)
	@JoinColumn(name="EventID", nullable=false, updatable=false)
	private EventCause eventId;
	@ManyToOne(optional=false)
	@JoinColumn(name="Failure_Class", nullable=false, updatable=false)
	private FailureClass failureClass;
	@ManyToOne(optional=false)
	@JoinColumn(name="UE_Type", nullable=false, updatable=false)
	private UEType ueType;
	@ManyToOne(optional=false)
	@JoinColumn(name="Market", nullable=false, updatable=false)
	private MCC_MNC market;
	@ManyToOne(optional=false)
	@JoinColumn(name="Operator", nullable=false, updatable=false)
	private MCC_MNC operator;
	@Column(name="Cell_ID")
	private int cellId;
	@Column(name="Duration")
	private int duration;
	@ManyToOne(optional=false)
	@JoinColumn(name="Cause_Code", nullable=false, updatable=false)
	private EventCause causeCode;
	@Column(name="NE_Version")
	private String neVersion;
	@Column(name="IMSI")
	private long imsi;
	@Column(name="HIER3_ID")
	private long hier3_id;
	@Column(name="HIER32_ID")
	private long hier32_id;
	@Column(name="HIER321_ID")
	private long hier321_id;
	
	public ErrorEvent(){
			
	}
	
	public ErrorEvent(Date date, EventCause eventId, FailureClass failureClass, UEType ueType, MCC_MNC market, MCC_MNC operator, int cellId,
						int duration, EventCause causeCode, String neVersion, long imsi, long hier3_id, long hier32_id, long hier321_id){
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EventCause getEventId() {
		return eventId;
	}

	public void setEventId(EventCause eventId) {
		this.eventId = eventId;
	}

	public FailureClass getFailureClass() {
		return failureClass;
	}

	public void setFailureClass(FailureClass failureClass) {
		this.failureClass = failureClass;
	}

	public UEType getUeType() {
		return ueType;
	}

	public void setUeType(UEType ueType) {
		this.ueType = ueType;
	}

	public MCC_MNC getMarket() {
		return market;
	}

	public void setMarket(MCC_MNC market) {
		this.market = market;
	}

	public MCC_MNC getOperator() {
		return operator;
	}

	public void setOperator(MCC_MNC operator) {
		this.operator = operator;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public EventCause getCauseCode() {
		return causeCode;
	}

	public void setCauseCode(EventCause causeCode) {
		this.causeCode = causeCode;
	}

	public String getNeVersion() {
		return neVersion;
	}

	public void setNeVersion(String neVersion) {
		this.neVersion = neVersion;
	}

	public long getImsi() {
		return imsi;
	}

	public void setImsi(long imsi) {
		this.imsi = imsi;
	}

	public long getHier3_id() {
		return hier3_id;
	}

	public void setHier3_id(long hier3_id) {
		this.hier3_id = hier3_id;
	}

	public long getHier32_id() {
		return hier32_id;
	}

	public void setHier32_id(long hier32_id) {
		this.hier32_id = hier32_id;
	}

	public long getHier321_id() {
		return hier321_id;
	}

	public void setHier321_id(long hier321_id) {
		this.hier321_id = hier321_id;
	}

	public void print(){
		if(neVersion == null)
			System.out.println("No ErrorEvent found!");
		else{
			System.out.println("Date: " + date + "\tEvent ID: " + eventId.getEventId() + "\t\tFailure Class: " + failureClass.getFailureClass() + 
							   "\nUE Type: " + ueType.getTac() + "\t\tMarket: " + market.getMcc() + "\t\tOperator: " + operator.getMnc() + 
							   "\nCell ID: " + cellId + "\t\t\tDuration: " + duration + "\t\tCause Code: " + causeCode.getCauseCode() + 
							   "\nNE Version: " + neVersion + "\t\t\tIMSI: " + imsi + "\tHIER3_ID: " + hier3_id + 
							   "\nHIER32_ID: " + hier32_id + "\tHIER321_ID: " + hier321_id);
		}
	}
}

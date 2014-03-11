package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries( {
	@NamedQuery(name = "ErrorEvent.EventCauseByIMSI",
				query = "SELECT o.eventCause FROM ErrorEvent o WHERE o.imsi=:imsi GROUP BY o.eventCause"),
	@NamedQuery(name = "ErrorEvent.NumOfFailuresAndDuration",
				query= "SELECT COUNT(o), SUM(o.duration) FROM ErrorEvent o WHERE o.imsi=:imsi and o.date>=:fromDate and o.date<=:toDate"),
	@NamedQuery(name = "ErrorEvent.UniqueEventCauseAndOccurancesByModel",
				query = "SELECT o.eventCause, COUNT(o) FROM ErrorEvent o JOIN o.ueType d WHERE d.model=:model GROUP BY o.eventCause"),
})


@Entity
public class ErrorEvent implements DatasetEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="Date")
	private Date date;
	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name = "Event_ID", referencedColumnName = "a_Event_ID"),
		@JoinColumn(name = "Cause_Code", referencedColumnName = "b_Cause_Code") })
	private EventCause eventCause;
	@ManyToOne(optional=false)
	@JoinColumn(name="Failure_Class")
	private FailureClass failureClass;
	@ManyToOne(optional=false)
	@JoinColumn(name="UE_Type")
	private UEType ueType;
	@ManyToOne(optional=false)
	@JoinColumns({
		@JoinColumn(name = "Market", referencedColumnName = "MCC"),
		@JoinColumn(name = "Operator", referencedColumnName = "MNC") })
	private MCC_MNC mcc_mnc;
	@Column(name="Cell_ID")
	private int cellId;
	@Column(name="Duration")
	private int duration;
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

	public ErrorEvent(Date date, EventCause eventCause, FailureClass failureClass, UEType ueType, MCC_MNC mcc_mnc, int cellId,
			int duration, String neVersion, long imsi, long hier3_id, long hier32_id, long hier321_id){
		super();
		this.date = date;
		this.eventCause = eventCause;
		this.failureClass = failureClass;
		this.ueType = ueType;
		this.mcc_mnc = mcc_mnc;
		this.cellId = cellId;
		this.duration = duration;
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
		return eventCause;
	}

	public void setEventId(EventCause eventId) {
		this.eventCause = eventId;
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
		return mcc_mnc;
	}

	public void setMarket(MCC_MNC market) {
		this.mcc_mnc = market;
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
			System.out.println("Date: " + date + "\tEvent ID: " + eventCause.getEventId() + "\t\tFailure Class: " + failureClass.getFailureClass() + 
					"\nUE Type: " + ueType.getTac() + "\t\tMarket: " + mcc_mnc.getMcc() + //"\t\tOperator: " + operator.getMnc() + 
					"\nCell ID: " + cellId + "\t\t\tDuration: " + duration + //"\t\tCause Code: " + causeCode.getCauseCode() + 
					"\nNE Version: " + neVersion + "\t\t\tIMSI: " + imsi + "\tHIER3_ID: " + hier3_id + 
					"\nHIER32_ID: " + hier32_id + "\tHIER321_ID: " + hier321_id);
		}
	}

	@Override
	public Object getPrimaryKey() {
		return id;
	}
}

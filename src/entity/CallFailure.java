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
	@NamedQuery(name = "CallFailure.EventCauseByIMSI",
				query = "SELECT o.eventCause FROM CallFailure o WHERE o.imsi=:imsi GROUP BY o.eventCause"),
	@NamedQuery(name = "CallFailure.NumOfFailuresAndDuration",
				query= "SELECT o.imsi, COUNT(o.imsi), SUM(o.duration) FROM CallFailure o WHERE o.date>=:fromDate and o.date<=:toDate GROUP BY o.imsi"),
	@NamedQuery(name = "CallFailure.NumOfFailures",
				query= "SELECT COUNT(o), SUM(o.duration) FROM CallFailure o WHERE o.imsi=:imsi and o.date>=:fromDate and o.date<=:toDate"),
	@NamedQuery(name = "CallFailure.UniqueEventCauseAndOccurancesByModel",
				query = "SELECT o.eventCause, COUNT(o) FROM CallFailure o JOIN o.ueType d WHERE d.model=:model GROUP BY o.eventCause"),
	@NamedQuery(name = "CallFailure.ListIMSIFail",
				query= "SELECT o.imsi FROM CallFailure o WHERE o.date>=:fromDate and o.date<=:toDate GROUP BY o.imsi"),
	@NamedQuery(name = "CallFailure.FailuresByModelOverTime",
				query = "SELECT COUNT(o) FROM CallFailure o JOIN o.ueType d WHERE d.model=:model AND o.date>=:fromDate AND o.date<=:toDate"),		
})


@Entity
public class CallFailure implements DatasetEntity{
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

	public CallFailure(){

	}

	public CallFailure(Date date, EventCause eventCause, FailureClass failureClass, UEType ueType, MCC_MNC mcc_mnc, int cellId,
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

	@Override
	public Object getPrimaryKey() {
		return id;
	}
}

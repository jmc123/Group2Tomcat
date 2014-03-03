package compositeKeys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class EventCauseComp implements Serializable {
	@Column(name="Cause_Code")
	private int causeCode;
	@Column(name="Event_ID")
	private int eventId;
	
	public int getCauseCode() {
		return causeCode;
	}
	public void setCauseCode(int causeCode) {
		this.causeCode = causeCode;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public EventCauseComp(){
		
	}
	
	public EventCauseComp(int eventId, int causeCode){
		super();
		this.eventId = eventId;
		this.causeCode = causeCode;
	}
}

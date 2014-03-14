package compositeKeys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class EventCauseComp implements Serializable {
	@Column(name="a_Event_ID")
	public int a_eventId;
	@Column(name="b_Cause_Code")
	public int b_causeCode;
	
	public int getCauseCode() {
		return b_causeCode;
	}

	public int getEventId() {
		return a_eventId;
	}
	
	public EventCauseComp(){
		
	}
	
	public EventCauseComp(int eventId, int causeCode){
		super();
		this.a_eventId = eventId;
		this.b_causeCode = causeCode;
	}
}

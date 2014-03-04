package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import compositeKeys.EventCauseComp;

@NamedQueries( {
	@NamedQuery(name = "EventCause.findByEventIdAndCauseCode",
				query = "SELECT o FROM EventCause o WHERE o.id=:id"),
})

@Entity
public class EventCause {
	@EmbeddedId
	@Column(name="Event-Cause_ID")
	private EventCauseComp id;
	@Column(name="Description")
	private String desc;
	@OneToMany(mappedBy="eventCause")
	private List<ErrorEvent> errorEvents;

	public int getEventId() {
		return id.getEventId();
	}

	public void setEventId(int eventId) {
		this.id.setEventId(eventId);
	}
	
	public int getCauseCode() {
		return id.getCauseCode();
	}

	public void setCauseCode(int causeCode) {
		this.id.setCauseCode(causeCode);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public List<ErrorEvent> getErrorEvents() {
		return errorEvents;
	}

	public void setErrorEvents(List<ErrorEvent> errorEvents) {
		this.errorEvents = errorEvents;
	}

	public EventCause(){
		
	}
	
	public EventCause(int causeCode, int eventId, String desc){
		super();
		this.id = new EventCauseComp(eventId, causeCode);
		this.desc = desc;
	}

	public void print() {
		if(desc == null)
			System.out.println("No EventCause found!");
		else{
			System.out.println("EventID: " + id.getEventId() + "\tCause Code: " + id.getCauseCode() +
							   "\nDescription: " + desc);
		}
	}
}


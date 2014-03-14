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
public class EventCause implements DatasetEntity{
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

	
	public int getCauseCode() {
		return id.getCauseCode();
	}


	public String getDesc() {
		return desc;
	}



	public EventCause(){
		
	}
	
	public EventCause(int causeCode, int eventId, String desc){
		super();
		this.id = new EventCauseComp(eventId, causeCode);
		this.desc = desc;
	}

	@Override
	public Object getPrimaryKey() {
		return id;
	}
}
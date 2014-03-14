package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FailureClass implements DatasetEntity{
	@Id
	@Column(name="Failure_Class")
	private int failureClass;
	@Column(name="Description")
	private String desc;
	@OneToMany(mappedBy="failureClass")
	private List<CallFailure> callFailures;

	public int getFailureClass() {
		return failureClass;
	}


	public String getDesc() {
		return desc;
	}
	
	public FailureClass(){
		
	}
	
	public FailureClass(int failureClass, String desc){
		this.failureClass = failureClass;
		this.desc = desc;
	}

	@Override
	public Object getPrimaryKey() {
		return failureClass;
	}
}

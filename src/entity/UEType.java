package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//@NamedQueries( {
//	@NamedQuery(name = "UEType.findAll", query = "SELECT o FROM UEType o"),
//	@NamedQuery(name = "UEType.findUETypeByTAC", query = "SELECT o FROM UEType o WHERE o.tac=:tac"),
//})

@Entity
public class UEType implements DatasetEntity{
	@Id
	@Column(name="TAC")
	private int tac;
	@Column(name="Marketing_Name")
	private String mName;
	@Column(name="Manufacturer")
	private String manu;
	@Column(name="Access_Capability")
	private String access;
	@Column(name="Model")
	private String model;
	@Column(name="Vendor_Name")
	private String vName;
	@Column(name="UE_Type")
	private String ueType;
	@Column(name="OS")
	private String os;
	@Column(name="Input_Mode")
	private String inputMode;
	@OneToMany(mappedBy="ueType")
	private List<ErrorEvent> errorEvents;

	public int getTac() {
		return tac;
	}

	public String getModel() {
		return model;
	}

	public UEType(){
		
	}
	
	public UEType(int tac, String mName, String manu, String access, String model,
				  String vName, String ueType, String os, String inputMode){
		super();
		this.tac = tac;
		this.mName = mName;
		this.manu = manu;
		this.access = access;
		this.model = model;
		this.vName = vName;
		this.ueType = ueType;
		this.os = os;
		this.inputMode = inputMode;
	}

	@Override
	public Object getPrimaryKey() {
		return tac;
	}
}

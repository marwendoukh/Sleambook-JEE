package persistance;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class School {
	
	@Id
	private Integer id;
	private String label;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	

}

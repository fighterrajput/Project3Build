package in.co.rays.project_3.dto;

public class DeveloperDTO extends BaseDTO {
	
	private String name;
	private String tech;
	private long projectNumber;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	public long getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(long projectNumber) {
		this.projectNumber = projectNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	

}

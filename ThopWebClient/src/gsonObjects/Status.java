package gsonObjects;

public class Status {

	private Integer statusId;
	private String statusName;
	private String statusDescription;

	public Status(Integer statusId, String statusName, String statusDescription) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
		this.statusDescription = statusDescription;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

}

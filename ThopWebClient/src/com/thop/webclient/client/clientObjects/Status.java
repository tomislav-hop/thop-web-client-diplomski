package com.thop.webclient.client.clientObjects;

import java.io.Serializable;
import java.util.List;

public class Status implements Serializable {

	public Status() {
		super();
	}

	private static final long serialVersionUID = 1869858221877194614L;
	private Integer statusId;
	private String statusName;
	private String statusDescription;

	public Status(Integer statusId, String statusName, String statusDescription) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
		this.statusDescription = statusDescription;
	}

	public int getStatusId(List<Status> statusList, String selectedStatus) {
		for (Status s : statusList) {
			if (s.getStatusName().equalsIgnoreCase(selectedStatus)) {
				return s.getStatusId();
			}
		}
		return -1;
	}

	public String getStatusName(List<Status> statusList, int idStatus) {
		for (Status s : statusList) {
			if (s.getStatusId() == idStatus) {
				return s.getStatusName();
			}
		}
		return null;
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

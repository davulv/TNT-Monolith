package com.emc.ps.appmod.tnt.domain.publications;

import java.io.Serializable;
import java.util.Date;

public class Tutorial implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7010282695947453045L;

	private Integer id;
	
	private int userId;

	private Date maxDeliveryDate;
	
	private Date endDate;

	private String name;
	
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getMaxDeliveryDate() {
		return maxDeliveryDate;
	}

	public void setMaxDeliveryDate(Date maxDeliveryDate) {
		this.maxDeliveryDate = maxDeliveryDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

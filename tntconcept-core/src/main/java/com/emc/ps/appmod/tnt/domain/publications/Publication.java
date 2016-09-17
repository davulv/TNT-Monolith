package com.emc.ps.appmod.tnt.domain.publications;

import java.util.Date;

public class Publication {

	private Integer id;

	private String name;

	private Date magazineDeliveryDate;

	private Date magazinePublicationDate;

	private Date ownPublicationDate;

	private boolean accepted;

	private Integer ownerId;

	private Integer departmentId;

	private Date insertDate;

	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getMagazineDeliveryDate() {
		return magazineDeliveryDate;
	}

	public void setMagazineDeliveryDate(Date magazineDeliveryDate) {
		this.magazineDeliveryDate = magazineDeliveryDate;
	}

	public Date getMagazinePublicationDate() {
		return magazinePublicationDate;
	}

	public void setMagazinePublicationDate(Date magazinePublicationDate) {
		this.magazinePublicationDate = magazinePublicationDate;
	}

	public Date getOwnPublicationDate() {
		return ownPublicationDate;
	}

	public void setOwnPublicationDate(Date ownPublicationDate) {
		this.ownPublicationDate = ownPublicationDate;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	//private Magazine magazine;

}
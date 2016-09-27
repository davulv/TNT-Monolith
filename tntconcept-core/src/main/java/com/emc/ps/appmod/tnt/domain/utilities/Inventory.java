package com.emc.ps.appmod.tnt.domain.utilities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Inventory implements Serializable {

	private static final long serialVersionUID = -8913970246003697175L;
	
	private Integer id;

	
	private Date buyDate;

	private Boolean toRent;

	private boolean amortizable;

	private BigDecimal cost;

	private String serialNumber;

	private String provider;

	private String trademark;

	private String model;

	private String speed;

	private String storage;

	private String ram;

	private String location;

	private String description;

	private String type;

	private Integer assignedTo;

	private Integer ownerId;

	private Integer departmentId;

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Boolean getToRent() {
		return toRent;
	}

	public void setToRent(Boolean toRent) {
		this.toRent = toRent;
	}

	public boolean isAmortizable() {
		return amortizable;
	}

	public void setAmortizable(boolean amortizable) {
		this.amortizable = amortizable;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInventoryType() {
		return type;
	}

	public void setInventoryType(String type) {
		
		this.type = type;
	}

	public Integer getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Integer assignedTo) {
		this.assignedTo = assignedTo;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", buyDate=" + buyDate + ", toRent="
				+ toRent + ", amortizable=" + amortizable + ", cost=" + cost
				+ ", serialNumber=" + serialNumber + ", provider=" + provider
				+ ", trademark=" + trademark + ", model=" + model + ", speed="
				+ speed + ", storage=" + storage + ", ram=" + ram
				+ ", location=" + location + ", description=" + description
				+ ", type=" + type + ", assignedTo=" + assignedTo
				+ ", ownerId=" + ownerId + ", departmentId=" + departmentId
				+ "]";
	}
	

}

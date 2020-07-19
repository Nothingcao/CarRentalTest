package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarSchedule {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private Long carNo;
	private String scheduledDate;
	public Long getCarNo() {
		return carNo;
	}
	public void setCarNo(Long carNo) {
		this.carNo = carNo;
	}
	public String getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(String scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

}

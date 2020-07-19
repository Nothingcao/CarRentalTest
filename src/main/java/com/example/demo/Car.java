package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {
	
	@Id
	private Long carNo;
	
	private String brandName;
	public Long getCarNo() {
		return carNo;
	}
	public void setCarNo(Long carNo) {
		this.carNo = carNo;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	

}

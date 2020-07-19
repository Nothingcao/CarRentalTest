package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service

public class CarService {

	@Resource
	private CarRepository carRepository;
	
	@Resource
	private ScheduleRepository scheduleRepository;


	public Iterable<Car> getAll() {
		return carRepository.findAll();
	}
	
	public Car findCarByCarNo(Long carNo) {
		return carRepository.findById(carNo).isPresent()?carRepository.findById(carNo).get():null;
	}
	
	public Iterable<Car> getCarByBrandName(String brandName){
		return carRepository.findCarByBrandName(brandName);
	}
	
	public Iterable<CarSchedule> findScheduleByCarNo(Long carNo){
		return scheduleRepository.findScheduleByCarNo(carNo);
	}

	@Transactional
	public void bookCarSchedule(List<CarSchedule> carScheduleList) {
		for(CarSchedule carSchedule:carScheduleList) {
			scheduleRepository.save(carSchedule);
		}
		

	}

}

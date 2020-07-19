package com.example.demo;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/car")

public class RentalController {

	@Resource

	private CarService carService;

	@RequestMapping("/bookCar/carNo/{carNo}/date/{date}")

	public boolean bookCar(@PathVariable("carNo") Long carNo,@PathVariable("date") String date) {

		Iterable<CarSchedule> scheduledCarDate=carService.findScheduleByCarNo(carNo);
		List<String> dateList=Arrays.asList(date.split(","));
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String currentDate=df.format(new Date());
        List<CarSchedule> carScheduleList=new ArrayList<CarSchedule>();
		for(String d:dateList) {
			CarSchedule carSchedule=new CarSchedule();
			carSchedule.setCarNo(carNo);
			carSchedule.setScheduledDate(d);
			if(d.compareTo(currentDate)<0) {
				return false;//不能小于当前日期
			}
			while(scheduledCarDate.iterator().hasNext()) {
				String temp=scheduledCarDate.iterator().next().getScheduledDate();
				if(d.equals(temp)){//传过来的日期同数据库中日期比较，相同则表示已被预订，返回错误
					return false;
				}
			}
			carScheduleList.add(carSchedule);
		}
		//插入日期表
		carService.bookCarSchedule(carScheduleList);
		return true;

	}



	@RequestMapping("/getAllCars")

	public Iterable<Car> getAll() {
		return carService.getAll();

	}
	
	@RequestMapping("/carSchedule/{carNo}")
	public Iterable<CarSchedule> getCarScheduleByCarNo(@PathVariable("carNo") Long carNo) {
		return carService.findScheduleByCarNo(carNo);

	}

}
package com.example.demo;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Car car=carService.findCarByCarNo(carNo);
		if(car==null) {
			return false;//if carNo doesn't exist,return false
		}
		Iterable<CarSchedule> scheduledCarDate=carService.findScheduleByCarNo(carNo);
		List<String> dateList=Arrays.asList(date.split(","));//consult with front end, use "," as split code
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        String currentDate=df.format(new Date());
        List<CarSchedule> carScheduleList=new ArrayList<CarSchedule>();
		for(String d:dateList) {
			if(!isDateFormat(d)) {
				//if the string is not date format,return false
				return false;
			}
			CarSchedule carSchedule=new CarSchedule();
			carSchedule.setCarNo(carNo);
			carSchedule.setScheduledDate(d);
			if(d.compareTo(currentDate)<0) {
				//should greater than current date
				return false;
			}
			while(scheduledCarDate.iterator().hasNext()) {
				String temp=scheduledCarDate.iterator().next().getScheduledDate();
				//if the param already exists in the database,then it means the date has been booked,return false
				if(d.equals(temp)){
					return false;
				}
			}
			carScheduleList.add(carSchedule);
		}
		//insert into the carschedule table with each date
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
	
	//method to identify whether the string is date format or not
    private  boolean isDateFormat(String mes){
        String format = "([0-9]{4})(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(mes);
        if (matcher.matches()) {
            pattern = Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");
            matcher = pattern.matcher(mes);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m-1, 1);
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    
    }

}
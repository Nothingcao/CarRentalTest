package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<CarSchedule,Long> {
	@Query(value="select c.id,c.car_no,c.scheduled_date from car_schedule c where c.car_no=?1",nativeQuery=true)
	Iterable<CarSchedule> findScheduleByCarNo(Long carNo);

}

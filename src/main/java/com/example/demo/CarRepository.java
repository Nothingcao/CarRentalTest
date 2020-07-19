package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car,Long> {
	
	@Query(value="select c.car_no,c.brand_name from car c where c.brand_name=?1",nativeQuery=true)
	Iterable<Car> findCarByBrandName(String brandName);
}

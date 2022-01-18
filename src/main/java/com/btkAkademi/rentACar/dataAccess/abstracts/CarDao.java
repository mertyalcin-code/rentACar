package com.btkAkademi.rentACar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.btkAkademi.rentACar.entities.concretes.Car;

public interface CarDao extends JpaRepository<Car, Integer> {

	List<Car> findAllByBrandId(int brandId, Pageable pagable);

	List<Car> findAllByColorId(int colorId, Pageable pagable);
	
	List<Car> findAllBySegmentId(int segmentId);
	
	@Query(value = "select cars.id as rental_id,\r\n"
			+ "	rentals.return_date\r\n"
			+ "from cars\r\n"
			+ "left join car_maintenances on cars.id = car_maintenances.car_id and car_maintenances.maintenance_end is null\r\n"
			+ "left join rentals on cars.id = rentals.car_id and (rentals.return_date is null or rentals.return_date>NOW())\r\n"
			+ "where car_maintenances.id is null and rentals.id is null and cars.segment_id =?1	and cars.city_id =?2 limit 1",nativeQuery = true)
	List<Integer> findAvailableCarBySegment(Integer segmentId,Integer cityId);
}

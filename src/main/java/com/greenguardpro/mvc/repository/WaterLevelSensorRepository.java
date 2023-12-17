package com.greenguardpro.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenguardpro.mvc.model.WaterLevelSensor;

@Repository
public interface WaterLevelSensorRepository extends JpaRepository<WaterLevelSensor, Long>{

}

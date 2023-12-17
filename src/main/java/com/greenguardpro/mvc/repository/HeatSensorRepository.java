package com.greenguardpro.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenguardpro.mvc.model.HeatSensor;

@Repository
public interface HeatSensorRepository extends JpaRepository<HeatSensor, Long>{

}

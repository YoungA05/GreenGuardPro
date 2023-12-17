package com.greenguardpro.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenguardpro.mvc.model.HumiditySensor;

@Repository
public interface HumiditySensorRepository extends JpaRepository<HumiditySensor, Long>{

}

package com.ibs.training.ExpediaProject.repository;

import com.ibs.training.ExpediaProject.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<FlightEntity,Long> {
}

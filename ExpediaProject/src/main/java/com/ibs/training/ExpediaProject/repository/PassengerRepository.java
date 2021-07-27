package com.ibs.training.ExpediaProject.repository;

import com.ibs.training.ExpediaProject.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}

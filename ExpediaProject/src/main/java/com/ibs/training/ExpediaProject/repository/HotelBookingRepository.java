package com.ibs.training.ExpediaProject.repository;

import com.ibs.training.ExpediaProject.entity.HotelBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelBookingRepository extends JpaRepository<HotelBookingEntity,String> {
}

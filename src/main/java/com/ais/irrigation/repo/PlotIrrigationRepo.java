package com.ais.irrigation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ais.irrigation.model.PlotIrrigationEntity;

@Repository
public interface PlotIrrigationRepo extends JpaRepository<PlotIrrigationEntity, Long> {

	PlotIrrigationEntity findByPlot_Id(Long plotId);

	@Query("SELECT pi FROM PlotIrrigationEntity pi WHERE pi.status = com.ais.irrigation.model.Status.IDLE AND pi.nextIrrigationTime <= :currentTime")
	List<PlotIrrigationEntity> findAllPlotIrrigationToCreateSlots(@Param("currentTime") Long currentTime);

}

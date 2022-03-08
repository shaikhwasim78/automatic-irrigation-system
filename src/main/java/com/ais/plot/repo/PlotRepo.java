package com.ais.plot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ais.plot.model.PlotEntity;

@Repository
public interface PlotRepo extends JpaRepository<PlotEntity, Long> {

	List<PlotEntity> findAllByIsDeletedFalse();

	PlotEntity findByIdAndIsDeletedFalse(Long id);

}

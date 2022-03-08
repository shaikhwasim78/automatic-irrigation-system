package com.ais.slot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ais.slot.model.SlotEntity;

@Repository
public interface SlotRepo extends JpaRepository<SlotEntity, Long> {

	@Query("SELECT s FROM SlotEntity s WHERE s.status = com.ais.slot.model.Status.PENDING OR s.status = com.ais.slot.model.Status.IN_PROGRESS")
	List<SlotEntity> findAllSlotForUpdate();
}

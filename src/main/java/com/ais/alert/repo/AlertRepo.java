package com.ais.alert.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ais.alert.model.AlertEntity;

@Repository
public interface AlertRepo extends JpaRepository<AlertEntity, Long> {

}

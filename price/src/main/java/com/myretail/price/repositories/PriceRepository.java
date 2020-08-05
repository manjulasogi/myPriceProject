package com.myretail.price.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myretail.price.entities.PriceData;

@Repository
public interface PriceRepository extends JpaRepository <PriceData,Integer> {
	public PriceData findByPid(int pid);

}

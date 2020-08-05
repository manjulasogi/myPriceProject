package com.myretail.price.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.myretail.price.entities.PriceData;
import com.myretail.price.repositories.PriceRepository;


@Service
public class PriceService {
			
	@Autowired
	private PriceRepository priceRepository;
	
	
	@Transactional
	public List<PriceData> getAllPrices(){
		return priceRepository.findAll();
	}
	
	@Transactional
	public PriceData getProductPrice(int pid ){
		return priceRepository.findByPid(pid);
	}
	
	@Transactional
	public void addProductPrice(PriceData priceData){
		priceRepository.save(priceData);
	}
	
	@Transactional
	public void updateProductPrice(PriceData priceData){
		priceRepository.save(priceData);
	}
	
	@Transactional
	public PriceData findByPid(int pid) {
		return priceRepository.findByPid(pid);
	}
	
	
}

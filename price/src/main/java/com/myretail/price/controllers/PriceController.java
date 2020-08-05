package com.myretail.price.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.price.entities.PriceData;
import com.myretail.price.exceptions.DuplicateProductIdException;
import com.myretail.price.security.AuthenticationRequest;
import com.myretail.price.security.AuthenticationResponse;
import com.myretail.price.security.MyUserDetailsService;
import com.myretail.price.service.PriceService;
import com.myretail.price.util.JwtUtil;


@CrossOrigin
@RestController
public class PriceController {
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	MyUserDetailsService userDetailsService;

	@CrossOrigin
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	@CrossOrigin
	@RequestMapping("/prices")
	public List<PriceData> getAllPrices(){
		return priceService.getAllPrices();
	}
	
	@CrossOrigin
	@RequestMapping("/product/{productId}/price")
	public PriceData getProductPrices(@PathVariable ("productId") int id ){
		return priceService.getProductPrice(id);
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST,  value="/product/{productId}/price")
	public void addProductPrice(@RequestBody PriceData price, @PathVariable ("productId") int id){
						
		PriceData foundPriceData = priceService.getProductPrice(id);
		
		if(foundPriceData == null) {
			priceService.addProductPrice(price);
		}
		else {
			throw new DuplicateProductIdException();
		}
	
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.PUT,  value="/product/{productId}/price")
	public void updateProductPrice(@RequestBody PriceData price, @PathVariable ("productId") int id){
		PriceData  pdata = priceService.getProductPrice(id);
		if(pdata == null) {
			priceService.updateProductPrice(price);
		}
		else
		{
			PriceData priceData = new PriceData();
			priceData.setPriceid(pdata.getPriceid());
			priceData.setValue(price.getValue());
			priceData.setCurrency(price.getCurrency());
			priceData.setPid(pdata.getPid());
			priceService.updateProductPrice(priceData);
		}
	}
}

package com.myretail.price.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="Product IDAlready Exists")
public class DuplicateProductIdException extends RuntimeException {
	
	

}

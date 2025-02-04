package com.health.custom_exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String resource, Long id) {
		super(resource +" not found with Id : "+id);
	}
	
	public ResourceNotFoundException(String resource) {
		super(resource);
	}
}

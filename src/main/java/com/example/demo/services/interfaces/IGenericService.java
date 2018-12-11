package com.example.demo.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;



public interface IGenericService<ParameterizedType> { 
	
	public List<ParameterizedType> getAll(Long id,Pageable request);

}

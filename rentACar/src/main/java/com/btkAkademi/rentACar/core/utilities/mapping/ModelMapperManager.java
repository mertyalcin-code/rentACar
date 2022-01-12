package com.btkAkademi.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class ModelMapperManager implements ModelMapperService {
	//Dependencies
	private ModelMapper modelMapper;
	//Dependency Injection
	@Autowired
	public ModelMapperManager(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	// Mapping Configuration for Original to Dto
	@Override
	public ModelMapper forDto() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	}
	// Mapping Configuration for Request to Original
	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		return modelMapper;
	}

}

package com.btkAkademi.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

	ModelMapper forDto();

	ModelMapper forRequest();
}

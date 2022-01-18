package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.AdditionalServiceItemService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.AdditionalServiceItemListDto;
import com.btkAkademi.rentACar.business.dtos.CityListDto;
import com.btkAkademi.rentACar.business.requests.additionalServiceItemRequest.CreateAdditionalServiceItemRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.AdditionalServiceItemDao;
import com.btkAkademi.rentACar.entities.concretes.AdditionalServiceItem;
import com.btkAkademi.rentACar.entities.concretes.City;
@Service
public class AdditionalServiceItemManager implements AdditionalServiceItemService{
	private ModelMapperService modelMapperService;
	private AdditionalServiceItemDao additionalServiceItemDao;
	
	@Autowired
	public AdditionalServiceItemManager(ModelMapperService modelMapperService,
			AdditionalServiceItemDao additionalServiceItemDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.additionalServiceItemDao = additionalServiceItemDao;
	}

	@Override
	public Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		AdditionalServiceItem additionalServiceItem = this.modelMapperService.forRequest().map(createAdditionalServiceItemRequest, AdditionalServiceItem.class);
		this.additionalServiceItemDao.save(additionalServiceItem);
		return new SuccessResult(Messages.cityAdded);
	}

	@Override
	public DataResult<AdditionalServiceItemListDto> findById(int id) {
		if(additionalServiceItemDao.existsById(id)) {
			AdditionalServiceItem item = additionalServiceItemDao.findById(id).get();
			AdditionalServiceItemListDto response = modelMapperService.forDto().map(item, AdditionalServiceItemListDto.class);
			return new SuccessDataResult<AdditionalServiceItemListDto>(response);
		}else return new ErrorDataResult<AdditionalServiceItemListDto>();
		
	}

}

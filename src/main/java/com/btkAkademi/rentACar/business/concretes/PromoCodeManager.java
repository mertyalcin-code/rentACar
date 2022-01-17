package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PromoCodeService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.CarListDto;
import com.btkAkademi.rentACar.business.dtos.PromoCodeDto;
import com.btkAkademi.rentACar.business.requests.promoCodeRequest.CreatePromoCodeRequest;
import com.btkAkademi.rentACar.business.requests.promoCodeRequest.UpdatePromoCodeRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PromoCodeDao;
import com.btkAkademi.rentACar.entities.concretes.Car;
import com.btkAkademi.rentACar.entities.concretes.PromoCode;
@Service
public class PromoCodeManager implements PromoCodeService{
	private PromoCodeDao promoCodeDao;
	private ModelMapperService modelMapperService;	
	@Autowired
	public PromoCodeManager(PromoCodeDao promoCodeDao, ModelMapperService modelMapperService) {
		super();
		this.promoCodeDao = promoCodeDao;
		this.modelMapperService = modelMapperService;
	}
	@Override
	public DataResult<List<PromoCodeDto>> findAll() {
		List<PromoCode> promoCodeList = this.promoCodeDao.findAll();
		List<PromoCodeDto> response = promoCodeList.stream().map(promoCode -> modelMapperService.forDto().map(promoCode, PromoCodeDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<PromoCodeDto>>(response);
	}

	@Override
	public DataResult<PromoCodeDto> findById(int promoCodeId) {
		PromoCode promoCode = promoCodeDao.findById(promoCodeId).get();
		PromoCodeDto response = modelMapperService.forDto().map(promoCode, PromoCodeDto.class);
		return new SuccessDataResult<PromoCodeDto>(response);
	}

	@Override
	public Result add(CreatePromoCodeRequest createPromoCodeRequest) {
		Result result = BusinessRules.run(				
				checkIfPromoCodeExistsByCode(createPromoCodeRequest.getCode())
				)	;		
		if(result!=null) {			
			return result;
		}
		PromoCode promoCode = this.modelMapperService.forRequest().map(createPromoCodeRequest, PromoCode.class);
		
		this.promoCodeDao.save(promoCode);
		return new SuccessResult(Messages.promoCodeAdded);
		
	}

	@Override
	public Result update(UpdatePromoCodeRequest updatePromoCodeRequest) {		
		PromoCode promoCode = this.modelMapperService.forRequest().map(updatePromoCodeRequest, PromoCode.class);		
		this.promoCodeDao.save(promoCode);
		return new SuccessResult(Messages.promoCodeUpdated);
	}

	@Override
	public Result delete(int id) {
		if(promoCodeDao.existsById(id)) {
			promoCodeDao.deleteById(id);
			return new SuccessResult(Messages.promoCodeDeleted);
		}else return new ErrorResult();
	
	}
	private Result checkIfPromoCodeExistsByCode(String code){
		if(promoCodeDao.findByCode(code)!=null) {
			return new ErrorResult(Messages.promoCodeAlreadyExists);
		}
		else return new SuccessResult();
	}


}

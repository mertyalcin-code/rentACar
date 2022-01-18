package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PromoCodeService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.PromoCodeDto;
import com.btkAkademi.rentACar.business.requests.promoCodeRequest.CreatePromoCodeRequest;
import com.btkAkademi.rentACar.business.requests.promoCodeRequest.UpdatePromoCodeRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.PromoCodeDao;
import com.btkAkademi.rentACar.entities.concretes.PromoCode;

@Service
public class PromoCodeManager implements PromoCodeService {
	// Dependencies
	private PromoCodeDao promoCodeDao;
	private ModelMapperService modelMapperService;

	// Dependency Injeciton
	@Autowired
	public PromoCodeManager(PromoCodeDao promoCodeDao, ModelMapperService modelMapperService) {
		super();
		this.promoCodeDao = promoCodeDao;
		this.modelMapperService = modelMapperService;
	}

	// finds all promo codes
	@Override
	public DataResult<List<PromoCodeDto>> findAll() {
		List<PromoCode> promoCodeList = this.promoCodeDao.findAll();
		List<PromoCodeDto> response = promoCodeList.stream()
				.map(promoCode -> modelMapperService.forDto().map(promoCode, PromoCodeDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<PromoCodeDto>>(response);
	}

	// finds all active codes
	@Override
	public DataResult<List<PromoCodeDto>> findAllNotExpired() {
	/*	List<PromoCode> promoCodeList = promoCodeDao.findAllActiveCodes(LocalDate.now());
		List<PromoCodeDto> response = promoCodeList.stream()
				.map(promoCode -> modelMapperService.forDto().map(promoCode, PromoCodeDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<PromoCodeDto>>(response);
		*/
		return null;
	}

	// finds by id
	@Override
	public DataResult<PromoCodeDto> findById(int promoCodeId) {
		PromoCode promoCode = promoCodeDao.findById(promoCodeId).get();
		PromoCodeDto response = modelMapperService.forDto().map(promoCode, PromoCodeDto.class);
		return new SuccessDataResult<PromoCodeDto>(response);
	}

	// Finds by Code
	@Override
	public DataResult<PromoCodeDto> findByCode(String code) {

		PromoCode promoCode = promoCodeDao.findByCode(code);
		if (promoCode == null) {
			return new ErrorDataResult<PromoCodeDto>(Messages.promoCodeNotFound);
		}
		PromoCodeDto response = modelMapperService.forDto().map(promoCode, PromoCodeDto.class);
		return new SuccessDataResult<PromoCodeDto>(response);
	}

	// Creates a new code
	@Override
	public Result add(CreatePromoCodeRequest createPromoCodeRequest) {
		Result result = BusinessRules.run(checkIfPromoCodeExistsByCode(createPromoCodeRequest.getCode()),
				checkIfDatesAreCorrect(createPromoCodeRequest.getStartDate(),createPromoCodeRequest.getEndDate())
				);
		if (result != null) {
			return result;
		}
		PromoCode promoCode = this.modelMapperService.forRequest().map(createPromoCodeRequest, PromoCode.class);

		this.promoCodeDao.save(promoCode);
		return new SuccessResult(Messages.promoCodeAdded);

	}

	// Update
	@Override
	public Result update(UpdatePromoCodeRequest updatePromoCodeRequest) {
		Result result = BusinessRules.run(
				checkIfDatesAreCorrect(updatePromoCodeRequest.getStartDate(),updatePromoCodeRequest.getEndDate())
				);
		if (result != null) {
			return result;
		}
		PromoCode promoCode = this.modelMapperService.forRequest().map(updatePromoCodeRequest, PromoCode.class);
		this.promoCodeDao.save(promoCode);
		return new SuccessResult(Messages.promoCodeUpdated);
	}

	// Delete
	@Override
	public Result delete(int id) {
		if (promoCodeDao.existsById(id)) {
			promoCodeDao.deleteById(id);
			return new SuccessResult(Messages.promoCodeDeleted);
		} else
			return new ErrorResult();

	}

	// codes should be unique
	private Result checkIfPromoCodeExistsByCode(String code) {
		if (promoCodeDao.findByCode(code) != null) {
			return new ErrorResult(Messages.promoCodeAlreadyExists);
		} else
			return new SuccessResult();
	}
	// codes should be unique
	private Result checkIfDatesAreCorrect(LocalDate startDate, LocalDate endDate) {
		if (endDate.isBefore(startDate)) {
			return new ErrorResult(Messages.datesAreIncorrect);
		} else
			return new SuccessResult();
	}
}

package com.btkAkademi.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.PromoCodeService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.PromoCodeDto;
import com.btkAkademi.rentACar.business.requests.promoCodeRequests.CreatePromoCodeRequest;
import com.btkAkademi.rentACar.business.requests.promoCodeRequests.UpdatePromoCodeRequest;
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
		return new SuccessDataResult<List<PromoCodeDto>>(response, Messages.PROMOCODELIST);
	}

	// finds by id
	@Override
	public DataResult<PromoCodeDto> findById(int promoCodeId) {
		if (promoCodeDao.existsById(promoCodeId)) {
			PromoCode promoCode = promoCodeDao.findById(promoCodeId).get();
			PromoCodeDto response = modelMapperService.forDto().map(promoCode, PromoCodeDto.class);
			return new SuccessDataResult<PromoCodeDto>(response, Messages.PROMOCODELIST);
		} else
			return new ErrorDataResult<PromoCodeDto>(Messages.PROMOCODENOTFOUND);

	}

	// Finds by Code
	@Override
	public DataResult<PromoCodeDto> findByCode(String code) {
		PromoCode promoCode = promoCodeDao.findByCode(code);
		if (promoCode == null) {
			return new ErrorDataResult<PromoCodeDto>(Messages.PROMOCODENOTFOUND);
		} else {
			PromoCodeDto response = modelMapperService.forDto().map(promoCode, PromoCodeDto.class);

			Result result = BusinessRules.run(checkIfCodeStillValid(response));
			if (result != null) {
				return new ErrorDataResult<PromoCodeDto>(Messages.PROMOCODEEXPIRED);
			} else
				return new SuccessDataResult<PromoCodeDto>(response, Messages.PROMOCODELIST);
		}

	}

	// Creates a new code
	@Override
	public Result add(CreatePromoCodeRequest createPromoCodeRequest) {
		Result result = BusinessRules.run(checkIfPromoCodeExistsByCode(createPromoCodeRequest.getCode()),
				checkIfDatesAreCorrect(createPromoCodeRequest.getStartDate(), createPromoCodeRequest.getEndDate()));
		if (result != null) {
			return result;
		}
		PromoCode promoCode = this.modelMapperService.forRequest().map(createPromoCodeRequest, PromoCode.class);
		promoCode.setId(0);
		this.promoCodeDao.save(promoCode);
		return new SuccessResult(Messages.PROMOCODEADD);

	}

	// Update
	@Override
	public Result update(UpdatePromoCodeRequest updatePromoCodeRequest) {
		Result result = BusinessRules.run(
				checkIfDatesAreCorrect(updatePromoCodeRequest.getStartDate(), updatePromoCodeRequest.getEndDate()));
		if (result != null) {
			return result;
		}
		PromoCode promoCode = this.modelMapperService.forRequest().map(updatePromoCodeRequest, PromoCode.class);
		this.promoCodeDao.save(promoCode);
		return new SuccessResult(Messages.PROMOCODEUPDATE);
	}

	// Delete
	@Override
	public Result delete(int id) {
		if (promoCodeDao.existsById(id)) {
			promoCodeDao.deleteById(id);
			return new SuccessResult(Messages.PROMOCODEDELETE);
		} else
			return new ErrorResult();

	}

	// codes should be unique
	private Result checkIfPromoCodeExistsByCode(String code) {
		if (promoCodeDao.findByCode(code) != null) {
			return new ErrorResult(Messages.PROMOCODEALREADYEXISTS);
		} else
			return new SuccessResult();
	}

	// dates should be correct
	private Result checkIfDatesAreCorrect(LocalDate startDate, LocalDate endDate) {
		if (endDate.isBefore(startDate)) {
			return new ErrorResult(Messages.DATESARENOTCORRECT);
		} else
			return new SuccessResult();
	}

	// Controls the codes end date is passed
	private Result checkIfCodeStillValid(PromoCodeDto code) {
		if (code.getEndDate().isBefore(LocalDate.now())) {
			return new ErrorResult(Messages.PROMOCODEEXPIRED);
		} else
			return new SuccessResult();
	}
}

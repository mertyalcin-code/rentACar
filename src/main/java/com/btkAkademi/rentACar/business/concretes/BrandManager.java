package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.BrandService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.BrandListDto;
import com.btkAkademi.rentACar.business.requests.brandRequests.CreateBrandRequest;
import com.btkAkademi.rentACar.business.requests.brandRequests.UpdateBrandRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.BrandDao;
import com.btkAkademi.rentACar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {
	// Constants
	private static final int limit = 20;
	// Dependencies
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	// Dependency Injection
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {

		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	// Lists all brands
	@Override
	public DataResult<List<BrandListDto>> findAll() {
		List<Brand> brandList = this.brandDao.findAll();
		List<BrandListDto> response = brandList.stream()
				.map(brand -> modelMapperService.forDto().map(brand, BrandListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<BrandListDto>>(response);
	}

	// Finds brand by id
	@Override
	public DataResult<BrandListDto> findById(int id) {
		if (brandDao.existsById(id)) {
			Brand brand = this.brandDao.findById(id).get();
			BrandListDto response = modelMapperService.forDto().map(brand, BrandListDto.class);
			return new SuccessDataResult<BrandListDto>(response);
		} else
			return new ErrorDataResult<>(Messages.notFound);
	}

	// Adds a new brand
	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		Result result = BusinessRules.run(checkIfBrandNameExists(createBrandRequest.getName()),
				checkIfBrandLimitExceeded(BrandManager.limit));

		if (result != null) {

			return result;
		}

		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);

		return new SuccessResult(Messages.brandAdded);
	}

	// Updates current brand
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		Result result = BusinessRules.run(checkIfBrandNameExists(updateBrandRequest.getName()),
				checkIfBrandIdExists(updateBrandRequest.getId()));

		if (result != null) {

			return result;
		}

		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);

		return new SuccessResult(Messages.brandUpdated);
	}

	// Deletes brand by id
	@Override
	public Result delete(int id) {
		if (brandDao.existsById(id)) {
			brandDao.deleteById(id);
			return new SuccessResult(Messages.brandDeleted);
		} else
			return new ErrorResult(Messages.notFound);

	}

	// Checks brand name exists in the database
	private Result checkIfBrandNameExists(String brandname) {

		Brand brand = this.brandDao.findByName(brandname);

		if (brand != null) {
			return new ErrorResult(Messages.brandNameExists);
		}
		return new SuccessResult();

	}

	// Checks total brands in the database and controls limit
	private Result checkIfBrandLimitExceeded(int limit) {
		if (this.brandDao.count() >= limit) {

			return new ErrorResult(Messages.brandLimitExceeded);
		}
		return new SuccessResult();
	}

	// Checks is there a brand with that id
	private Result checkIfBrandIdExists(int id) {

		if (!this.brandDao.existsById(id)) {
			return new ErrorResult(Messages.brandIdNotExists);

		}

		return new SuccessResult();
	}

}

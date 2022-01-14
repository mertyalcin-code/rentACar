package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.ColorService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.ColorListDto;
import com.btkAkademi.rentACar.business.requests.colorRequest.CreateColorRequest;
import com.btkAkademi.rentACar.business.requests.colorRequest.UpdateColorRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.ColorDao;

import com.btkAkademi.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {
	// Dependencies
	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	// Dependency Injection
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {

		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	// Lists all colors
	@Override
	public DataResult<List<ColorListDto>> getAll() {
		List<Color> colorList = this.colorDao.findAll();
		List<ColorListDto> response = colorList.stream()
				.map(color -> modelMapperService.forDto().map(color, ColorListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ColorListDto>>(response);
	}

	// Adds a new color
	@Override
	public Result add(CreateColorRequest createColorRequest) {
		Result result = BusinessRules.run(checkIfColorNameExists(createColorRequest.getName()));

		if (result != null) {

			return result;
		}

		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);

		return new SuccessResult(Messages.colorAdded);
	}

	// Updates current color
	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Result result = BusinessRules.run(checkIfColorNameExists(updateColorRequest.getName()),
				checkIfColorIdExists(updateColorRequest.getId()));

		if (result != null) {

			return result;
		}

		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);

		return new SuccessResult(Messages.colorUpdate);
	}
	//checks is there a color with that id
	@Override
	public Result checkIfColorExist(int colorId) {
		if(!colorDao.existsById(colorId)) {
			return new ErrorResult(Messages.colorNotFound);					
		}else return new SuccessResult();		
	
	}
	// Helpers

	// Checks is there a color with that id
	private Result checkIfColorIdExists(int id) {
		if (!this.colorDao.existsById(id)) {

			return new ErrorResult(Messages.colorIdNotExists);
		}
		return new SuccessResult();
	}

	// Checks color name exists in the database
	private Result checkIfColorNameExists(String colorname) {

		Color color = this.colorDao.findByName(colorname);

		if (color != null) {
			return new ErrorResult(Messages.colorNameExists);
		}
		return new SuccessResult();

	}

	@Override
	public Result delete(int id) {
		colorDao.deleteById(id);
		return new SuccessResult();
	}

}

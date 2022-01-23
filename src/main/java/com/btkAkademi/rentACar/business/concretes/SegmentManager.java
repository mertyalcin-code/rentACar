package com.btkAkademi.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.SegmentService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.RentalListDto;
import com.btkAkademi.rentACar.business.dtos.SegmentListDto;
import com.btkAkademi.rentACar.business.requests.segmentRequest.CreateSegmentRequest;
import com.btkAkademi.rentACar.business.requests.segmentRequest.UpdateSegmentRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessDataResult;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.SegmentDao;
import com.btkAkademi.rentACar.entities.concretes.Rental;
import com.btkAkademi.rentACar.entities.concretes.Segment;

@Service
public class SegmentManager implements SegmentService {
	// Dependencies
	private ModelMapperService modelMapperService;
	private SegmentDao segmentDao;

	// Dependency Injection
	@Autowired
	public SegmentManager(ModelMapperService modelMapperService, SegmentDao segmentDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.segmentDao = segmentDao;
	}
	//Finds all segments
	@Override
	public DataResult<List<SegmentListDto>> findAll() {
		List<Segment> segmentList = this.segmentDao.findAll();
		List<SegmentListDto> response = segmentList.stream()
				.map(segment -> modelMapperService.forDto().map(segment, SegmentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<SegmentListDto>>(response,Messages.SEGMENTLIST);
	}

	// finds a segment
	@Override
	public DataResult<SegmentListDto> findById(int id) {
		if(segmentDao.existsById(id)) {
			SegmentListDto response = modelMapperService.forDto().map(segmentDao.findById(id).get(), SegmentListDto.class);
			return new SuccessDataResult<SegmentListDto>(response,Messages.SEGMENTLIST);
		}else return new ErrorDataResult<SegmentListDto>(Messages.NOTFOUND);
		
	}
	

	// create a new segment
	@Override
	public Result add(CreateSegmentRequest createSegmentRequest) {
		Result result = BusinessRules.run(CheckIfSegmentNameAlreadyExists(createSegmentRequest.getSegmentName()));
		if (result != null) {
			return result;
		}
		Segment segment = this.modelMapperService.forRequest().map(createSegmentRequest, Segment.class);
		this.segmentDao.save(segment);
		return new SuccessResult(Messages.SEGMENTADD);
	}

	// updates a new segment
	@Override
	public Result update(UpdateSegmentRequest updateSegmentRequest) {
		Segment segment = this.modelMapperService.forRequest().map(updateSegmentRequest, Segment.class);
		this.segmentDao.save(segment);
		return new SuccessResult(Messages.SEGMENTUPDATE);
	}

	// deletes a new segment
	@Override
	public Result delete(int id) {
		if (segmentDao.existsById(id)) {
			segmentDao.deleteById(id);
			return new SuccessResult(Messages.SEGMENTDELETE);
		} else
			return new ErrorResult(Messages.SEGMENTNOTFOUND);
	}

	// controls if there is a segment with that name
	private Result CheckIfSegmentNameAlreadyExists(String SegmentName) {
		if (segmentDao.findBySegmentName(SegmentName) != null) {
			return new ErrorResult(Messages.SEGMENTNAMEALREADYEXISTS);
		}
		return new SuccessResult();
	}



}

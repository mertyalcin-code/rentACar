package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.SegmentService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.SegmentListDto;
import com.btkAkademi.rentACar.business.requests.segmentRequest.CreateSegmentRequest;
import com.btkAkademi.rentACar.business.requests.segmentRequest.UpdateSegmentRequest;
import com.btkAkademi.rentACar.core.utilities.business.BusinessRules;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.SegmentDao;
import com.btkAkademi.rentACar.entities.concretes.Invoice;
import com.btkAkademi.rentACar.entities.concretes.Segment;
@Service
public class SegmentManager implements SegmentService{
	//Dependencies
	private ModelMapperService modelMapperService;
	private SegmentDao segmentDao;
	//Dependency Injection
	@Autowired
	public SegmentManager(ModelMapperService modelMapperService, SegmentDao segmentDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.segmentDao = segmentDao;
	}
	// finds a segment
	@Override
	public DataResult<SegmentListDto> findById(int id) {
		// TODO Auto-generated method stub
		return null;
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
		return new SuccessResult(Messages.segmentAdded);
	}
	// updates a new segment
	@Override
	public Result update(UpdateSegmentRequest createSegmentRequest) {
		Segment segment = this.modelMapperService.forRequest().map(createSegmentRequest, Segment.class);
		this.segmentDao.save(segment);
		return new SuccessResult(Messages.segmentUpdated);
	}
	// deletes a new segment
	@Override
	public Result delete(int id) {
	if(segmentDao.existsById(id)) {
		segmentDao.deleteById(id);
		return new SuccessResult(Messages.segmentDeleted);
	}else return new ErrorResult(Messages.notFound);
	}
	//controls if there is a segment with that name
	private Result CheckIfSegmentNameAlreadyExists(String SegmentName) {
		if(segmentDao.findBySegmentName(SegmentName)!=null) {
			return new ErrorResult(Messages.segmentAlreadyExists);
		}
		return new SuccessResult();
	}

}

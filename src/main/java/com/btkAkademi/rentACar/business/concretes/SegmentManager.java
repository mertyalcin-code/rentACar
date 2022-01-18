package com.btkAkademi.rentACar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btkAkademi.rentACar.business.abstracts.SegmentService;
import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.business.dtos.SegmentListDto;
import com.btkAkademi.rentACar.business.requests.segmentRequest.CreateSegmentRequest;
import com.btkAkademi.rentACar.business.requests.segmentRequest.UpdateSegmentRequest;
import com.btkAkademi.rentACar.core.utilities.mapping.ModelMapperService;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;
import com.btkAkademi.rentACar.core.utilities.results.SuccessResult;
import com.btkAkademi.rentACar.dataAccess.abstracts.SegmentDao;
import com.btkAkademi.rentACar.entities.concretes.Invoice;
import com.btkAkademi.rentACar.entities.concretes.Segment;
@Service
public class SegmentManager implements SegmentService{
	private ModelMapperService modelMapperService;
	private SegmentDao segmentDao;
	
	@Autowired
	public SegmentManager(ModelMapperService modelMapperService, SegmentDao segmentDao) {
		super();
		this.modelMapperService = modelMapperService;
		this.segmentDao = segmentDao;
	}

	@Override
	public DataResult<SegmentListDto> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result add(CreateSegmentRequest createSegmentRequest) {
		Segment segment = this.modelMapperService.forRequest().map(createSegmentRequest, Segment.class);
		this.segmentDao.save(segment);
		return new SuccessResult(Messages.invoiceAdded);
	}

	@Override
	public Result update(UpdateSegmentRequest createSegmentRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

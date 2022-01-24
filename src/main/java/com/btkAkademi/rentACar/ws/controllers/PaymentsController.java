package com.btkAkademi.rentACar.ws.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btkAkademi.rentACar.business.abstracts.PaymentService;
import com.btkAkademi.rentACar.business.dtos.PaymentListDto;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CalculateTotalPriceRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.CreatePaymentRequest;
import com.btkAkademi.rentACar.business.requests.paymentRequests.UpdatePaymentRequest;
import com.btkAkademi.rentACar.core.utilities.results.DataResult;
import com.btkAkademi.rentACar.core.utilities.results.Result;

@CrossOrigin
@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
	private PaymentService paymentService;

	@Autowired
	public PaymentsController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

	@GetMapping("find-all")
	public DataResult<List<PaymentListDto>> findAll(@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = " 10") int pageSize) {
		return this.paymentService.findAll(pageNo, pageSize);
	}

	@PostMapping("find-total-price")
	public DataResult<Double> calculateTotalPrice(@RequestBody CalculateTotalPriceRequest calculateTotalPriceRequest) {
		System.out.println(calculateTotalPriceRequest.getReturnDate());
		return this.paymentService.calculateTotalPriceForDisplay(calculateTotalPriceRequest);
	}

	@GetMapping("find-all-by-rental-id/{rentalId}")
	public DataResult<List<PaymentListDto>> getAllByRentalId(@PathVariable int rentalId) {

		return this.paymentService.findAllByRentalId(rentalId);
	}

	@GetMapping("find-by-id/{id}")
	public DataResult<PaymentListDto> findById(@PathVariable int id) {
		return this.paymentService.findById(id);
	}

	// adds a new payment
	@PostMapping("add")
	public Result add(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) {

		return this.paymentService.add(createPaymentRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest) {

		return this.paymentService.update(updatePaymentRequest);
	}

	@DeleteMapping("delete/{id}")
	public Result delete(@PathVariable int id) {
		return this.paymentService.delete(id);
	}
}

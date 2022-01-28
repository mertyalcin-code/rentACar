package com.btkAkademi.rentACar.business.exception;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
@CrossOrigin
@RestControllerAdvice
public class ExceptionHandling implements ErrorController{
	// For Validation Exceptions
	@ExceptionHandler(MethodArgumentNotValidException.class)

    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException argumentNotValidException){
        String message = argumentNotValidException.getBindingResult().getFieldErrors().get(0).getDefaultMessage();       
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(message);
        return errorDataResult;
    }
	
	// for NullPointerException 
    @ExceptionHandler(NullPointerException.class)
	public ErrorResult handleNullPointerException(NullPointerException exception){
		ErrorResult error = new ErrorResult(Messages.DATANOTFOUND);
		return error;
	}

	
	// Generally for messages in our case
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorResult handHttpMessageExceptionError(HttpMessageNotReadableException httpMessageNotReadableException){
		ErrorResult errorResult = new ErrorResult(Messages.FORMATERROR);
		return errorResult;

	}
	// For Deleting data which have relation in database
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ErrorResult handHttpMessageExceptionError(DataIntegrityViolationException dataIntegrityViolationException){
		ErrorResult errorResult = new ErrorResult(Messages.DATAHAVERELATIONS);
		return errorResult;
	}


}

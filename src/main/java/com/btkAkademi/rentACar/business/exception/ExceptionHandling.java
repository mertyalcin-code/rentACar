package com.btkAkademi.rentACar.business.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.SerializationException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.btkAkademi.rentACar.business.constants.Messages;
import com.btkAkademi.rentACar.core.utilities.results.ErrorDataResult;
import com.btkAkademi.rentACar.core.utilities.results.ErrorResult;
@CrossOrigin
@RestControllerAdvice
public class ExceptionHandling implements ErrorController{
	// For Validation Exceptions
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException argumentNotValidException){
        Map<String,String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : argumentNotValidException.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(validationErrors,Messages.VALIDATIONERROR);
        return errorDataResult;
    }
	// For Entities
    @ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    private ErrorResult handleEntityNotFound(EntityNotFoundException ex){
        return new ErrorResult("Data not found!");
    }
    
    @ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleNoSuchElementException(NoSuchElementException exception){
		ErrorResult error = new ErrorResult(Messages.DATANOTFOUND);
		return error;
	}


	@ExceptionHandler(SerializationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResult handleSerializationException(SerializationException serializationException){
		ErrorResult error = new ErrorResult(Messages.FORMATERROR);
		return error;
	}
	
	// Generally for messages in our case
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorResult handHttpMessageExceptionError(HttpMessageNotReadableException httpMessageNotReadableException){
		ErrorResult errorResult = new ErrorResult(Messages.FORMATERROR);
		return errorResult;

	}
	// For Deletinh data which have relation in databas
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ErrorResult handHttpMessageExceptionError(DataIntegrityViolationException dataIntegrityViolationException){
		ErrorResult errorResult = new ErrorResult(Messages.DATAHAVERELATIONS);
		return errorResult;

	}


}

package ci.atos.apireservationservicedomicile.web.resource;


import ci.atos.apireservationservicedomicile.web.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(BookingNotFoundException.class)
//    public ProblemDetail handleBookingNotFoundException(BookingNotFoundException exception) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
//                HttpStatus.NOT_FOUND, "Booking not found: " + exception.getMessage());
//        problemDetail.setProperty("errorType", "BookingError");
//        return problemDetail;
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(ClientNotFoundException.class)
//    public ProblemDetail handleClientNotFoundException(ClientNotFoundException exception) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
//                HttpStatus.NOT_FOUND, "Client not found: " + exception.getMessage());
//        problemDetail.setProperty("errorType", "ClientError");
//        return problemDetail;
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(CustomerNotFoundException.class)
//    public ProblemDetail handleCustomerNotFoundException(CustomerNotFoundException exception) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
//                HttpStatus.NOT_FOUND, "Customer not found: " + exception.getMessage());
//        problemDetail.setProperty("errorType", "CustomerError");
//        return problemDetail;
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(MyProviderNotFoundException.class)
//    public ProblemDetail handleMyProviderNotFoundException(MyProviderNotFoundException exception) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
//                HttpStatus.NOT_FOUND, "Provider not found: " + exception.getMessage());
//        problemDetail.setProperty("errorType", "ProviderError");
//        return problemDetail;
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(ServiceHomeCategoryNotFoundException.class)
//    public ProblemDetail handleServiceHomeCategoryNotFoundException(ServiceHomeCategoryNotFoundException exception) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
//                HttpStatus.NOT_FOUND, "Category not found: " + exception.getMessage());
//        problemDetail.setProperty("errorType", "categoryError");
//        return problemDetail;
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(ServiceHomeNotFoundException.class)
//    public ProblemDetail handleServiceHomeNotFoundException(ServiceHomeNotFoundException exception) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
//                HttpStatus.NOT_FOUND, "ServiceHome not found: " + exception.getMessage());
//        problemDetail.setProperty("errorType", "serviceError");
//        return problemDetail;
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(ServiceHomeException.class)
//    public ProblemDetail handleServiceHomeException(ServiceHomeException exception) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
//                HttpStatus.NOT_FOUND, "Service not found: " + exception.getMessage());
//        problemDetail.setProperty("errorType", "serviceError");
//        return problemDetail;
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException exception) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
//                HttpStatus.BAD_REQUEST, "Validation Field Error");
//
//        BindingResult bindingResult = exception.getBindingResult();
//        Map<String, String> errors = new HashMap<>();
//
//        for (ObjectError error : bindingResult.getAllErrors()) {
//            if (error instanceof FieldError fieldError) {
//                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
//            } else {
//                errors.put(error.getObjectName(), error.getDefaultMessage());
//            }
//        }
//
//        problemDetail.setProperty("errors", errors);
//        return problemDetail;
//    }
}

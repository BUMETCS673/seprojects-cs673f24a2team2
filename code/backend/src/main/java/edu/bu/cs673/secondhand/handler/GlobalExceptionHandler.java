package edu.bu.cs673.secondhand.handler;

import edu.bu.cs673.secondhand.enums.ErrorMsg;
import edu.bu.cs673.secondhand.exception.ParamException;
import edu.bu.cs673.secondhand.vo.ResultVo;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/***
 Email: ybinman@bu.edu
 DateTime: 10/12/24-22:25
 *****/
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVo MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> collect = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResultVo.fail(ErrorMsg.PARAM_ERROR,collect);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVo HttpMessageNotReadableExceptionHandler() {
        return ResultVo.fail(ErrorMsg.MISSING_PARAMETER, "requestBody Error!");
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultVo MissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return ResultVo.fail(ErrorMsg.MISSING_PARAMETER, "missing param: "+e.getParameterName());
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResultVo ConstraintViolationExceptionHandler(ConstraintViolationException e) {
//
//        Set<ConstraintViolation<?>> set = e.getConstraintViolations();
//        Map<String, String> map = new HashMap<>();
//        if (set.size() > 0) {
//            for (ConstraintViolation<?> cv : set) {
//                String[] param = cv.getPropertyPath().toString().split("\\.");
//                String message = cv.getMessage();
//                map.put(param[param.length - 1], message);
//            }
//        }
//
//        return ResultVo.fail(ErrorMsg.PARAM_ERROR, map);
//    }

    @ExceptionHandler(ParamException.class)
    public ResultVo ParamExceptionHandler(ParamException e) {
        return ResultVo.fail(ErrorMsg.PARAM_ERROR, e.getMap());
    }

    @ExceptionHandler(MissingRequestCookieException.class)
    public ResultVo MissingRequestCookieExceptionHandler(){
        return ResultVo.fail(ErrorMsg.COOKIE_ERROR);
    }
}

/**
 * DefaultHandlerExceptionResolver
 *
 * HttpRequestMethodNotSupportedException
 * 405 (SC_METHOD_NOT_ALLOWED)
 * HttpMediaTypeNotSupportedException
 * 415 (SC_UNSUPPORTED_MEDIA_TYPE)
 * HttpMediaTypeNotAcceptableException
 * 406 (SC_NOT_ACCEPTABLE)
 * MissingPathVariableException
 * 500 (SC_INTERNAL_SERVER_ERROR)
 * MissingServletRequestParameterException
 * 400 (SC_BAD_REQUEST)
 * ServletRequestBindingException
 * 400 (SC_BAD_REQUEST)
 * ConversionNotSupportedException
 * 500 (SC_INTERNAL_SERVER_ERROR)
 * TypeMismatchException
 * 400 (SC_BAD_REQUEST)
 * HttpMessageNotReadableException
 * 400 (SC_BAD_REQUEST)
 * HttpMessageNotWritableException
 * 500 (SC_INTERNAL_SERVER_ERROR)
 * MethodArgumentNotValidException
 * 400 (SC_BAD_REQUEST)
 * MissingServletRequestPartException
 * 400 (SC_BAD_REQUEST)
 * BindException
 * 400 (SC_BAD_REQUEST)
 * NoHandlerFoundException
 * 404 (SC_NOT_FOUND)
 * AsyncRequestTimeoutException
 * 503 (SC_SERVICE_UNAVAILABLE)
 */

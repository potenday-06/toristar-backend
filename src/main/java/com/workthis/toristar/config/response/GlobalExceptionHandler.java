package com.workthis.toristar.config.response;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.workthis.toristar.common.dto.CustomFieldError;
import com.workthis.toristar.common.dto.ErrorResponse;
import com.workthis.toristar.common.exception.BaseErrorCode;
import com.workthis.toristar.common.exception.GlobalErrorCode;
import com.workthis.toristar.common.exception.ProjectCodeException;
import com.workthis.toristar.common.exception.ProjectDynamicException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    private final SlackInternalErrorSender slackInternalErrorSender;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request) {
        ErrorResponse errorResponse =
                new ErrorResponse(statusCode.value(), statusCode.toString(), ex.getMessage());
        return super.handleExceptionInternal(ex, errorResponse, headers, statusCode, request);
    }

    @SneakyThrows
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        List<CustomFieldError> errors =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(CustomFieldError::new)
                        .collect(Collectors.toList());

        String errorsToJsonString = new ObjectMapper().writeValueAsString(errors);
        ErrorResponse errorResponse =
                new ErrorResponse(status.value(), status.toString(), errorsToJsonString, errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ProjectCodeException.class)
    public ResponseEntity<ErrorResponse> rainbowCodeExceptionHandler(ProjectCodeException e) {
        BaseErrorCode code = e.getErrorCode();
        return ResponseEntity.status(HttpStatus.valueOf(code.getErrorResponse().getStatus()))
                .body(code.getErrorResponse());
    }

    /** Request Param Validation 예외 처리 */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> constraintViolationExceptionHandler(
            ConstraintViolationException e, HttpServletRequest request) {
        Map<String, Object> bindingErrors = new HashMap<>();

        e.getConstraintViolations()
                .forEach(
                        constraintViolation -> {
                            List<String> propertyPath =
                                    List.of(
                                            constraintViolation
                                                    .getPropertyPath()
                                                    .toString()
                                                    .split("\\."));
                            String path =
                                    propertyPath.stream()
                                            .skip(propertyPath.size() - 1L)
                                            .findFirst()
                                            .orElse(null);
                            bindingErrors.put(path, constraintViolation.getMessage());
                        });

        ErrorResponse errorResponse =
                new ErrorResponse(
                        400, HttpStatus.BAD_REQUEST.getReasonPhrase(), bindingErrors.toString());
        return ResponseEntity.status(HttpStatus.valueOf(errorResponse.getStatus()))
                .body(errorResponse);
    }

    @ExceptionHandler(ProjectDynamicException.class)
    public ResponseEntity<ErrorResponse> RainbowDynamicExceptionHandler(
            ProjectDynamicException e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(e.getStatus(), e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request)
            throws IOException {
//        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
//        final Long userId = SecurityUtils.getCurrentMemberId();

        log.error("INTERNAL_SERVER_ERROR", e);
        GlobalErrorCode internalServerError = GlobalErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse =
                new ErrorResponse(
                        internalServerError.getStatus(),
                        internalServerError.getCode(),
                        internalServerError.getMessage());

//        slackInternalErrorSender.execute(cachingRequest, e, userId);
        return ResponseEntity.status(HttpStatus.valueOf(internalServerError.getStatus()))
                .body(errorResponse);
    }
}

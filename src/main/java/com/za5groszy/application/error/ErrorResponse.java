package com.za5groszy.application.error;

import com.za5groszy.foundation.sharedkernel.exception.AlreadyExistsException;
import com.za5groszy.foundation.sharedkernel.exception.DomainModelException;
import com.za5groszy.foundation.sharedkernel.exception.NotFoundException;
import com.za5groszy.foundation.sharedkernel.exception.PaymentRequiredException;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

final public class ErrorResponse extends ResponseEntity<List<JSONObject>> {
    private static final String FIELD_NAME = "fieldName";
    private static final String FIELD_MESSAGE = "message";
    private static final String FIELD_CONTEXT = "context";
    private static final String ERROR_CODE = "errorCode";

    private ErrorResponse(List<JSONObject> body, HttpStatus status) {
        super(body, status);
    }

    public static ErrorResponse validationError(List<FieldError> errors) {
        List<JSONObject> jsonErrors = new ArrayList<>();
        errors.forEach(item -> {
            jsonErrors.add(buildJson(item));
        });

        return new ErrorResponse(jsonErrors, HttpStatus.BAD_REQUEST);
    }

    public static ErrorResponse domainError(DomainModelException exception, String fieldName) {
        JSONObject json = buildJson(exception);
        json.put(FIELD_NAME, fieldName);
        List<JSONObject> jsonErrors = new ArrayList<>();
        jsonErrors.add(json);

        return new ErrorResponse(
                jsonErrors,
                getHttpStatusCode(exception)
        );
    }

    public static ErrorResponse domainError(DomainModelException exception) {
        JSONObject json = buildJson(exception);
        json.put(FIELD_NAME, "");
        List<JSONObject> jsonErrors = new ArrayList<>();
        jsonErrors.add(json);

        return new ErrorResponse(
                jsonErrors,
                getHttpStatusCode(exception)
        );
    }

    private static HttpStatus getHttpStatusCode(DomainModelException exception) {
        HttpStatus code = HttpStatus.BAD_REQUEST;
        if (exception instanceof PaymentRequiredException) {
            code = HttpStatus.PAYMENT_REQUIRED;
        } else if (exception instanceof NotFoundException) {
            code = HttpStatus.NOT_FOUND;
        } else if (exception instanceof AlreadyExistsException) {
            code = HttpStatus.CONFLICT;
        }

        return code;
    }

    private static JSONObject buildJson(FieldError error) {
        JSONObject json = new JSONObject();
        json.put(FIELD_NAME, error.getField());
        json.put(FIELD_MESSAGE, error.getDefaultMessage());
        json.put(FIELD_CONTEXT, error.getRejectedValue());
        json.put(ERROR_CODE, error.getCode());

        return json;
    }

    private static JSONObject buildJson(DomainModelException exception) {
        JSONObject json = new JSONObject();
        json.put(FIELD_MESSAGE, exception.getMessage());
        json.put(FIELD_CONTEXT, "");
        json.put(ERROR_CODE, exception.getErrorCode());

        return json;
    }
}

package com.homework.safetrust.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.PropertyEditor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.homework.safetrust.exception.EntityNotFoundException;
import com.homework.safetrust.exception.UnmatchIDException;

@SpringBootTest
public class AppExceptionHandlerTest {
    @Autowired
    private AppExceptionHandler appExceptionHandler;

    @Test
    void handleUserNotFoundSuccessCaseTest() {
        Map<String, String> errorMap = appExceptionHandler
                .handleUserNotFound(new EntityNotFoundException("EntityNotFoundException"));
        assertEquals("EntityNotFoundException", errorMap.get("errorMessage"));
    }

    @Test
    void handleUserNotFound2SuccessCaseTest() throws Exception {
        Map<String, String> errorMap = appExceptionHandler
                .handleUserNotFound(new UnmatchIDException("UnmatchIDException"));
        assertEquals("UnmatchIDException", errorMap.get("errorMessage"));
    }

    @Test
    void handleInvalidArgumentSuccessCaseTest() {
        MethodParameter methodParameter = new MethodParameter(new Object() {
        }.getClass().getEnclosingMethod(), -1);
        BindingResult bindingResult = new BindingResult() {

            @Override
            public String getObjectName() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getObjectName'");
            }

            @Override
            public void reject(String errorCode, Object[] errorArgs, String defaultMessage) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'reject'");
            }

            @Override
            public void rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'rejectValue'");
            }

            @Override
            public List<ObjectError> getGlobalErrors() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getGlobalErrors'");
            }

            @Override
            public List<FieldError> getFieldErrors() {
                List<FieldError> fieldErrors = new ArrayList<>();
                fieldErrors.add(new FieldError("name", "name", "wrong name"));
                return fieldErrors;
            }

            @Override
            public Object getFieldValue(String field) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getFieldValue'");
            }

            @Override
            public Object getTarget() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getTarget'");
            }

            @Override
            public Map<String, Object> getModel() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getModel'");
            }

            @Override
            public Object getRawFieldValue(String field) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getRawFieldValue'");
            }

            @Override
            public PropertyEditor findEditor(String field, Class<?> valueType) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'findEditor'");
            }

            @Override
            public PropertyEditorRegistry getPropertyEditorRegistry() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getPropertyEditorRegistry'");
            }

            @Override
            public String[] resolveMessageCodes(String errorCode) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'resolveMessageCodes'");
            }

            @Override
            public String[] resolveMessageCodes(String errorCode, String field) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'resolveMessageCodes'");
            }

            @Override
            public void addError(ObjectError error) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'addError'");
            }

        };
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(methodParameter, bindingResult);
        Map<String, String> errorMap = appExceptionHandler.handleInvalidArgument(exception);
        assertEquals("wrong name", errorMap.get("name"));
    }

}

    package org.yug.societylink.exception;

    import jakarta.servlet.http.HttpServletRequest;
    import org.springframework.dao.DataIntegrityViolationException;
    import org.springframework.http.HttpRequest;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.FieldError;
    import org.springframework.web.bind.MethodArgumentNotValidException;
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.ResponseBody;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    import java.util.HashMap;
    import java.util.Map;

    @RestControllerAdvice
    /* I CHANGED ControllerAdvice --> RestControllerAdvice as this annotation
    includes @ResponseBody */
    public class GlobalExceptionHandler {

        // Handles our custom UserNotFoundException
        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
            ApiError apiError=new ApiError(HttpStatus.NOT_FOUND.value(),
                    ex.getMessage(),
                    request.getRequestURI()
                    );
            return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND); // 404
        }

        // Handles any other generic logic errors (like wrong password)
        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex , HttpServletRequest request ) {
            ApiError apiError=new ApiError(HttpStatus.BAD_REQUEST.value(),
                    ex.getMessage(),
                    request.getRequestURI());
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST); // 400




        }

        //WRONG PASSWORD
        @ExceptionHandler(BadCredentials.class)
        public ResponseEntity<ApiError> handleBadCredentials(BadCredentials ex,HttpServletRequest request){
            ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED.value(),
                    ex.getMessage(),
                    request.getRequestURI());
            return new ResponseEntity<>(apiError,HttpStatus.UNAUTHORIZED);
        }

        //IF VALIDATION FAILS
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiError> handleValidationException(MethodArgumentNotValidException ex,HttpServletRequest request) {

            Map<String, String> errors = new HashMap<>();

            // Loop through all the errors Spring caught
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                // Extract the field name (like "contactNumber")
                String fieldName = ((FieldError) error).getField();
                // Extract the message you wrote (like "MOBILE NUMBER MUST BE EXACTLY 10 DIGITS")
                String errorMessage = error.getDefaultMessage();

                // Put them in the map
                errors.put(fieldName, errorMessage);
            });

            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST.value(),
                    "VALIDATION FAILED!",
                    request.getRequestURI(),
                    errors);

            // Return the map with a 400 Bad Request status
            return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
        }

        //WHEN A USER IS TRYING TO LOGIN WITH EMAIL THAT ALREADY EXISTS IN DB
        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException ex,HttpServletRequest request){

            ApiError apiError = new ApiError(HttpStatus.CONFLICT.value(),
                    "A RECORD WITH THIS EMAIL IS ALREADY EXIST!",
                    request.getRequestURI());
            return new ResponseEntity<>(apiError,HttpStatus.CONFLICT);
        }

    }
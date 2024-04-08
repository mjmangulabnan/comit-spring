package controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CustomErrorController implements ErrorController {

    //@Override
    public String getErrorPath() {
        return "/error";
    }

    @GetMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        // Get the HTTP status code of the error
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = status != null ? HttpStatus.valueOf(Integer.parseInt(status.toString())) : HttpStatus.INTERNAL_SERVER_ERROR;

        // Customize the error response message based on the status code
        String errorMessage;
        switch (httpStatus) {
            case NOT_FOUND:
                errorMessage = "The resource you are looking for is not found.";
                break;
            case INTERNAL_SERVER_ERROR:
                errorMessage = "An internal server error occurred.";
                break;
            default:
                errorMessage = "An error occurred.";
                break;
        }

        return ResponseEntity.status(httpStatus).body(errorMessage);
    }
}

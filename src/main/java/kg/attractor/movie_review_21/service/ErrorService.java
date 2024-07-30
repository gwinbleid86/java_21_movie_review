package kg.attractor.movie_review_21.service;

import kg.attractor.movie_review_21.errors.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(Exception exception);

    ErrorResponseBody makeResponse(BindingResult bindingResult);
}

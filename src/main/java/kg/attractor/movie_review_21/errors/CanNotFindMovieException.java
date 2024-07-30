package kg.attractor.movie_review_21.errors;

import java.util.NoSuchElementException;

public class CanNotFindMovieException extends NoSuchElementException {
    public CanNotFindMovieException() {
    }

    public CanNotFindMovieException(String message) {
        super(message);
    }
}

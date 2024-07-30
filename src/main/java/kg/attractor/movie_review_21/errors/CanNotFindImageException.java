package kg.attractor.movie_review_21.errors;

import java.util.NoSuchElementException;

public class CanNotFindImageException extends NoSuchElementException {
    public CanNotFindImageException(String msg) {
        super(msg);
    }
}

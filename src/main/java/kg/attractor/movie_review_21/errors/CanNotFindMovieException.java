package kg.attractor.movie_review_21.errors;

public class CanNotFindMovieException extends RuntimeException {
    public CanNotFindMovieException() {
    }

    public CanNotFindMovieException(String message) {
        super(message);
    }
}

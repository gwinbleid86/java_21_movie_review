package kg.attractor.movie_review_21.common;

import jakarta.servlet.http.HttpServletRequest;

public class Utilities {
    private Utilities() {
    }

    public static String getSiteUrl(HttpServletRequest request) {
        String siteUrl = request.getRequestURL().toString();
        return siteUrl.replace(request.getServletPath(), "");
    }
}

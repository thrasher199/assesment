package my.com.maybank.assessment.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HeadersUtil {
    public static String getHeadersAsString(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
                headers.append(headerName).append(": ").append(request.getHeader(headerName)).append(", "));
        return headers.toString();
    }

    public static String getHeadersAsString(HttpServletResponse response) {
        StringBuilder headers = new StringBuilder();
        response.getHeaderNames().forEach(headerName ->
                headers.append(headerName).append(": ").append(response.getHeader(headerName)).append(", "));
        return headers.toString();
    }
}

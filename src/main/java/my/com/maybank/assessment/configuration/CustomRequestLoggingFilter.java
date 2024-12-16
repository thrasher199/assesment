package my.com.maybank.assessment.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import my.com.maybank.assessment.utils.HeadersUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomRequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(CustomRequestLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            long timeTaken = System.currentTimeMillis() - startTime;

            String requestBody = new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
            String responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);

            log.info("\n============================= Request Begin ===============================\n" +
                            "URI         : {}\n" +
                            "Method      : {}\n" +
                            "Headers     : {}\n" +
                            "Request body: {}\n" +
                            "============================ Request End ================================",
                    request.getRequestURI(),
                    request.getMethod(),
                    HeadersUtil.getHeadersAsString(request),
                    requestBody);

            log.info("\n============================= Response Begin ==============================\n" +
                            "Status code  : {}\n" +
                            "Status text  : {}\n" +
                            "Headers      : {}\n" +
                            "Response body: {}\n" +
                            "Time taken   : {} ms\n" +
                            "============================= Response End ================================",
                    response.getStatus(),
                    response.getStatus(),
                    HeadersUtil.getHeadersAsString(response),
                    responseBody,
                    timeTaken);

            responseWrapper.copyBodyToResponse();
        }
    }
}

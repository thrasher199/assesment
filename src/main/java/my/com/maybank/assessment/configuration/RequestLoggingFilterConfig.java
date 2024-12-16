package my.com.maybank.assessment.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

public class RequestLoggingFilterConfig {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilterConfig.class);

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }

    @Bean
    public CustomRequestLoggingFilter customRequestLoggingFilter() {
        return new CustomRequestLoggingFilter();
    }
}

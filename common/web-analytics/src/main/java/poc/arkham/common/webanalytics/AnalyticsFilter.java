package poc.arkham.common.webanalytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Register user requests
 */
@Component
@ConditionalOnExpression("${asylum.analytics.enabled:true}")
public class AnalyticsFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(AnalyticsFilter.class);

    @Autowired
    private AnalyticsAsyncRecorder analyticsAsyncRecorder;

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        analyticsAsyncRecorder.doRecordAsync(request.getMethod(), request.getRequestURI());

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
 
    @Override
    public void destroy() {}
 
}
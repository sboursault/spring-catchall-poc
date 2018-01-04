package poc.arkham.common.webanalytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Register user requests
 */
public class AnalyticsFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(AnalyticsFilter.class);

    private AnalyticsAsyncRecorder analyticsAsyncRecorder;

    public AnalyticsFilter(AnalyticsAsyncRecorder analyticsAsyncRecorder) {
        this.analyticsAsyncRecorder = analyticsAsyncRecorder;
    }

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
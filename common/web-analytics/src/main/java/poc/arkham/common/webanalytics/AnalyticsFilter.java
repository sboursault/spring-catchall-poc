package poc.arkham.common.webanalytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Register user requests
 */
@Component
@ConditionalOnExpression("${asylum.analytics.enabled:true}")
public class AnalyticsFilter implements Filter {

    @Autowired
    private StringRedisTemplate strRedisTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String user = Math.random() < 0.5 ? "John" : "Peter";
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        ZSetOperations<String, String> zset = strRedisTemplate.opsForZSet();
        String key = "operation:" + httpRequest.getMethod() + " " + httpRequest.getRequestURI() + ":analytics";
        Long time = Long.valueOf(new Date().getTime());
        zset.add(key, time.toString(), time.doubleValue());
        // zset.removeRangeByScore() // TODO: remove old values in the set
        strRedisTemplate.expire(key, 10, TimeUnit.DAYS); // set or update the key's time to live

        // TODO track the actions of a specific user :)
        // TODO defer this process

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
 
    @Override
    public void destroy() {}
 
}
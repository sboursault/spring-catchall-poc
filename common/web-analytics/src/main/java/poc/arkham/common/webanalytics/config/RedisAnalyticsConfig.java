package poc.arkham.common.webanalytics.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import poc.arkham.common.webanalytics.AnalyticsAsyncRecorder;
import poc.arkham.common.webanalytics.AnalyticsFilter;

@Configuration
@EnableAsync
@ComponentScan("poc.arkham.common.webanalytics")
public class RedisAnalyticsConfig {

    @Bean
	@ConditionalOnMissingBean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("redis");
		connectionFactory.setPort(6379);
		return connectionFactory;
	}

	@Bean
	@ConditionalOnMissingBean
	public StringRedisTemplate strRedisTemplate() {
		StringRedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(connectionFactory());
		return redisTemplate;
	}

	//@Bean("analyticsAsyncExecutor")
	//public Executor asyncExecutor() {
	//	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	//	executor.setCorePoolSize(2);
	//	executor.setMaxPoolSize(2);
	//	executor.setQueueCapacity(500);
	//	executor.setThreadNamePrefix("GithubLookup-");
	//	executor.initialize();
	//	return executor;
	//}

	@Bean
	@ConditionalOnExpression("${asylum.analytics.enabled:true}")
	public AnalyticsFilter analyticsFilter(StringRedisTemplate strRedisTemplate) {
		AnalyticsAsyncRecorder asyncRecorder = new AnalyticsAsyncRecorder(strRedisTemplate);
		return new AnalyticsFilter(asyncRecorder);
	}
}

package poc.arkham.common.webanalytics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class RedisConfig {

    @Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("redis");
		connectionFactory.setPort(6379);
		return connectionFactory;
	}

	@Bean
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
}

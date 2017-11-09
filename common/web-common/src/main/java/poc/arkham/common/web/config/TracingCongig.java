package poc.arkham.common.web.config;

import brave.Tracing;
import brave.opentracing.BraveTracer;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.okhttp3.OkHttpSender;

@Configuration
public class TracingCongig {

    @Bean
    public Tracer zipkinTracer(@Value("${spring.application.name:anonymous-app}") String application) {
        // could use Jaeger instead of zipkin (https://dzone.com/articles/opentracing-spring-boot-instrumentation)
        // could also find zipkin from eureka (http://www.baeldung.com/tracing-services-with-zipkin)
        OkHttpSender okHttpSender = OkHttpSender.create("http://zipkin:9411/api/v1/spans");
        AsyncReporter<Span> reporter = AsyncReporter.builder(okHttpSender).build();
        Tracing braveTracer = Tracing.newBuilder().localServiceName(application).reporter(reporter).build();
        return BraveTracer.create(braveTracer);
    }
}

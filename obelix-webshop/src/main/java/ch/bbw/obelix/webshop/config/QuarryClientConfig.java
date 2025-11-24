package ch.bbw.obelix.webshop.config;

import ch.bbw.obelix.quarry.api.QuarryApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class QuarryClientConfig {

    @Value("${obelix.quarry.base-url:http://localhost:8081}")
    private String quarryBaseUrl;

    @Bean
    public WebClient quarryWebClient() {
        return WebClient.builder()
                .baseUrl(quarryBaseUrl)
                .build();
    }

    @Bean
    public QuarryApi quarryApi(final WebClient quarryWebClient) {
        var adapter = WebClientAdapter.create(quarryWebClient);
        var factory = HttpServiceProxyFactory.builderFor(adapter)
                .build();
        return factory.createClient(QuarryApi.class);
    }
}
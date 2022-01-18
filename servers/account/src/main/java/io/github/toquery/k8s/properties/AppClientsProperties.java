package io.github.toquery.k8s.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "app.clients")
public class AppClientsProperties {
    private String movie;
}

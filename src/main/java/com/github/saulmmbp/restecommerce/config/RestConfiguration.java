package com.github.saulmmbp.restecommerce.config;

import com.github.saulmmbp.restecommerce.entity.Category;
import com.github.saulmmbp.restecommerce.entity.Order;
import com.github.saulmmbp.restecommerce.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;

    private EntityManager entityManager;

    public RestConfiguration(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] unsupportedMethods = {HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE};
        disableHttpMethods(config, Product.class, unsupportedMethods);
        disableHttpMethods(config, Category.class, unsupportedMethods);
        disableHttpMethods(config, Order.class, unsupportedMethods);

        config.exposeIdsFor(Product.class);
        config.exposeIdsFor(Category.class);

        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins);
    }

    private <T> void disableHttpMethods(RepositoryRestConfiguration config, Class<T> entity, HttpMethod[] unsupportedMethods) {
        config.getExposureConfiguration()
                .forDomainType(entity)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedMethods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedMethods));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Class<?>[] domainTypes = entityManager
                .getMetamodel()
                .getEntities()
                .stream()
                .map(Type::getJavaType)
                .toArray(Class<?>[]::new);

        config.exposeIdsFor(domainTypes);
    }
}

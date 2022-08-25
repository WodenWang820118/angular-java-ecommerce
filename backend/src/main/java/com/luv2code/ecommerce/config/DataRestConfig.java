package com.luv2code.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] allowedOrigins;
    private EntityManager entityManager;
    private List<Class> entityClasses;

    @Autowired
    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityClasses = new ArrayList<>();
    }
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedAction = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH};
        // disable HTTP methods for Product: PUT, POST, DELETE, PATCH
        config.getExposureConfiguration()
            .forDomainType(Product.class)
            .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedAction))
            .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedAction));
        
        // disable HTTP methods for ProductCategory: PUT, POST, DELETE
        config.getExposureConfiguration()
            .forDomainType(ProductCategory.class)
            .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedAction))
            .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedAction));
        
        // call an internal helper method
        exposeIds(config);

        // configure CORS mapping
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins);
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = this.getEntities();
        this.setAllExposedEntities(entities);
        // length of the new Class is zero initially
        // and generate required length of the Class type array if necessary
        Class<?> [] domainTypes = this.entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

    private Set<EntityType<?>> getEntities() {
        return entityManager.getMetamodel().getEntities();
    }

    private void addEntity(Class<?> entityClass) {
        this.entityClasses.add(entityClass);
    }

    private void setAllExposedEntities(Set<EntityType<?>> entities) {
        for (EntityType<?> entity: entities) {
            this.addEntity(entity.getJavaType());
        }
    }
}

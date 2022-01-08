package com.example.ecommerce.config;

import com.example.ecommerce.entity.County;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.entity.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
import org.springframework.data.rest.core.mapping.HttpMethods;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;
    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
//        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod[] theUnsupportedMethods ={HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

//        disable http methods for Product: put, post and delete

        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(Product.class), theUnsupportedMethods);

        //        disable http methods for ProductCategory: put, post and delete

        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(ProductCategory.class), theUnsupportedMethods);

        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(County.class), theUnsupportedMethods);

        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(Town.class), theUnsupportedMethods);

//        call an internal method
        exposeIds(config);
    }

    private void disableHttpMethods(ExposureConfigurer config, HttpMethod[] theUnsupportedMethods) {
        config
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedMethods))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedMethods));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
//        expose entity ids
//        get a list of all entity classes from entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
//        get an array list of entity types

        List<Class> entityClasses = new ArrayList<>();

//        get the entity types for the entities
        for (EntityType tempEntityType: entities){
            entityClasses.add(tempEntityType.getJavaType());
        }
//        expose entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}

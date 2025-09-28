package com.example.virtualBookStore.graphql;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphqlScalarsConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(){
        return writingBuilder->{
            GraphQLScalarType bigDecimalScalar = ExtendedScalars.GraphQLBigDecimal;
            writingBuilder
                    .scalar(GraphQLScalarType.newScalar(bigDecimalScalar).name("BigDecimal").build())
                    .scalar(ExtendedScalars.DateTime);
        };
    }
}

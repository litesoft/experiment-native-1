package org.litesoft.en1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main( String[] args ) {
        SpringApplication.run( Application.class, args );
    }

    @Bean
    Zinterface mapper() {
        return new Zinterface() {
            @Override
            public String convert( int value ) {
                return "" + value;
            }

            @Override
            public Integer convert( String value )
                    throws IllegalArgumentException {
                if ( value != null ) {
                    value = value.trim();
                    if ( !value.isEmpty() ) {
                        return Integer.parseInt( value );
                    }
                }
                throw new IllegalArgumentException( "value was null or blank" );
            }

            @Override
            public String toString() {
                return "Root Mapper";
            }
        };
    }
}

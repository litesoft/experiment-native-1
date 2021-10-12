package org.litesoft.en1;

import java.lang.reflect.Proxy;

public interface Zinterface {
    String convert( int value );

    Integer convert( String value )
            throws IllegalArgumentException;

    static void assertNotNull( Zinterface mapper ) {
        if ( mapper == null ) {
            throw new IllegalStateException( "No Mapper provided" );
        }
    }

    static Zinterface report( Zinterface mapper ) {
        assertNotNull( mapper );
        System.out.println( mapper + " | Is Proxy: " + Proxy.isProxyClass( mapper.getClass() ) );
        return mapper;
    }
}

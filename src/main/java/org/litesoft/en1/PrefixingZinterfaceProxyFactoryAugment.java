package org.litesoft.en1;

import org.springframework.stereotype.Component;

@Component
public class PrefixingZinterfaceProxyFactoryAugment implements ZinterfaceProxyFactory {
    private static final String PREFIX = "Fred: ";

    @Override
    public Zinterface decorate( Zinterface mapper ) {
        Zinterface.assertNotNull( mapper );
        return new Zinterface() {
            @Override
            public String convert( int value ) {
                return PREFIX + mapper.convert( value );
            }

            @Override
            public Integer convert( String value )
                    throws IllegalArgumentException {
                if ( value != null ) {
                    if ( value.startsWith( PREFIX ) ) {
                        value = value.substring( PREFIX.length() );
                    }
                }
                return mapper.convert( value );
            }

            @Override
            public String toString() {
                return "Prefix -> " + mapper;
            }
        };
    }
}

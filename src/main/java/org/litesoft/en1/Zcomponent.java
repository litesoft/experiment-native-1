package org.litesoft.en1;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Zcomponent implements CommandLineRunner {
    private final Zinterface mapper;

    public Zcomponent( Zinterface mapper, List<ZinterfaceProxyFactory> factories ) {
        Zinterface.report( mapper );
        for ( ZinterfaceProxyFactory factory : factories ) {
            mapper = Zinterface.report( factory.decorate( mapper ) );
        }
        this.mapper = mapper;
    }

    @Override
    public void run( String... args ) {
        System.out.println( "Args:" );
        if ( (args == null) || (args.length == 0) ) {
            System.out.println( "  None!" );
            return;
        }
        for ( String zArg : args ) {
            Integer zInt;
            System.out.print( "  '" + zArg + "'" );
            try {
                zInt = mapper.convert( zArg );
                System.out.print( " : " + zInt );
                String zArg2 = mapper.convert( zInt );
                System.out.print( " : " + zArg2 );
                int zInt2 = mapper.convert( zArg2 );
                System.out.print( " : " + zInt2 );
            }
            catch ( IllegalArgumentException e ) {
                System.out.print( " : ? " + e.getClass().getSimpleName() + ": " + e.getMessage() );
            }
            System.out.println();
        }
    }
}

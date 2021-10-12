package org.litesoft.en1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.stereotype.Component;

@Component
public class DynamicZinterfaceProxyFactory implements ZinterfaceProxyFactory {
    @Override
    public Zinterface decorate( Zinterface mapper ) {
        Zinterface.assertNotNull( mapper );
        var handler = handler( mapper );
        return (Zinterface)Proxy.newProxyInstance( Zinterface.class.getClassLoader(),
                                                   new Class<?>[]{Zinterface.class},
                                                   handler );
    }

    @SuppressWarnings("Convert2Lambda")
    private static InvocationHandler handler( Zinterface source ) {
        return new InvocationHandler() {
            @Override
            public Object invoke( Object proxy, Method method, Object[] args ) {
                String zName = method.getName();
                if ( "toString".equals( zName ) ) {
                    return "Dynamic -> " + source.toString();
                }
                if ( !"convert".equals( zName ) ) {
                    throw new UnsupportedOperationException( "Not proxying method: " + zName );
                }
                // System.out.println( "\n ... method = " + method + ", args = " + Arrays.deepToString( args ) );
                if ( args == null ) {
                    throw new IllegalArgumentException( "null Args" );
                }
                if ( args.length != 1 ) {
                    throw new IllegalArgumentException( "Args.length NOT 1, but: " + args.length );
                }
                Object arg = args[0];
                if ( arg == null ) {
                    throw new IllegalArgumentException( "Args[0] == null" );
                }
                if ( arg instanceof String ) {
                    return source.convert( arg.toString() );
                }
                if ( !(arg instanceof Integer) ) {
                    throw new IllegalArgumentException( "Arg neither String or Integer, but " + arg.getClass().getSimpleName() +
                                                        ": " + args.length );
                }
                Integer argInt = (Integer)arg;
                return source.convert( argInt );
            }
        };
    }
}

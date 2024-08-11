package cc.ziyi.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal utility class
 */
@SuppressWarnings("all")
public class ThreadLocalUtil {
    // Provide a constant THREAD_LOCAL to maintain an global unique ThreadLocal object
    private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();

    // Get value based on key, this method call ThreadLocal's get(), can store any type of data
    public static <T> T get(){
        return (T) THREAD_LOCAL.get();
    }
	
    // Get key-value pairs
    public static void set(Object value){
        THREAD_LOCAL.set(value);
    }


    // Clear ThreadLocal to prevent memory leaks
    // ThreadLocal-object is global unique, has a long lifecycle
    public static void remove(){
        THREAD_LOCAL.remove();
    }
}

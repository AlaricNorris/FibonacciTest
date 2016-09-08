/**
 *  UniversalFibonacciLoader
 *  com.alaric.norris.exercises.calculateFibonacci.calculateFibonacci
 *  Function:       ${TODO}
 *  date            author
 *  *****************************************************
 *  2016/9/7 007         AlaricNorris
 *  Copyright (c) 2016, TNT All Rights Reserved.
 */
package com.alaric.norris.exercises.fibonacci.fibonacci;

import android.support.v4.util.LruCache;
import android.widget.TextView;

import java.math.BigInteger;
/**
 @formatter:off ClassName:      UniversalFibonacciLoader
 @formatter:off Function:       ${TODO}  ADD FUNCTION
 @formatter:off Contact:        Norris.sly@gmail.com
 @formatter:off @author         AlaricNorris
 @formatter:off @version        Ver 1.0
 @formatter:off @since          I used to be a programmer like you, then I took an arrow in the knee
 @formatter:off ***************************************************************************************************
 @formatter:off Modified By     AlaricNorris     2016/9/7 007    下午 01:13
 @formatter:off Modifications:  ${TODO}
 @formatter:off ***************************************************************************************************
 */
public class UniversalFibonacciLoader {
    public static LruCache< Integer, BigInteger > CachedFibonacciResults =
            new LruCache<>( 60 * 1024 );
    private static volatile UniversalFibonacciLoader mInstance;
    protected UniversalFibonacciLoader () {
    }
    /**
     *
     * @return
     *
     */
    public static UniversalFibonacciLoader getInstance () {
        if ( mInstance == null ) {
            synchronized ( UniversalFibonacciLoader.class ) {
                if ( mInstance == null ) {
                    mInstance = new UniversalFibonacciLoader();
                }
            }
        }
        return mInstance;
    }

    public static void displayFibonacciNumber ( Integer tag, TextView inTextView ) {
        inTextView.setTag( tag );
        if ( CachedFibonacciResults != null ) {
            if ( CachedFibonacciResults.get( tag ) != null ) {
                if ( inTextView.getTag() != null && inTextView.getTag().equals( tag ) ) {
                    inTextView.setText( tag + ":\t" + CachedFibonacciResults.get( tag ) );
                    return;
                }
            }
        }
        new FibonacciCalculateThread( tag, inTextView ).start();
    }

}

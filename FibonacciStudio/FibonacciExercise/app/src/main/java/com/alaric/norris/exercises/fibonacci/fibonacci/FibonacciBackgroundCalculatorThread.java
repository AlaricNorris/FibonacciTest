/**
 *  FibonacciBackgroundCalculatorThread
 *  com.alaric.norris.exercises.fibonacci.fibonacci
 *  Function:       ${TODO}
 *  date            author
 *  *****************************************************
 *  2016/9/8 008         AlaricNorris
 *  Copyright (c) 2016, TNT All Rights Reserved.
 */
package com.alaric.norris.exercises.fibonacci.fibonacci;

import android.support.annotation.NonNull;
import android.util.Log;

import java.math.BigInteger;
/**
 @formatter:off ClassName:      FibonacciBackgroundCalculatorThread
 @formatter:off Function:       ${TODO}  ADD FUNCTION
 @formatter:off Contact:        Norris.sly@gmail.com
 @formatter:off @author         AlaricNorris
 @formatter:off @version        Ver 1.0
 @formatter:off @since          I used to be a programmer like you, then I took an arrow in the knee
 @formatter:off ***************************************************************************************************
 @formatter:off Modified By     AlaricNorris     2016/9/8 008    下午 05:02
 @formatter:off Modifications:  ${TODO}
 @formatter:off ***************************************************************************************************
 */
public class FibonacciBackgroundCalculatorThread extends Thread {

    @NonNull
    private Integer seed;
    public FibonacciBackgroundCalculatorThread ( Integer inSeed ) {
        this.seed = inSeed;
    }
    @Override
    public void run () {
        int currentSeed = 1;
        do {
            currentSeed++;
            BigInteger result = calculateFibonacci( currentSeed );
            if ( UniversalFibonacciLoader.CachedFibonacciResults != null ) {
                UniversalFibonacciLoader.CachedFibonacciResults.put( currentSeed, result );
                Log.d(
                        "tag",
                        "FibonacciBackgroundCalculatorThread\n Cached:" + currentSeed + "result"
                );
            }

        }
        while ( currentSeed <= seed * seed );
    }

    public BigInteger calculateFibonacci ( int inSeed ) {
        if ( inSeed == 0 )
            return new BigInteger( String.valueOf( 0 ) );
        if ( inSeed == 1 )
            return new BigInteger( String.valueOf( 1 ) );
        BigInteger _1 = null;
        BigInteger _2 = null;
        Integer key_1 = inSeed - 1;
        Integer key_2 = inSeed - 2;
        if ( UniversalFibonacciLoader.CachedFibonacciResults != null ) {
            _1 = UniversalFibonacciLoader.CachedFibonacciResults.get( key_1 );
            _2 = UniversalFibonacciLoader.CachedFibonacciResults.get( key_2 );
        }
        _1 = _1 == null ? calculateFibonacci( key_1 ) : _1;
        _2 = _2 == null ? calculateFibonacci( key_2 ) : _2;
        if ( UniversalFibonacciLoader.CachedFibonacciResults != null ) {
            UniversalFibonacciLoader.CachedFibonacciResults.put( key_1, _1 );
            UniversalFibonacciLoader.CachedFibonacciResults.put( key_2, _2 );
        }
        return _1.add( _2 );
    }

}

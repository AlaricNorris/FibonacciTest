/**
 *  a
 *  com.alaric.norris.exercises.calculateFibonacci.calculateFibonacci
 *  Function:       ${TODO}
 *  date            author
 *  *****************************************************
 *  2016/9/7 007         AlaricNorris
 *  Copyright (c) 2016, TNT All Rights Reserved.
 */
package com.alaric.norris.exercises.fibonacci.fibonacci;

import android.support.annotation.NonNull;
import android.widget.TextView;

import java.math.BigInteger;
/**
 @formatter:off ClassName:      a
 @formatter:off Function:       ${TODO}  ADD FUNCTION
 @formatter:off Contact:        Norris.sly@gmail.com
 @formatter:off @author         AlaricNorris
 @formatter:off @version        Ver 1.0
 @formatter:off @since          I used to be a programmer like you, then I took an arrow in the knee
 @formatter:off ***************************************************************************************************
 @formatter:off Modified By     AlaricNorris     2016/9/7 007    下午 04:36
 @formatter:off Modifications:  ${TODO}
 @formatter:off ***************************************************************************************************
 */
public final class FibonacciCalculateThread extends Thread {
    @NonNull
    private Integer seed;
    @NonNull
    private TextView mTextView;
    public FibonacciCalculateThread ( Integer inSeed, TextView inTextView ) {
        this.seed = inSeed;
        this.mTextView = inTextView;
    }
    @Override
    public void run () {
        final BigInteger result = calculateFibonacci( seed );
        if ( result == null )
            return;

        if ( UniversalFibonacciLoader.CachedFibonacciResults != null ) {
            UniversalFibonacciLoader.CachedFibonacciResults.put( seed, result );
        }
        mTextView.post( new Runnable() {

            @Override
            public void run () {
                mTextView.setText( "" + result );
            }
        } );
    }

    public boolean isNeedAbandon () {
        if ( mTextView == null )
            return true;
        if ( mTextView.getTag() == null )
            return true;
        if ( ! mTextView.getTag().equals( seed ) )
            return true;
        return false;
    }

    public BigInteger calculateFibonacci ( int inSeed ) {
        if ( isNeedAbandon() )
            return null;
        if ( inSeed == 0 )
            return new BigInteger( String.valueOf( 0 ) );
        if ( inSeed == 1 )
            return new BigInteger( String.valueOf( 1 ) );
        BigInteger _1 = null;
        BigInteger _2 = null;
        if ( UniversalFibonacciLoader.CachedFibonacciResults != null ) {
            _1 = UniversalFibonacciLoader.CachedFibonacciResults.get( inSeed - 1 );
            _2 = UniversalFibonacciLoader.CachedFibonacciResults.get( inSeed - 2 );
        }
        _1 = _1 == null ? calculateFibonacci( inSeed - 1 ) : _1;
        _2 = _2 == null ? calculateFibonacci( inSeed - 2 ) : _2;
        if ( _1 == null || _2 == null )
            return null;
        if ( UniversalFibonacciLoader.CachedFibonacciResults != null ) {
            UniversalFibonacciLoader.CachedFibonacciResults.put( inSeed - 1, _1 );
            UniversalFibonacciLoader.CachedFibonacciResults.put( inSeed - 2, _2 );
        }
        return _1.add( _2 );
    }

}

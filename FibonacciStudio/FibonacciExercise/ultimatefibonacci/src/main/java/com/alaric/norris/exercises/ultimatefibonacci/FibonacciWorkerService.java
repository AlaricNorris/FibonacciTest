package com.alaric.norris.exercises.ultimatefibonacci;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.alaric.norris.exercises.ultimatefibonacci.db.FibonacciDao;
import com.alaric.norris.exercises.ultimatefibonacci.entity.Fibonacci;

import java.math.BigInteger;

public class FibonacciWorkerService extends Service {
    private FibonacciDao mDao;
    public FibonacciWorkerService () {
    }

    @Override
    public IBinder onBind ( Intent intent ) {
        throw new UnsupportedOperationException( "Not yet implemented" );
    }

    @Override
    public int onStartCommand ( Intent intent, int flags, int startId ) {
        new Thread() {

            @Override
            public void run () {
                {
                    mDao = new FibonacciDao( getApplicationContext() );
                    int currentSeed = 0;
                    do {
                        currentSeed++;
                        BigInteger result = calculateFibonacci( currentSeed );
                        Fibonacci temp = new Fibonacci( currentSeed, result );
                        mDao.add( temp );
                        Log.d( "tag", "FibonacciWorkerService\n result:" + currentSeed + "result" );
                    }
                    while ( currentSeed <= 450 * 450 );
                }
            }
        }.start();
        return super.onStartCommand( intent, flags, startId );
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
        Fibonacci fibonacci1 = mDao.getFibonacci( key_1 );
        Fibonacci fibonacci2 = mDao.getFibonacci( key_2 );

        _1 = fibonacci1 == null ? null : fibonacci1.getResult();
        _2 = fibonacci2 == null ? null : fibonacci2.getResult();
        _1 = _1 == null ? calculateFibonacci( key_1 ) : _1;
        _2 = _2 == null ? calculateFibonacci( key_2 ) : _2;
        return _1.add( _2 );
    }
}

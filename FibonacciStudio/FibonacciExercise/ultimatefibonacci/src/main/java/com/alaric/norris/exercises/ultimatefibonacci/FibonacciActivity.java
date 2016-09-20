package com.alaric.norris.exercises.ultimatefibonacci;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class FibonacciActivity extends AppCompatActivity {
    public static final ArrayList< Integer > mList = new ArrayList< Integer >();

    static {
        for ( int i = 1 ; i <= 450 ; i++ ) {
            mList.add( i );
        }
    }

    private ListView mListView;
    private FibonacciAdapter mAdapter;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        EventBus.getDefault().register( this );
        setContentView( R.layout.activity_fibonacci );
        mListView = ( ListView ) findViewById( R.id.list );
        mAdapter = new FibonacciAdapter( this, mList );
        mListView.setAdapter( mAdapter );
        mListView.postDelayed( new Runnable() {

            @Override
            public void run () {
                mAdapter.notifyDataSetChanged();
            }
        }, 1000 );
        startService( new Intent( getApplicationContext(), FibonacciWorkerService.class ) );
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        EventBus.getDefault().unregister( this );
    }

    public void onEvent ( EB_Event_CalculateFinished inEvent ) {
    }

    public static final class EB_Event_CalculateFinished {
        public Integer seed;

        public EB_Event_CalculateFinished () {
        }
        public EB_Event_CalculateFinished ( Integer inSeed ) {
            seed = inSeed;
        }
    }
}

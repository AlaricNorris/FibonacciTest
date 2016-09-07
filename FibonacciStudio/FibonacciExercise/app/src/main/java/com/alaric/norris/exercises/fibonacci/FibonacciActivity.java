package com.alaric.norris.exercises.fibonacci;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class FibonacciActivity extends AppCompatActivity {
    public static final ArrayList< Integer > mList = new ArrayList< Integer >();

    static {
        for ( int i = 1 ; i < 450 ; i++ ) {
            mList.add( i );
        }
    }

    private ListView mListView;
    private FibonacciAdapter mAdapter;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_fibonacci );
        mListView = ( ListView ) findViewById( R.id.list );
        mAdapter = new FibonacciAdapter( this, mList );
        mListView.setAdapter( mAdapter );
    }
}

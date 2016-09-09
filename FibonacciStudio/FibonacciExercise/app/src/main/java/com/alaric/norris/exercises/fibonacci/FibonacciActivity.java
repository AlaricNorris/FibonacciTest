package com.alaric.norris.exercises.fibonacci;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.alaric.norris.exercises.fibonacci.fibonacci.FibonacciBackgroundCalculatorThread;
import com.alaric.norris.exercises.fibonacci.fibonacci.UniversalFibonacciLoader;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
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
        initDiskCache();

        new FibonacciBackgroundCalculatorThread( 450, getApplicationContext() ).start();
    }
    private void initDiskCache () {
        try {
            File cacheDir = getDiskCacheDir( this, "Fibonacci" );
            if ( ! cacheDir.exists() ) {
                cacheDir.mkdirs();
            }
            UniversalFibonacciLoader.mDiskLruCache =
                    DiskLruCache.open( cacheDir, 1, 1, 10 * 1024 * 1024 );
        }
        catch ( Exception inE ) {
            inE.printStackTrace();
        }
    }

    public int getAppVersion ( Context context ) {
        try {
            PackageInfo info = getPackageManager().getPackageInfo( context.getPackageName(), 0 );
            return info.versionCode;
        }
        catch ( PackageManager.NameNotFoundException e ) {
            e.printStackTrace();
        }
        return 1;
    }

    public File getDiskCacheDir ( Context context, String uniqueName ) {
        String cachePath;
        if ( Environment.MEDIA_MOUNTED.equals( Environment.getExternalStorageState() ) ||
                ! Environment.isExternalStorageRemovable() ) {
            cachePath = context.getExternalCacheDir().getPath();
        }
        else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File( cachePath + File.separator + uniqueName );
    }
}

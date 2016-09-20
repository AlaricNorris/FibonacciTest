/**
 *  FibonacciDao
 *  com.alaric.norris.exercises.ultimatefibonacci.db
 *  Function:       ${TODO}
 *  date            author
 *  *****************************************************
 *  2016/9/20 020         AlaricNorris
 *  Copyright (c) 2016, TNT All Rights Reserved.
 */
package com.alaric.norris.exercises.ultimatefibonacci.db;

import android.content.Context;

import com.alaric.norris.exercises.ultimatefibonacci.entity.Fibonacci;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
/**
 @formatter:off ClassName:      FibonacciDao
 @formatter:off Function:       ${TODO}  ADD FUNCTION
 @formatter:off Contact:        Norris.sly@gmail.com
 @formatter:off @author         AlaricNorris
 @formatter:off @version        Ver 1.0
 @formatter:off @since          I used to be a programmer like you, then I took an arrow in the knee
 @formatter:off ***************************************************************************************************
 @formatter:off Modified By     AlaricNorris     2016/9/20 020    上午 10:25
 @formatter:off Modifications:  ${TODO}
 @formatter:off ***************************************************************************************************
 */
public class FibonacciDao {
    private Context mContext;
    private Dao< Fibonacci, Integer > mFibonacciDao;
    private DatabaseHelper mDatabaseHelper;
    public FibonacciDao ( Context context ) {
        this.mContext = context;
        try {
            mDatabaseHelper = DatabaseHelper.getHelper( mContext );
            mFibonacciDao = mDatabaseHelper.getDao( Fibonacci.class );
        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public void add ( Fibonacci inFibonacci ) {
        try {
            if ( mFibonacciDao.idExists( inFibonacci.getSeed() ) )
                return;
            mFibonacciDao.createIfNotExists( inFibonacci );
        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    public Fibonacci getFibonacci ( int id ) {
        Fibonacci article = null;
        try {
            article = mFibonacciDao.queryForId( id );

        }
        catch ( SQLException e ) {
            e.printStackTrace();
        }
        return article;
    }

    public int getCount () {
        int count = 0;
        try {
            count = mFibonacciDao.queryForAll().size();
        }
        catch ( SQLException inE ) {
            inE.printStackTrace();
        }
        return count;
    }
}

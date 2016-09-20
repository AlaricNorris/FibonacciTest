/**
 *  DatabaseHelper
 *  com.alaric.norris.exercises.fibonacci.db
 *  Function:       ${TODO}
 *  date            author
 *  *****************************************************
 *  2016/9/9 009         AlaricNorris
 *  Copyright (c) 2016, TNT All Rights Reserved.
 */
package com.alaric.norris.exercises.ultimatefibonacci.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.alaric.norris.exercises.ultimatefibonacci.entity.Fibonacci;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
/**
 @formatter:off ClassName:      DatabaseHelper
 @formatter:off Function:       ${TODO}  ADD FUNCTION
 @formatter:off Contact:        Norris.sly@gmail.com
 @formatter:off @author         AlaricNorris
 @formatter:off @version        Ver 1.0
 @formatter:off @since          I used to be a programmer like you, then I took an arrow in the knee
 @formatter:off ***************************************************************************************************
 @formatter:off Modified By     AlaricNorris     2016/9/9 009    上午 09:51
 @formatter:off Modifications:  ${TODO}
 @formatter:off ***************************************************************************************************
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME = "fibonacci.db";
    private static DatabaseHelper instance;
    /**
     * FibonacciDao ，每张表对于一个
     */
    private Dao< Fibonacci, Integer > FibonacciDao;
    public DatabaseHelper ( Context context ) {
        super( context, TABLE_NAME, null, 2 );
    }
    /**
     * 单例获取该Helper
     *
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper ( Context context ) {
        if ( instance == null ) {
            synchronized ( DatabaseHelper.class ) {
                if ( instance == null )
                    instance = new DatabaseHelper( context );
            }
        }

        return instance;
    }
    @Override
    public void onCreate ( SQLiteDatabase database, ConnectionSource connectionSource ) {
        try {
            TableUtils.createTable( connectionSource, Fibonacci.class );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade (
            SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
            int newVersion
    ) {
        try {
            TableUtils.dropTable( connectionSource, Fibonacci.class, true );
            onCreate( database, connectionSource );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
    /**
     * 获得userDao
     *
     * @return
     */
    public Dao< Fibonacci, Integer > getFibonacciDao () throws Exception {
        if ( FibonacciDao == null ) {
            FibonacciDao = getDao( Fibonacci.class );
        }
        return FibonacciDao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close () {
        super.close();
        FibonacciDao = null;
    }
}

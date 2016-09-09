/**
 *  Fibonacci
 *  com.alaric.norris.exercises.fibonacci.entity
 *  Function:       ${TODO}
 *  date            author
 *  *****************************************************
 *  2016/9/9 009         AlaricNorris
 *  Copyright (c) 2016, TNT All Rights Reserved.
 */
package com.alaric.norris.exercises.fibonacci.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigInteger;
/**
 @formatter:off ClassName:      Fibonacci
 @formatter:off Function:       ${TODO}  ADD FUNCTION
 @formatter:off Contact:        Norris.sly@gmail.com
 @formatter:off @author         AlaricNorris
 @formatter:off @version        Ver 1.0
 @formatter:off @since          I used to be a programmer like you, then I took an arrow in the knee
 @formatter:off ***************************************************************************************************
 @formatter:off Modified By     AlaricNorris     2016/9/9 009    上午 09:45
 @formatter:off Modifications:  ${TODO}
 @formatter:off ***************************************************************************************************
 */
@DatabaseTable ( tableName = "fibonacci" )
public class Fibonacci {
    @DatabaseField ( unique = true, columnName = "seed", canBeNull = false )
    private Integer seed;

    @DatabaseField ( columnName = "result", canBeNull = false )
    private BigInteger result;

    public Fibonacci ( Integer inSeed, BigInteger inResult ) {
        seed = inSeed;
        result = inResult;
    }
    public Integer getSeed () {
        return seed;
    }
    public void setSeed ( Integer inSeed ) {
        seed = inSeed;
    }
    public BigInteger getResult () {
        return result;
    }
    public void setResult ( BigInteger inResult ) {
        result = inResult;
    }
    @Override
    public String toString () {
        return "Fibonacci{" +
                "seed=" + seed +
                ", result=" + result +
                '}';
    }
}

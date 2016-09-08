/**
 *  FibonacciAdapter
 *  com.alaric.norris.exercises.calculateFibonacci
 *  Function:       ${TODO}
 *  date            author
 *  *****************************************************
 *  2016/9/7 007         AlaricNorris
 *  Copyright (c) 2016, TNT All Rights Reserved.
 */
package com.alaric.norris.exercises.fibonacci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alaric.norris.exercises.fibonacci.fibonacci.UniversalFibonacciLoader;

import java.util.List;
/**
 @formatter:off ClassName:      FibonacciAdapter
 @formatter:off Function:       ${TODO}  ADD FUNCTION
 @formatter:off Contact:        Norris.sly@gmail.com
 @formatter:off @author         AlaricNorris
 @formatter:off @version        Ver 1.0
 @formatter:off @since          I used to be a programmer like you, then I took an arrow in the knee
 @formatter:off ***************************************************************************************************
 @formatter:off Modified By     AlaricNorris     2016/9/7 007    下午 12:35
 @formatter:off Modifications:  ${TODO}
 @formatter:off ***************************************************************************************************
 */
public class FibonacciAdapter extends BaseAdapter {
    private Context mContext;
    private List< Integer > mList;
    public FibonacciAdapter ( Context inContext, List< Integer > inList ) {
        mContext = inContext;
        mList = inList;
    }
    @Override
    public int getCount () {
        return mList == null ? 0 : mList.size();
    }
    @Override
    public Object getItem ( int position ) {
        return mList == null ? null : mList.get( position );
    }
    @Override
    public long getItemId ( int position ) {
        return position;
    }
    @Override
    public View getView ( int position, View convertView, ViewGroup parent ) {
        ViewHolder holder = null;
        if ( null == convertView ) {
            convertView = ( ( LayoutInflater ) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE ) ).inflate(
                    R.layout.listitem_fibonacci, null );
            holder = new ViewHolder();
            holder.mTextView = ( TextView ) convertView.findViewById( R.id.text_fibonacci );
            convertView.setTag( holder );
        }
        else {
            holder = ( ViewHolder ) convertView.getTag();
        }
        Integer bean = mList.get( position );
        holder.mTextView.setText( bean + "^2 FibonacciCalculator Number calculating..." );
        holder.mTextView.setTag( Integer.valueOf( ( ( int ) ( Math.pow( bean, 2 ) ) ) ) );
        UniversalFibonacciLoader.getInstance()
                                .displayFibonacciNumber(
                                        Integer.valueOf( ( ( int ) ( Math.pow( bean, 2 ) ) ) ),
                                        holder.mTextView
                                );
        return convertView;
    }

    public static final class ViewHolder {
        TextView mTextView;

    }
}

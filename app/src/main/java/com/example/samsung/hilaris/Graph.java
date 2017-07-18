package com.example.samsung.hilaris;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.lang.reflect.Array;

/**
 * Created by js960 on 2017-07-17.
 */

public class Graph extends AppCompatActivity{

    Series addLineSeriesData(int array[], String color, String title)
    {
        LineGraphSeries<DataPoint> Series = new LineGraphSeries<>(new DataPoint[] { });
        for(int index =0 ; index < array.length; index++ )
        {
            Series.appendData(new DataPoint(index, array[index]), true, 10000);
        }
        Series.setAnimated(true);
        Series.setColor(Color.parseColor(color));
        Series.setTitle(title);
        return Series;
    }

    //Method overloading
    Series addLineSeriesData(int array[], String color)
    {
        LineGraphSeries<DataPoint> Series = new LineGraphSeries<>(new DataPoint[] { });
        for(int index =0 ; index < array.length; index++ )
        {
            Series.appendData(new DataPoint(index, array[index]), true, 10000);
        }
        Series.setAnimated(true);
        Series.setColor(Color.parseColor(color));
        return Series;
    }

    Series addBarSeriesData(int array[], String color, String title)
    {
        BarGraphSeries<DataPoint> Series = new BarGraphSeries<>(new DataPoint[] { });
        for(int index =0 ; index < array.length; index++ )
        {
            Series.appendData(new DataPoint(index, array[index]), true, 10000);
        }
        Series.setAnimated(true);
        Series.setColor(Color.parseColor(color));
        Series.setTitle(title);
        Series.setSpacing(50);
        return Series;
    }

    //Method overloading
    Series addBarSeriesData(int array[], String color)
    {
        BarGraphSeries<DataPoint> Series = new BarGraphSeries<>(new DataPoint[] { });
        for(int index =0 ; index < array.length; index++ )
        {
            Series.appendData(new DataPoint(index, array[index]), true, 10000);
        }
        Series.setAnimated(true);
        Series.setColor(Color.parseColor(color));
        Series.setSpacing(50);
        return Series;
    }
}

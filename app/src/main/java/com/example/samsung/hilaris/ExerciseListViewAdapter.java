package com.example.samsung.hilaris;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;


/**
 * ExerciseListVeiwAdapter class is an adapter class for Exercises_Select
 */



public class ExerciseListViewAdapter extends BaseAdapter {

    private Context context;
    private int layoutId;
    private ArrayList<ExerciseItem> list;
    private LayoutInflater inflater;//레이아웃 xml파일을 자바객체로 변환하기 위한객체


    public ExerciseListViewAdapter(Context context, int layoutId, ArrayList<ExerciseItem> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
        //LayoutInflater 객체 얻어오기(레이아웃xml파일을 자바객체로 변환하기 위해서)
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //항목의 갯수 반환
    @Override
    public int getCount() {
        return list.size();
    }

    //position에 해당하는 항목 반환
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    //항목에 해당하는 아이디 반환
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "detailInfo_listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.exercise_item, parent, false);
        }


        ExerciseItem item = list.get(position);

        WebView wv = (WebView) convertView.findViewById(R.id.webview);
        wv.setFocusable(false);
        wv.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR); // 화면을 유지

        // set the scale
        wv.setInitialScale(35); // 35%

        //initialScale to fit
        wv.getSettings().setUseWideViewPort(true);


        if (wv != null)  wv.loadUrl( item.getIconID() );


        //텍스트뷰에 이름 넣기
        TextView txt=(TextView)convertView.findViewById(R.id.txt);
        txt.setText(item.getName());

        //텍스트뷰에 타입 넣기
        TextView type=(TextView)convertView.findViewById(R.id.Type);
        type.setText(item.getType());


        return convertView;
    }



}


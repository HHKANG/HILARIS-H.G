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


// 사용자 정의 어댑터 클래스 만들기
// 어댑터(데이터(모델)+보여줄형식(레이아웃))
public class ExerciseListViewAdapter extends BaseAdapter {
    private Context context;
    private int layoutId;
    private ArrayList<ExerciseItem> list;
    private LayoutInflater inflater;//레이아웃 xml파일을 자바객체로 변환하기 위한객체

    /**
     * @param context  : 컨텍스트
     * @param layoutId : 보여줄 레이아웃
     * @param list     : 보여줄 데이터를 갖고있는 배열
     */
    public ExerciseListViewAdapter(Context context, int layoutId, ArrayList<ExerciseItem> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
        //LayoutInflater 객체 얻어오기(레이아웃xml파일을 자바객체로 변환하기 위해서)
        inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    //각 항목뷰+에 어떻게 보여질지를 정의
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {//항목뷰가 한번도 보여진적이 없는 경우
            //레이아웃(item1.xml)을 자바객체로 변환하기
            convertView = inflater.inflate(layoutId, parent, false);
        }
        //////  convertView에 어떻게 보여질지 설정   ////////////

        final ExerciseItem item = list.get(position);


        //텍스트뷰에 이름 넣기
        TextView txt=(TextView)convertView.findViewById(R.id.txt);
        txt.setText(item.getName());

        //텍스트뷰에 타입 넣기
        TextView type=(TextView)convertView.findViewById(R.id.Type);
        type.setText(item.getType());


        WebView wv = (WebView) convertView.findViewById(R.id.webview);
        wv.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR); // 화면을 유지

        // set the scale
        wv.setInitialScale(35); // 35%

        //initialScale to fit
        wv.getSettings().setUseWideViewPort(true);

        wv.setHorizontalScrollBarEnabled(false);//가로스크롤없애기
        wv.setVerticalScrollBarEnabled(false);//세로스크롤없애기


        if (wv != null) wv.loadUrl( item.getIconID() );
        wv.setFocusable(false);

        return convertView;
    }





}


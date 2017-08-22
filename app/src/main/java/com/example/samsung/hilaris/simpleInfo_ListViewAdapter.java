package com.example.samsung.hilaris;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;

/**
 *sipleInfo_ListViewAdapter class is an adapter class for Simpelinfo.
 *This listview item is in ListViewItem.
 *There are 6 items.
 *item 1 is TestDate , item 2 is BMI
 *item 3 is TestType , item 4 is Waist
 *item 5 is Power , item 6 is MedifitTestGUID
 */

public class simpleInfo_ListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;

    // ListViewAdapter의 생성자
    public simpleInfo_ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_list_view_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView textview1 = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView textview2 = (TextView) convertView.findViewById(R.id.textView2) ;
        TextView textview3 = (TextView) convertView.findViewById(R.id.textView3) ;
        TextView textview4 = (TextView) convertView.findViewById(R.id.textView4) ;
        TextView textview5 = (TextView) convertView.findViewById(R.id.textView5) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        textview1.setText(listViewItem.getinfo1());
        textview2.setText(String.valueOf(listViewItem.getinfo2()));
        textview3.setText(listViewItem.getinfo3());
        textview4.setText(String.valueOf(listViewItem.getinfo4()));
        textview5.setText(String.valueOf(listViewItem.getinfo5()));
        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }
    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }
    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    //String info1, double info2, String info3, double info4, double info5, String info6
    public void addItem(JSON object) throws JSONException {
        ListViewItem item = new ListViewItem();
        item.setinfo1( object.getTestDate());
        item.setinfo2( object.getBMI());
        item.setinfo3(object.getTestType());
        item.setinfo4( object.getWaist());
        item.setinfo5(object.getPower());
        item.setinfo6(object.getMedifitTestGUID());
        listViewItemList.add(item);
    }
    public void delItem(){
        listViewItemList.clear();
    }
}
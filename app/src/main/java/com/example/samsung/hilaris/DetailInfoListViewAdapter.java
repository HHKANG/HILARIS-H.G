package com.example.samsung.hilaris;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * DetailInfoListViewAdapter class is an adapter class for DetailInfo
 * This listview item layout is detailinfo_listview_item.
 */

public class DetailInfoListViewAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> DetailInfoListViewItemList = new ArrayList<ListViewItem>() ;

    // DetailInfoViewAdapter의 생성자
    public DetailInfoListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return DetailInfoListViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "detailInfo_listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.detailinfo_listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView objectName = (TextView) convertView.findViewById(R.id.textview_objectname) ;


        // Data Set(DetailInfoListViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = DetailInfoListViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        objectName.setText(listViewItem.getinfo_detail());
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
        return DetailInfoListViewItemList.get(position) ;
    }
    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String info1) {
        ListViewItem item = new ListViewItem();
        item.setObjectName(info1);
        DetailInfoListViewItemList.add(item);
    }
    public void delItem(){
        DetailInfoListViewItemList.clear();
    }


}

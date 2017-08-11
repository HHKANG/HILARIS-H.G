package com.example.samsung.hilaris;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ExerciseListviewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<String> mParentList;
    private ArrayList<ChildListData> mChildList;
    private ChildListViewHolder mChildListViewHolder;
    private HashMap<String, ArrayList<ChildListData>> mChildHashMap;
    // CustomExpandableListViewAdapter 생성자
    public ExerciseListviewAdapter(Context context, ArrayList<String> parentList, HashMap<String, ArrayList<ChildListData>> childHashMap){
        this.mContext = context;
        this.mParentList = parentList;
        this.mChildHashMap = childHashMap;
    }

    /* ParentListView에 대한 method */
    @Override
    public String getGroup(int groupPosition) { // ParentList의 position을 받아 해당 TextView에 반영될 String을 반환
        return mParentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() { // ParentList의 원소 개수를 반환
        return mParentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) { // ParentList의 position을 받아 long값으로 반환
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) { // ParentList의 View
        if(convertView == null){
            LayoutInflater groupInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // ParentList의 layout 연결. root로 argument 중 parent를 받으며 root로 고정하지는 않음
            convertView = groupInfla.inflate(R.layout.parent_listview, parent, false);
        }

        // ParentList의 Layout 연결 후, 해당 layout 내 TextView를 연결
        TextView parentText = (TextView)convertView.findViewById(R.id.parenttext);
        parentText.setText(getGroup(groupPosition));
        return convertView;
    }

    /* 여기서부터 ChildListView에 대한 method */
    @Override
    public ChildListData getChild(int groupPosition, int childPosition) { // groupPostion과 childPosition을 통해 childList의 원소를 얻어옴
        return this.mChildHashMap.get(this.mParentList.get(groupPosition)).get(childPosition);

    }

    @Override
    public int getChildrenCount(int groupPosition) { // ChildList의 크기를 int 형으로 반환
        return this.mChildHashMap.get(this.mParentList.get(groupPosition)).size();

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) { // ChildList의 ID로 long 형 값을 반환
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // ChildList의 View. 위 ParentList의 View를 얻을 때와 비슷하게 Layout 연결 후, layout 내 TextView, ImageView를 연결
        ChildListData childData = (ChildListData)getChild(groupPosition, childPosition);
        if(convertView == null){
            LayoutInflater childInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childInfla.inflate(R.layout.child_listview, null);

            mChildListViewHolder = new ChildListViewHolder();
            mChildListViewHolder.mListPicHolder = (ImageView)convertView.findViewById(R.id.child_item_icon);
            mChildListViewHolder.mListText01Holder = (TextView)convertView.findViewById(R.id.childtext01);
            mChildListViewHolder.mListText02Holder = (TextView)convertView.findViewById(R.id.childtext02);

            convertView.setTag(mChildListViewHolder);
        } else{
            mChildListViewHolder = (ChildListViewHolder)convertView.getTag();
        }

        mChildListViewHolder.mListText01Holder.setText(getChild(groupPosition, childPosition).mText01);
        mChildListViewHolder.mListText02Holder.setText(getChild(groupPosition, childPosition).mText02);
        mChildListViewHolder.mListPicHolder.setImageDrawable(getChild(groupPosition, childPosition).mListPic);
        return convertView;

    }

    @Override
    public boolean hasStableIds() { return true; } // stable ID인지 boolean 값으로 반환

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { return true; } // 선택여부를 boolean 값으로 반환

}




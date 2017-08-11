package com.example.samsung.hilaris;


import android.graphics.drawable.Drawable;

/**
 * Created by minnie on 2017-08-11.
 */

class ChildListData {

    /* CustomListView가 담을 객체에 대한 Class 생성 */
    // ImageView에 상응
    public Drawable mListPic;
    // TextView01에 상응
    public String mText01;
    public String mText02;


    public ChildListData(Drawable mListPic, String mText01,String mText02) {

        this.mListPic=mListPic;
        this.mText01=mText01;
        this.mText02=mText02;

    }


    // TextView02에 상응
  //  public String mTest02;

}

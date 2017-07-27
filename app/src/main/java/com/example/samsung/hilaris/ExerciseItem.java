package com.example.samsung.hilaris;

import android.support.v7.app.AppCompatActivity;

public class ExerciseItem extends AppCompatActivity {
    private int iconID;//아이콘 리소스 아이디 저장(이미지)
    private String Name;//텍스트뷰에 보여질 이름
    private String Type;//텍스트뷰에 보여질 타입

    public ExerciseItem() {
        // TODO Auto-generated constructor stub
    }
    public ExerciseItem(int iconID,String Name,String Type){
        this.iconID=iconID;
        this.Name=Name;
        this.Type=Type;

    }
    public int getIconID() {
        return iconID;
    }
    public void setIconID(int iconID) {
        this.iconID = iconID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getType() {
        return Type;
    }
    public void setType(String Type) {
        this.Type = Type;
    }
}


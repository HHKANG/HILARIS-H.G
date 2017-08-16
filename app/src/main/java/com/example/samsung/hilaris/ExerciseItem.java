package com.example.samsung.hilaris;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ExerciseItem extends AppCompatActivity {
    //private ImageView imageView;
    private String iconID;//아이콘 리소스 아이디 저장(이미지)
    private String Name;//텍스트뷰에 보여질 이름
    private String Phase;//텍스트뷰에 보여질 타입

    public ExerciseItem(String iconID, String title, String phase) {
        this.iconID=iconID;
        this.Name=Name;
        this.Phase=Phase;
    }
    /*
    public ExerciseItem(ImageView imageView,String Name,String Phase){
        //this.iconID=iconID;
        this.imageView=imageView;
        this.Name=Name;
        this.Phase=Phase;

    }*/
    public String getIconID() {
        return iconID;
    }
    public void setIconID(String iconID) {
        this.iconID = iconID;
    }
   //public ImageView getImageView(){return imageView;}
    //public void setImageView(){this.imageView=imageView;}
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getPhase() {
        return Phase;
    }
    public void setPhase(String Phase) {
        this.Phase = Phase;
    }
}


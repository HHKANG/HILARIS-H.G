package com.example.samsung.hilaris;


public class ListViewItem {
    private String info1;//simple info
    private double info2;//simple info
    private String info3;//simple info
    private double info4;//simple info
    private double info5;//simple info
    private String info6;//simple info

    private int iconID;//운동 이미지
    private String Name;//운동 이름
    private String Type;//운동 타입

    String objectName;//Detail info



    public void setinfo1(String info1) {
        this.info1 = info1;
    }
    public String getinfo1() {return this.info1;}

    public void setinfo2(double info2) {
        this.info2 = info2 ;
    }
    public double getinfo2() {
        return this.info2;
    }

    public void setinfo3(String info3) {
        this.info3 = info3;
    }
    public String getinfo3() {
        return this.info3;
    }

    public void setinfo4(double info4) {
        this.info4 = info4;
    }
    public double getinfo4() {return this.info4; }

    public void setinfo5(double info5) {
        this.info5 = info5;
    }
    public double getinfo5() {return this.info5;}

    public void setinfo6(String info6) {
        this.info6 = info6;
    }
    public String getinfo6() {return this.info6;}



    public ListViewItem() {
    }


    public ListViewItem(int iconID,String Name,String Type){
        this.iconID=iconID;
        this.Name=Name;
        this.Type=Type;

    }

    public int getIconID() {
        return iconID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getType() {return Type;}


    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
    public String getinfo() {return this.objectName;}



}



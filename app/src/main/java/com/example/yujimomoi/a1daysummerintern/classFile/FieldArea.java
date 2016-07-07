package com.example.yujimomoi.a1daysummerintern.classFile;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class FieldArea {
    private int field_width;
    private int field_height;
    private String line_color;

    public FieldArea() {
        field_width = 0;
        field_height = 0;
        line_color = null;
    };

    public void initField(int width,int height) {
        field_width = width;
        field_height = height;
    };

    public void initFielid() {};

    public FieldArea getFieldSise() {
        return null;
    };

    public int getFieldWidth() {
        return field_width;
    };

    public int getFieldHeight() {
        return field_height;
    };

    public void getFieldSize() {};

    public void drawLine(Point point) {};
}

package com.nileshp.multiphotopicker.photopicker;

import java.util.ArrayList;

public class Constants {
    public static ArrayList<String> FORMAT_IMAGE = new ImageTypeList();

    static class ImageTypeList extends ArrayList<String> {
        ImageTypeList() {
            add(".PNG");
            add(".JPEG");
            add(".jpg");
            add(".png");
            add(".jpeg");
            add(".JPG");
            add(".GIF");
            add(".gif");
        }
    }
}

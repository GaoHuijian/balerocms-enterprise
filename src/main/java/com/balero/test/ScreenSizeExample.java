package com.balero.test;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

public class ScreenSizeExample {
    public static void main(String[] args) throws IOException{
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize.getWidth() + " x " + screenSize.getHeight());
    }
}
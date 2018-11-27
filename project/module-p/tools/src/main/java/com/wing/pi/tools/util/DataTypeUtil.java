package com.wing.pi.tools.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;
public class DataTypeUtil {


    public static void main(String[] args) {
        Student s = new Student();
        List<Integer> channelApplication;
        List<Integer> allchannelApplication = null;
        channelApplication = new ArrayList<>();
        channelApplication.add(3);
        channelApplication.add(1);
        channelApplication.add(6);
        channelApplication.add(4);
        channelApplication.add(9);
        channelApplication.add(12);
        System.out.println("----1----"+channelApplication.size());
        if ( channelApplication.size() >5) {
            allchannelApplication = channelApplication.subList(0, 4);
            System.out.println("----2----"+allchannelApplication.size());
        }
        System.out.println("----1----"+channelApplication.size());
        System.out.println("----2----"+allchannelApplication.size());

    }



}

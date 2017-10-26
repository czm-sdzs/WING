package javo;/**
 * Created by Administrator on 2017/10/25.
 */

import java.util.ArrayList;

/**
 * description 集合
 * author stephen.cui
 * date 2017/10/25 21:35
 **/
public class Collections {

    public static void main(String[] args) {
        String[] Array = {"a", "b", "c", "d", "e"};
        for (int j = 0; j < Array.length; j++) {
            System.out.print(Array[j] + " ");
        }
        reverseArray1(Array);// 使用集合ArrayList实现反转
        for (int j = 0; j < Array.length; j++) {
            System.out.print(Array[j] + " ");
        }

    }
    private static void reverseArray1(String[] Array) {
        ArrayList<String> array_list = new ArrayList<String>();
        for (int i = 0; i < Array.length; i++) {
            array_list.add(Array[Array.length - i - 1]);
        }
        Array = array_list.toArray(Array);
    }

}

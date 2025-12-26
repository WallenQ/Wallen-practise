package com.wallen.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author qianwenlong
 * @Date 2025/12/24 16:18
 */
public class InputUtil {

    private static final BufferedReader KEYBOARD_INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static String getString(String prompt) {
        String returnData = null;
        boolean flag = true;
        while (flag) {
            System.out.print(prompt);
            try {
                returnData = KEYBOARD_INPUT.readLine();
                if (returnData == null || "".equals(returnData)) {
                    System.err.println("输入的数据不允许为空");
                } else {
                    flag = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnData;
    }
}

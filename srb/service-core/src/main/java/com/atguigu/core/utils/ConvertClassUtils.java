package com.atguigu.core.utils;

import java.util.ArrayList;
import java.util.List;

// 将Object类型转换为List<Object>
public class ConvertClassUtils {
    // 转换
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

}

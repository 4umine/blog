package com.coderbike.utils.http;

import org.apache.commons.collections.MapUtils;

import java.util.*;

/**
 * 描述
 *
 * @author LBG - 2017/9/30 0030
 */
public class MapUtil {

    public static <K, V> Map<K, V> sortMapByValue(Map<K, V> map, Comparator<Map.Entry<K, V>> comparator) {
        if (MapUtils.isEmpty(map) || comparator == null) {
            return null;
        }
        Map<K, V> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<K, V>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, comparator);

        Iterator<Map.Entry<K, V>> iter = entryList.iterator();
        Map.Entry<K, V> tmpEntry;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
}

package com.lyn.test;

import org.apache.storm.shade.org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<Integer, Integer> map1 = new HashedMap();
        map1.put(1, 1);
        map1.put(2, 2);
        map1.put(3, 3);
        MapTest test = new MapTest();
        test.changeMap(map1);
        test.showMap(map1);
    }

    public void changeMap(Map<Integer, Integer> map) {
//            map.remove(1);
        Map<Integer, Integer> map2 = new HashMap<>();
        map2=map;
        map2.put(4, 4);
    }

    public void showMap(Map map) {
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

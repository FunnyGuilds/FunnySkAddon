package pl.funnyskaddon.core;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.collect.Lists;

public class UniversalObjectSorter {

    //toSort - lista z klasami np. Userów
    //topToBottom - sortowanie od największej do najmniejszej true; od najmniejszej do najwi�kszej false
    //getterName - nazwa gettera, wg którego obiekty zostaną posortowane np. "getLvl"
    //getterArgs - opcjonalne argumenty które mozna wprowadzić do gettera np. getLvl(true, 12) => true, 12; można zostawić puste
    public static List<Object> sort(List<Object> toSort, boolean topToBottom, String getterName) {
        Method getter = null;
        for(Method m : ((List<Object>) toSort).get(0).getClass().getMethods())
            if(m.getName().equals(getterName)) {
                getter = m;
                break;
            }
        try {
            if(getter.getReturnType() == Double.class || getter.getReturnType() == double.class) {
                HashMap<Object, Double> map = new HashMap<Object, Double>();
                DoubleValueComparator vc = new DoubleValueComparator(map);
                TreeMap<Object, Double> sorted_map = new TreeMap<Object, Double>(vc);
                for(Object u : toSort)
                    map.put(u, (Double) getter.invoke(u));
                sorted_map.putAll(map);
                if(topToBottom)
                    return Lists.newArrayList(sorted_map.keySet());
                return Lists.reverse(Lists.newArrayList(sorted_map.keySet()));
            } else if(getter.getReturnType() == Integer.class || getter.getReturnType() == int.class) {
                HashMap<Object, Integer> map = new HashMap<Object, Integer>();
                IntegerValueComparator vc = new IntegerValueComparator(map);
                TreeMap<Object, Integer> sorted_map = new TreeMap<Object, Integer>(vc);
                for(Object u : toSort)
                    map.put(u, (Integer) getter.invoke(u));
                sorted_map.putAll(map);
                if(topToBottom)
                    return Lists.newArrayList(sorted_map.keySet());
                return Lists.reverse(Lists.newArrayList(sorted_map.keySet()));
            }
        } catch(Exception ex) {}
        return null;
    }

    private static class DoubleValueComparator implements Comparator<Object> {
        Map<Object, Double> base;

        public DoubleValueComparator(Map<Object, Double> base) {
            this.base = base;
        }

        public int compare(Object a, Object b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private static class IntegerValueComparator implements Comparator<Object> {
        Map<Object, Integer> base;

        public IntegerValueComparator(Map<Object, Integer> base) {
            this.base = base;
        }

        public int compare(Object a, Object b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}


package pl.funnyskaddon.core.sort;

import net.dzikoysk.funnyguilds.basic.user.User;

import java.util.*;

public class UserSort {

    public static List<User> sort(Set<User> toSort) {
        TreeSet<User> treeSet = new TreeSet<>(new ValueComparator());
        treeSet.addAll(toSort);
        return new ArrayList<>(treeSet);
    }

    private static class ValueComparator implements Comparator<User> {

        ValueComparator() {}

        @Override
        public int compare(User a, User b) {
            if(a.getRank().getPoints() < b.getRank().getPoints()){
                return 1;
            } else{
                if(a.getRank().getPoints() == b.getRank().getPoints()){
                    int compare = a.getName().compareTo(b.getName());

                    if (compare < 0 || compare == 0) {
                        return -1;
                    } else if (compare > 0) {
                        return 1;
                    }
                } else{
                    return -1;
                }
                return -1;
            }
        }
    }
}

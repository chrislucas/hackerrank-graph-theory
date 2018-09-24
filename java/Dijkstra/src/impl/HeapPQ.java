package impl;

import java.util.*;

public class HeapPQ {

    private static class Data implements Comparable<Data> {
        private int value;
        public Data(int value) {
            this.value = value;
        }
        @Override
        public int compareTo(Data that) {
            return value - that.value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private static class MinHeap implements Comparator<Data> {
        @Override
        public int compare(Data p, Data q) {
            return p.compareTo(q);
        }
    }

    private static class MaxHeap implements Comparator<Data> {
        @Override
        public int compare(Data p, Data q) {
            return q.compareTo(p);
        }
    }

    // 8,9,1,2,3,4,5
    private static final Data [] values = {
        new Data(8), new Data(9), new Data(1)
            , new Data(2), new Data(3), new Data(4), new Data(5)
    };

    private static void testMinHeap() {
        PriorityQueue<Data> pq = new PriorityQueue<>(new MinHeap());
        System.out.println("Min heap");
        for (Data data : values) {
            pq.add(data);
            print(pq.iterator());
        }
        System.out.println("");

        System.out.println("ASC sort");
        // asc sort
        Iterator<Data> iterator = pq.iterator();
        while (iterator.hasNext()) {
            System.out.printf("%s ", iterator.next());
            iterator.remove();
        }
        System.out.println("");

        //minHeap.addAll(Arrays.asList(values));
        //print(minHeap.iterator());
    }

    private static void testMaxHeap() {
        PriorityQueue<Data> pq = new PriorityQueue<>(new MaxHeap());
        System.out.println("Max heap");
        for (Data data : values) {
            pq.add(data);
            print(pq.iterator());
        }

        System.out.println("");
        System.out.println("DESC sort");
        // DESC sort
        Iterator<Data> iterator = pq.iterator();
        while (iterator.hasNext()) {
            System.out.printf("%s ", iterator.next());
            iterator.remove();
        }
        //maxHeap.addAll(Arrays.asList(values));
        //print(maxHeap.iterator());
    }

    private static void print(Iterator<Data> iterator) {
        while (iterator.hasNext()) {
            System.out.printf("%s ", iterator.next());
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        testMinHeap();
        testMaxHeap();
    }
}

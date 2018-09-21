package impl;

import problems.hackerearth.Dijkstra;

import java.util.Comparator;


/**
 * All pairs shortest path tracking path
 * */
public class ApspTrackingPath {

    public static class Edge implements Comparable<Edge> {
        int u, v, w;
        private Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
        @Override
        public int compareTo(Edge that) {
            return this.w - that.w;
        }
    }

    public static class ComparatorMinPQ implements Comparator<Edge> {
        @Override
        public int compare(Edge p, Edge q) {
            return p.compareTo(q);
        }
    }

    public static void main(String[] args) {

    }
}

package com.example.rappi_ando.model;

import java.util.ArrayList;

public interface Igraph<T> {

    public void addvertex(int key, T value);

    public void addedge(int key, int adj, int w);

    public ArrayList<Node<T>> BFS(int key);

    public void DFS();

    public void DFSVisit(Node<T>u);

    public ArrayList<Integer> dijkstra(int source);

    public int[][] floydWarsall();

    public int[] prim(int r, int w);

}

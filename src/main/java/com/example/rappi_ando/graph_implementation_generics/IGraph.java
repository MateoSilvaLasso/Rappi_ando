package com.example.rappi_ando.graph_implementation_generics;

import java.util.ArrayList;
import java.util.Collection;

public interface IGraph<T> {

    public void addVertex(int key, T value);

    public void addEdge(int key, int adj, int w);

    public ArrayList<Node<T>> BFS(int key);

    public int DFS();

    public void DFSVisit(Node<T>u);

    public ArrayList<Integer> dijkstra(int source);

    public int[][] floydWarsall();

    public int[] prim(int r, int w);

    public ArrayList<Node<T>> getGraph();

    public Node<T> searchNode(int key);
}


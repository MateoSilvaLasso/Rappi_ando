package com.example.rappi_ando.graph_implementation_generics;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListTest{

    private IGraph<Integer> graph;

    public void setUp1(){
        graph = new AdjacencyList<Integer>();
    }

    public void setUp2(){
        graph = new AdjacencyList<Integer>();
        //Adding the vertex
        graph.addVertex(1, 1);
        graph.addVertex(2, 2);
        graph.addVertex(3, 3);
    }

    public void setUp3(){
        graph = new AdjacencyList<Integer>();
        //Adding the vertex
        graph.addVertex(1, 1);
        graph.addVertex(2, 2);
        graph.addVertex(3, 3);

        //Adding the edges
        graph.addEdge(1, 2 , 10);
        graph.addEdge(2, 3 , 5);
        graph.addEdge(3, 1 , 20);
    }

    public void setUp4(){
        graph = new AdjacencyList<Integer>();
        //Adding the vertex
        graph.addVertex(1, 1);
        graph.addVertex(2, 2);
        graph.addVertex(3, 3);

        //Adding the edges
        graph.addEdge(2, 1 , 10);
        graph.addEdge(2, 3 , 5);
    }

    void setUp5(){
        graph = new AdjacencyList<Integer>();
        //Adding the vertex
        graph.addVertex(0, 1);
        graph.addVertex(1, 2);
        graph.addVertex(2, 3);

        //Adding the edges
        graph.addEdge(0, 1 , 10);
        graph.addEdge(1, 2 , 5);
    }

    // Tests of the addVertex method
    @Test
    public void addVertexTest1() {
        setUp1();
        graph.addVertex(1, 1);
        assertEquals(graph.getGraph().size(), 2);
    }
    @Test
    public void addVertexTest2() {
        setUp1();
        graph.addVertex(1, 1);
        try {
            graph.addVertex(1,4);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        assertEquals(graph.getGraph().size(), 2);
    }

    @Test
    public void addVertex3(){
        setUp1();
        graph.addVertex(1,1);
        graph.addVertex(2,2);
        graph.addVertex(3,3);
        assertTrue(graph.searchNode(2).getValue() == 2);
    }

    // Tests of the methods of addEdge
    @Test
    public void addEdgeTest1(){
        setUp2();
        graph.addEdge(3, 1, 10);
        assertEquals(graph.searchNode(3).getNodes().get(0).getValue(), 10);
    }
    @Test
    public void addEdgeTest2(){
        setUp2();
        graph.addEdge(3, 1, 10);
        graph.addEdge(3,1, 20);
        assertEquals(graph.searchNode(3).getNodes().get(1).getValue(), 20);
    }

    @Test
    public void addEdgeTest3(){
        setUp2();
        graph.addEdge(2, 1, -5);
        assertEquals(graph.searchNode(2).getNodes().get(0).getValue(), -5);
    }

    //Testing of the BFS method

    @Test
    public void BFSTest1() {
        setUp3();
        ArrayList<Node<Integer>> test = graph.BFS(1);
        ArrayList<Integer> expectedNodes = new ArrayList<>();
        expectedNodes.add(1);
        expectedNodes.add(2);
        expectedNodes.add(3);
        for (Node<Integer> n: test){
            expectedNodes.remove(n.getValue());
        }
        assertEquals(expectedNodes.size(), 0);
    }
    @Test
    public void BFSTest2() {
        setUp4();
        ArrayList<Node<Integer>> test = graph.BFS(1);
        assertEquals(test.get(0).getKey(), 1);
    }
    @Test
    public void BFSTest3() {
        setUp4();
        ArrayList<Node<Integer>> test = graph.BFS(2);
        assertEquals(test.get(0).getKey(), 2);
        assertEquals(test.get(1).getKey(), 1);
        assertEquals(test.get(2).getKey(), 3);
    }

    // Tests for the Method DFS
    //llego por quien lloraban

    @Test
    public void DFStest1(){
        setUp4();
        assertEquals(graph.DFS(),1);
    }

}
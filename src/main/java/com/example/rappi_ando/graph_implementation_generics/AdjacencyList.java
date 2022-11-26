package com.example.rappi_ando.graph_implementation_generics;
import java.util.ArrayList;
import java.util.*;

import com.example.rappi_ando.exceptions.RepeatedNodeException;
import javafx.util.*;

public class AdjacencyList<T> implements IGraph<T>{
    private ArrayList<Node<T>> graph;
    private int time;

    public AdjacencyList(){
        this.graph = new ArrayList<>();
        //graph.add(new Node<>());
    }

    @Override
    public void addVertex(int key, T value) throws RepeatedNodeException {
        Node<T> node= new Node(value,key);
        if(searchNode(key) != null){
            throw new RepeatedNodeException();
        }
        this.graph.add(node);
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        if(searchNode(from)==null || searchNode(to)==null) return;
        graph.get(from).addNode(searchNode(to),weight);
    }

    public Node<T> searchNode(int key){
        for(int i=0; i<graph.size(); i++){
            if(graph.get(i).getKey()==key){
                return graph.get(i);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Node<T>> BFS(int key) {
        ArrayList<Node<T>> arr= new ArrayList<>();
        Node<T> goal= searchNode(key);
        if(goal!=null){
            for(Node<T> u: graph){
                if(u!=goal){
                    u.setColor(0);
                    int a=0;
                    int b=((Integer)a).MAX_VALUE;
                    u.setD(b);
                    u.setPi(null);
                }
            }
            goal.setColor(1);
            goal.setD(0);
            goal.setPi(null);
            Queue<Node<T>> q= new LinkedList<>();
            q.add(goal);
            Node<T> u= new Node<>();
            while(!q.isEmpty()){
                u= q.poll();
                for(Pair<Node<T>,Integer>v: u.getNodes()){
                    if(v.getKey().getColor()==0) {
                        v.getKey().setColor(1);
                        v.getKey().setD(u.getD() + 1);
                        v.getKey().setPi(u);
                        q.add(v.getKey());
                    }
                }
                u.setColor(2);
                //System.out.print("("+u.getKey()+" "+u.getD()+") ");
                arr.add(u);
            }
        }
        return arr;
    }

    @Override
    public int DFS() {
        for(Node<T>u: graph){
            u.setColor(0);
            u.setPi(null);
        }
        time=0;
        int count=0;
        for(Node<T>u: graph){
            if(u.getColor()==0){
                count++;
                DFSVisit(u);
            }
        }
        return count;
    }

    @Override
    public void DFSVisit(Node<T> u) {
        time+=1;
        u.setD(time);
        u.setColor(1);
        for(Pair<Node<T>,Integer> v: u.getNodes()){
            if(v.getKey().getColor()==0){
                v.getKey().setPi(u);
                DFSVisit(v.getKey());
            }
        }
        u.setColor(2);
        time+=1;
        u.setForward(time);
    }

    @Override
    public ArrayList<Integer> dijkstra(int source) {
        ArrayList<Integer> dist= new ArrayList<>(graph.size()-1);
        ArrayList<Integer> prev= new ArrayList<>(graph.size()-1);
        for(int i=0; i<graph.size(); i++){
            dist.add(0);}

        dist.remove(source);
        dist.add(source,0);

        PriorityQueue<Integer>q=new PriorityQueue<>();
        Node<T> s= searchNode(source);
        for(Node<T> v:graph){
            if(v!=s){
                dist.remove(v.getKey());
                dist.add(v.getKey(),((Integer)1).MAX_VALUE);
            }
            prev.add(v.getKey(),null);
            q.add(dist.get(v.getKey()));
        }
        while(!q.isEmpty()){
            int u= q.peek(); q.poll();
            int value=-1;
            for(int i=0; i< dist.size(); i++){
                if(dist.get(i)==u){
                    value=i;
                    break;
                }
            }
            //System.out.println(dist);
            for(Pair<Node<T>,Integer>v: graph.get(value).getNodes()){
                int alt= dist.get(value)+v.getValue();
                if(alt<dist.get(v.getKey().getKey())){
                    q.remove(dist.get(v.getKey().getKey()));
                    dist.remove(v.getKey().getKey());
                    dist.add(v.getKey().getKey(),alt);
                    prev.remove(v.getKey().getKey());
                    prev.add(v.getKey().getKey(),u);
                    q.add(dist.get(v.getKey().getKey()));
                }
            }
        }

        return dist;

    }

    @Override
    public int[][] floydWarsall() {
        int[][] dist= new int[graph.size()][graph.size()];

        for(int i=0; i<dist.length;i++){
            for (int j=0; j<dist[0].length;j++){
                dist[i][j]=((Integer)1).MAX_VALUE;
            }
        }

        for(Node<T>v: graph){
            dist[v.getKey()][v.getKey()]=0;
        }

        for(Node<T>v:graph){
            for(Pair<Node<T>,Integer>u: v.getNodes()){
                dist[v.getKey()][u.getKey().getKey()]=u.getValue();
            }
        }

        for(int k=0; k<graph.size(); k++){
            for(int i=0; i< graph.size(); i++){
                for(int j=0; j< graph.size(); j++){
                    if(dist[i][j] > (dist[i][k] + dist[k][j]) && ((dist[i][k]) != Integer.MAX_VALUE && ((dist[k][j]) != Integer.MAX_VALUE))){
                        dist[i][j]=dist[i][k]+dist[k][j];
                    }
                }
            }
        }


        return dist;
    }

    @Override
    public int[] prim(int r, int w) {
        int[] key= new int[graph.size()];
        int [] color= new int[graph.size()];
        int [] pred= new int[graph.size()];
        for(Node<T>u: graph){
            key[u.getKey()]= ((Integer)1).MAX_VALUE;
            color[u.getKey()]=0;
        }

        key[r]=0;
        pred[r]=-1;
        PriorityQueue<Integer> q= new PriorityQueue<>();
        q.add(r);
        while(!q.isEmpty()){
            int u= q.poll();
            for(Pair<Node<T>,Integer> v: graph.get(u).getNodes()){
                if(color[v.getKey().getKey()]==0 && v.getValue()<key[v.getKey().getKey()] && v.getValue()!=((Integer)1).MAX_VALUE){
                    key[v.getKey().getKey()]= v.getValue();
                    pred[v.getKey().getKey()]= graph.get(u).getKey();
                }
            }
            color[u]= 1;
        }

        return key;
    }
    @Override
    public ArrayList<Node<T>> getGraph(){
        return graph;
    }
}
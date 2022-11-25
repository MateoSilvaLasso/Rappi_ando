package com.example.rappi_ando.model;

import java.util.*;


public class Graph{

    private LinkedList<Node> nodes = new LinkedList();
    private Dijkstra adj = new Dijkstra(true);

    void addNode(double x,double y,String name){
        Node temp = new Node(x,y,name);
        nodes.add(temp);
        System.out.println(x+" y "+y);
        System.out.println(("Node Added Successfully"));
    }
    Node getNode(String from){
        for(Node i:nodes){
            if(i.name.equals(from)){
                return i;
            }
        }
        return null;
    }
    String SearchNode(String node) {
        for(Node i:nodes){
            if(i.name.equals(node)){
                return ("X coordinate:"+i.x+"\n"+"Y coordinate:"+i.y);
            }
        }
        return ("Node not Found");
    }

    String DeleteNode(String node){
        for(Node n:nodes){
            if(n.name.equals(node)){
//                nodes.remove(n);
                adj.DeleteNo(n);
                nodes.remove(n);
                return  "Node Deleted";
            }
        }
        return "Node Doesn't exist";
    }

    String ModifyNode(String node,double x,double y){
        for(Node i:nodes){
            if(i.name.equals(node)){
                i.x = x;
                i.y = y;
                return ("Node Modified");
            }
        }
        return ("Node not Found");
    }

    String addEdge(String from,String to,double weight){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null)
            return ("Form node not found");
        else if(toNode == null)
            return ("To node not Found");
        else {
            adj.addEdge(fromNode, toNode, weight);
            return ("Edge added Successfully");
        }
    }

    String SearchEdge(String from,String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null || toNode == null) {
            return ("Edge Not Found");
        }
        else
        {
            if(adj.hasEdge(fromNode, toNode)){
                return ("Edge Found \n"+"Weight is "+adj.Weight(fromNode,toNode));
            }
            else
                return ("Edge Not Found");
        }
    }

    String ModifyEdge(String from,String to,double weight){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null || toNode == null)
            return ("Edge not Found");
        else {
            adj.ModifyEdgeWeight(fromNode, toNode,weight);
            return ("Edge Modified Successfully");
        }
    }
    String DeleteEdge(String from,String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null || toNode == null){
            return ("Edge not Found");
        }
        else if(fromNode == toNode){
            return ("Both Nodes are same!");
        }
        else {

            if(adj.DeleteEd(fromNode, toNode)){
                return ("Edge deleted");
            }
            else
                return ("Edge Not Found");
        }
    }

    String getPath(String from,String to){
        String output;
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode =i;
            }
        }
        output = adj.DijkstraShortestPath(fromNode,toNode);
        adj.resetNodesVisited();
        return output;
    }

    LinkedList<Node> getNodes(){
        return nodes;
    }
    public Dijkstra getAdj(){
        return adj;
    }

    Stack<Node> getNodePath(String from,String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode = i;
            }
        }
        return adj.animatePath(fromNode,toNode);
    }
    }

    /*
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
    public void DFS() {
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
                dist[i][j]=100000;
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



        for(int i=0; i<dist.length;i++){
            for (int j=0; j<dist[0].length;j++){
                System.out.print(dist[i][j]+" ");
            }
            System.out.println();
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
                if(color[v.getKey().getKey()]==0 && v.getValue()<key[v.getKey().getKey()]){
                    key[v.getKey().getKey()]= v.getValue();
                    pred[v.getKey().getKey()]= graph.get(u).getKey();
                }
            }
            color[u]= 1;
        }

        return key;
    }
     */


package com.example.rappi_ando.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

class Dijkstra {
    private Set<Node> nodes;
    private boolean directed;

    Dijkstra(boolean directed) {
        this.directed = directed;
        this.nodes = new HashSet();
    }

    void addEdge(Node source, Node destination, double weight) {
        this.nodes.add(source);
        this.nodes.add(destination);
        this.addEdgeHelper(source, destination, weight);
        if (!this.directed && source != destination) {
            this.addEdgeHelper(destination, source, weight);
        }

    }

    void ModifyEdgeWeight(Node a, Node b, double weight) {
        Iterator var5 = a.edges.iterator();

        Edge edge;
        do {
            if (!var5.hasNext()) {
                return;
            }

            edge = (Edge)var5.next();
        } while(edge.source != a || edge.destination != b);

        edge.weight = weight;
    }

    boolean DeleteEd(Node a, Node b) {
        Iterator var3 = a.edges.iterator();

        Edge edge;
        do {
            if (!var3.hasNext()) {
                return false;
            }

            edge = (Edge)var3.next();
        } while(edge.source != a || edge.destination != b);

        a.edges.remove(edge);
        return true;
    }

    void DeleteNo(Node from) {
        Iterator var2 = this.nodes.iterator();

        label27:
        while(var2.hasNext()) {
            Node node = (Node)var2.next();
            Iterator var4 = node.edges.iterator();

            while(true) {
                Edge edge;
                do {
                    if (!var4.hasNext()) {
                        continue label27;
                    }

                    edge = (Edge)var4.next();
                } while(edge.source != from && edge.destination != from);

                node.edges.remove(edge);
            }
        }

        this.nodes.remove(from);
    }

    public void copyEdge(ArrayList<Edge> edges) {
        Iterator var2 = this.nodes.iterator();

        while(var2.hasNext()) {
            Node node = (Node)var2.next();
            edges.addAll(node.edges);
        }

    }

    private void addEdgeHelper(Node a, Node b, double weight) {
        Iterator var5 = a.edges.iterator();

        Edge edge;
        do {
            if (!var5.hasNext()) {
                a.edges.add(new Edge(a, b, weight));
                return;
            }

            edge = (Edge)var5.next();
        } while(edge.source != a || edge.destination != b);

        edge.weight = weight;
    }

    boolean hasEdge(Node source, Node destination) {
        LinkedList<Edge> edges = source.edges;
        Iterator var4 = edges.iterator();

        Edge edge;
        do {
            if (!var4.hasNext()) {
                return false;
            }

            edge = (Edge)var4.next();
        } while(edge.destination != destination);

        return true;
    }

    double Weight(Node source, Node destination) {
        LinkedList<Edge> edges = source.edges;
        Iterator var4 = edges.iterator();

        Edge edge;
        do {
            if (!var4.hasNext()) {
                return 0.0;
            }

            edge = (Edge)var4.next();
        } while(edge.destination != destination);

        return edge.weight;
    }

    void resetNodesVisited() {
        Iterator var1 = this.nodes.iterator();

        while(var1.hasNext()) {
            Node node = (Node)var1.next();
            node.unvisited();
        }

    }

    String DijkstraShortestPath(Node start, Node end) {
        String output = "";
        HashMap<Node, Node> changedAt = new HashMap();
        changedAt.put(start, (Node) null);
        HashMap<Node, Double> shortestPathMap = new HashMap();
        Iterator var6 = this.nodes.iterator();

        Node child;
        while(var6.hasNext()) {
            child = (Node)var6.next();
            if (child == start) {
                shortestPathMap.put(start, 0.0);
            } else {
                shortestPathMap.put(child, Double.POSITIVE_INFINITY);
            }
        }

        var6 = start.edges.iterator();

        while(var6.hasNext()) {
            Edge edge = (Edge)var6.next();
            shortestPathMap.put(edge.destination, edge.weight);
            changedAt.put(edge.destination, start);
        }

        start.visit();

        while(true) {
            Node currentNode = this.closestReachableUnvisited(shortestPathMap);
            if (currentNode == null) {
                return "There isn't a path between " + start.name + " and " + end.name;
            }

            if (currentNode == end) {
                child = end;
                StringBuilder path = new StringBuilder(end.name);

                while(true) {
                    Node parent = (Node)changedAt.get(child);
                    if (parent == null) {
                        output = output + path;
                        return output;
                    }

                    path.insert(0, parent.name + "->");
                    child = parent;
                }
            }

            currentNode.visit();
            Iterator var12 = currentNode.edges.iterator();

            while(var12.hasNext()) {
                Edge edge = (Edge)var12.next();
                if (!edge.destination.isVisited() && (Double)shortestPathMap.get(currentNode) + edge.weight < (Double)shortestPathMap.get(edge.destination)) {
                    shortestPathMap.put(edge.destination, (Double)shortestPathMap.get(currentNode) + edge.weight);
                    changedAt.put(edge.destination, currentNode);
                }
            }
        }
    }

    Stack<Node> animatePath(Node start, Node end) {
        Stack<Node> path = new Stack();
        HashMap<Node, Node> changedAt = new HashMap();
        changedAt.put(start, (Node) null);
        HashMap<Node, Double> shortestPathMap = new HashMap();
        Iterator var6 = this.nodes.iterator();

        Node child;
        while(var6.hasNext()) {
            child = (Node)var6.next();
            if (child == start) {
                shortestPathMap.put(start, 0.0);
            } else {
                shortestPathMap.put(child, Double.POSITIVE_INFINITY);
            }
        }

        var6 = start.edges.iterator();

        while(var6.hasNext()) {
            Edge edge = (Edge)var6.next();
            shortestPathMap.put(edge.destination, edge.weight);
            changedAt.put(edge.destination, start);
        }

        start.visit();

        while(true) {
            Node currentNode = this.closestReachableUnvisited(shortestPathMap);
            if (currentNode == null) {
                return null;
            }

            if (currentNode == end) {
                child = end;
                path.push(end);

                while(true) {
                    Node parent = (Node)changedAt.get(child);
                    if (parent == null) {
                        return path;
                    }

                    path.push(parent);
                    child = parent;
                }
            }

            currentNode.visit();
            Iterator var11 = currentNode.edges.iterator();

            while(var11.hasNext()) {
                Edge edge = (Edge)var11.next();
                if (!edge.destination.isVisited() && (Double)shortestPathMap.get(currentNode) + edge.weight < (Double)shortestPathMap.get(edge.destination)) {
                    shortestPathMap.put(edge.destination, (Double)shortestPathMap.get(currentNode) + edge.weight);
                    changedAt.put(edge.destination, currentNode);
                }
            }
        }
    }

    private Node closestReachableUnvisited(HashMap<Node, Double> shortestPathMap) {
        double shortestDistance = Double.POSITIVE_INFINITY;
        Node closestReachableNode = null;
        Iterator var5 = this.nodes.iterator();

        while(var5.hasNext()) {
            Node node = (Node)var5.next();
            if (!node.isVisited()) {
                double currentDistance = (Double)shortestPathMap.get(node);
                if (currentDistance != Double.POSITIVE_INFINITY && currentDistance < shortestDistance) {
                    shortestDistance = currentDistance;
                    closestReachableNode = node;
                }
            }
        }

        return closestReachableNode;
    }
}

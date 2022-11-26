package com.example.rappi_ando.model;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdjEdgesTest {
    private ArrayList<Node> nodes = new ArrayList<>();
    private ArrayList<Edge> edges = new ArrayList<>();

    String loadData(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);

            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            int counter=0;
            boolean found=false;
            int no_of_nodes,no_of_edges;
            while ((line = reader.readLine()) != null) {
                String[] tem = line.split(" ");
                if(tem.length==1 && counter==0){
                    try {
                        no_of_nodes = Integer.parseInt(tem[0]);
                    } catch (NumberFormatException e) {
                        return "Invalid Input";
                    }
                }else if(tem.length==1&& counter!=0){
                    found=true;
                    try {
                        no_of_edges = Integer.parseInt(tem[0]);
                    } catch (NumberFormatException e) {
                        return "Invalid Input";
                    }
                }else if(!found){
                    String name=tem[2];
                    double x, y;
                    try {
                        x = Double.parseDouble(tem[0]);
                        y = Double.parseDouble(tem[1]);
                    } catch (NumberFormatException e) {
                        return "Invalid Input";
                    }
                    nodes.add(new Node(x,y,name));
                } else if(found){
                    String from = tem[0];
                    String to = tem[1];
                    double weight;
                    try {
                        weight = Double.parseDouble(tem[2]);
                    } catch (NumberFormatException e) {
                        return "Invalid Input";
                    }
                    //addEdge(from, to, weight);
                }
                counter++;
            }
            reader.close();
        }
        catch (FileNotFoundException fe){
            return ("File not Found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Values Imported";
    }
    void setUp1(){

    }

}
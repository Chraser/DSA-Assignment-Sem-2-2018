/************************************************************
 *Name: Kay Men Yap
 *File name: Graph.java
 *Date last modified: 28/10/2018
 ************************************************************/
 
//This file contains modified code obtained from DSA Prac 6
import java.io.*;
import java.util.*;
public class DSAGraph
{
    private class DSAGraphVertex implements Serializable
    {
        private String label;
        private DSALinkedList<DSAGraphEdge> links;
        private boolean visited;
        
        //Constructor
        public DSAGraphVertex(String inLabel)
        {
            if (inLabel == null)
            {
                throw new IllegalArgumentException("Label cannot be null");
            }
            label = inLabel;
            links = new DSALinkedList<DSAGraphEdge>();
            visited = false;
        }
                
        public String getLabel()
        {
            return label;
        }

        public DSALinkedList<DSAGraphEdge> getAdjacent()
        {
            return links;
        }
        
        public void addEdge(DSAGraphEdge edge)
        {
            links.insertLast(edge);
        }
        
        public void setVisited()
        {
            visited = true;
        }
        
        public void clearVisited()
        {
            visited = false;
        }
        
        public boolean getVisited()
        {
            return visited;
        }
        
        public String toString()
        {
            return label;
        }
    }
    
    private class DSAGraphEdge implements Serializable
    {
        private String label;
        private DSAGraphVertex source;
        private DSAGraphVertex dest;
        private int value;
        private String transportType;
        private boolean visited;
        
        public DSAGraphEdge(String inLabel, DSAGraphVertex inSource, DSAGraphVertex inDest, int inValue, String inTransportType)
        {
            label = inLabel;
            source = inSource;
            dest = inDest;
            value = inValue;
            transportType = inTransportType;
            visited = false;
        }
        
        public String getLabel()
        {
            return label;
        }
        
        public DSAGraphVertex getSource()
        {
            return source;
        }
        
        public DSAGraphVertex getDest()
        {
            return dest;
        }
        
        public int getValue()
        {
            return value;
        }
        
        public String getTransportType()
        {
            return transportType;
        }
        
        public void setVisited()
        {
            visited = true;
        }
        
        public void clearVisited()
        {
            visited = false;
        }
        
        public boolean getVisited()
        {
            return visited;
        }
    }
    
    private DSALinkedList<DSAGraphVertex> vertices;
    private DSALinkedList<DSAGraphEdge> edges;
    
    //Constructor
    public DSAGraph()
    {
        vertices = new DSALinkedList<DSAGraphVertex>();
    }
    
    public DSAGraph(String fileName1, String fileName2)
    {
        vertices = new DSALinkedList<DSAGraphVertex>();
        DSAGraphVertex vertex1, vertex2;
        String[] stringArray;
        String[] tempArray;
        DSALinkedList<String> stringList = new DSALinkedList<String>();
        FileIO.readGraphFile(stringList, fileName1, fileName2);
        for(String line : stringList)
        {
            stringArray = line.split(",");
            vertex1 = getVertex(stringArray[1]);
            vertex2 = getVertex(stringArray[5]);
            if(vertex1 == null)
            {
                vertex1 = addVertex(stringArray[1]);
            }
            if(vertex2 == null)
            {
                vertex2 = addVertex(stringArray[5]);
            }
            tempArray = stringArray[9].split(":");
            int value;
            if(tempArray.length == 3)
            {
                value = (Integer.parseInt(tempArray[0]) * 360) + (Integer.parseInt(tempArray[1]) * 60) + Integer.parseInt(tempArray[2]);
            }
            else
            {
                value = Integer.parseInt(stringArray[9]);
            }
            addEdge(vertex1, vertex2, value, stringArray[10]);
        }
    }
    
    private DSAGraphVertex addVertex(String label)
    {
        DSAGraphVertex vertex = new DSAGraphVertex(label);
        vertex = new DSAGraphVertex(label);
        vertices.insertLast(vertex);
        return vertex;
    }
    
    private void addEdge(DSAGraphVertex vertex1, DSAGraphVertex vertex2, int value, String transportType)
    {
        DSAGraphEdge edge1 = new DSAGraphEdge(vertex1.toString() + "-" + vertex2.toString(), vertex1, vertex2, value, transportType);
        DSAGraphEdge edge2 = new DSAGraphEdge(vertex2.toString() + "-" + vertex1.toString(), vertex2, vertex1, value, transportType);
        
        vertex1.addEdge(edge1);
        vertex2.addEdge(edge2);
    }

    private DSAGraphVertex getVertex(String label)
    {
        DSAGraphVertex vertex = null;
        for(DSAGraphVertex v: vertices)
        {
            if(v.getLabel().equals(label))
            {
                vertex = v;
            }
        }
        return vertex;
    }
    
    public boolean isAdjacent(DSAGraphVertex vertex1, DSAGraphVertex vertex2)
    {
        boolean adjacent = false;
        for(DSAGraphEdge edge: vertex1.getAdjacent())
        {
            if(edge.getDest().getLabel().equals(vertex2.getLabel()))
            {
                adjacent = true;
            }
        }
        return adjacent;
    }
    //End of code obtained from DSA Prac 6
    
    /*
    //method that was supposed to use dijkstra
    public void generateItinerary(DSALinkedList<String> areasToVisit)
    {
        DSALinkedList<String[]> itinerary = new DSALinkedList<String[]>();
        DSALinkedList<DSAGraphVertex> verticesToVisit = new DSALinkedList<DSAGraphVertex>();
        
        String[][] areaArray = new String[areasToVisit.getCount()][2];
        int i = 0;
        
        //get a 2d array so that the areas to visit is sorted by state
        for(area : areasToVisit)
        {
            areaArray[i][0] = (area[0].split("-"))[0];
            areaArray[i][1] = (area[0].split("-"))[1];
            i++;
        }
        
        //sorts the all states to be together
        Sorts.mergeSort(areaArray, 0);
        
        //gets a linked list of vertices that needs to be visited
        for(int j = 0; j = areaArray.length; j++)
        {
            verticesToVisit.insertLast(getVertex(areaArray[j][1]));
        }
        
        DSAGraphVertex source, dest;
        source = verticesToVisit.removeFirst();
        while(!(verticesToVisit.isEmpty())
        {
            dest =  verticesToVisit.removeFirst();
            dijkstra(source, dest, itinerary);
            source = dest;
        }
        
        return itinerary;
    }
    
    //Modified code obtained externally from https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm on 28/10/2018
    private void dijkstra(DSAGraphVertex source, DSAGraphVertex dest, DSALinkedList<String[]> itinerary) 
    { 
        DSALinkedList<DSAGraphVertex> vertexSet = new DSALinkedList<DSAGraphVertex>();
    
        int[] time = new int[vertices.getCount()];
        DSAGraphVertex[] prev = new DSAGraphVertex[];
        
        for (DSAGraphVertex vertex : vertices) 
        { 
            time[i] = Integer.MAX_VALUE;
            prev[i] = null;
            vertexSet.insertLast(vertex);
        } 
        
        // Distance of source vertex from itself is always 0 
        time[0] = 0; 
        DSAGraphVertex vertex1;
        DSAGraphEdge minEdge;
        int i = 0;
        while(!(vertexSet.isEmpty))
        {
            vertex1 = vertexSet.removeFirst();
            
            for(DSAGraphEdge edge : vertex1.getAdjacent())
            {
                altEdge = time[i] + edge.getValue();
                //Unsure what to do from here
                if(altEdge < )
                {
                    
                }
            }
            i++;
        }
  
        
    } 
    //end of code obtained from https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
    */
    
    
    //generate the itinerary in a depth first search manner
    public DSALinkedList<String[]> generateItinerary(DSALinkedList<String[]> areasToVisit)
    {
        DSALinkedList<String[]> itinerary = new DSALinkedList<String[]>();
        DSALinkedList<DSAGraphVertex> verticesToVisit = new DSALinkedList<DSAGraphVertex>();
        
        String[][] areaArray = new String[areasToVisit.getCount()][2];
        int i = 0;
        
        //get a 2d array so that the areas to visit is sorted by state
        for(String[] area : areasToVisit)
        {
            areaArray[i][0] = (area[0].split("-"))[0];
            areaArray[i][1] = (area[0].split("-"))[1];
            i++;
        }
        
        //sorts the all states to be together
        Sorts.mergeSort(areaArray, 0);
        
        //gets a linked list of vertices that needs to be visited
        for(int j = 0; j < areaArray.length; j++)
        {
            verticesToVisit.insertLast(getVertex(areaArray[j][1]));
        }
        
        DSAGraphVertex source, dest;
        source = verticesToVisit.removeFirst();
        while(!(verticesToVisit.isEmpty()))
        {
            dest =  verticesToVisit.removeFirst();
            findPath(source, dest, itinerary);
            source = dest;
        }
        
        return itinerary;
    }
    
    //finds a path from source to dest in a depth first search manner if not directly connected
    private void findPath(DSAGraphVertex source, DSAGraphVertex dest, DSALinkedList<String[]> itinerary)
    {
        DSAGraphEdge minEdge = source.getAdjacent().peekFirst();
        int minValue = Integer.MAX_VALUE;
        String[] path = new String[2];
        if(isAdjacent(source, dest))
        {
            for(DSAGraphEdge edge : source.getAdjacent())
            {
                if(edge.getDest().getLabel().equals(dest.getLabel()))
                {
                    if(edge.getValue() < minValue)
                    {
                        minEdge = edge;
                        minValue = minEdge.getValue();
                    }
                }
            }
            path[0] = minEdge.getSource().toString() + " to " + minEdge.getDest().toString() + " by " + minEdge.getTransportType();
            path[1] = Integer.toString(minEdge.getValue());
            itinerary.insertLast(path);
            path = new String[2];
            path[0] = "Meet and greet";
            path[1] = "10800"; //3 hours in seconds
            itinerary.insertLast(path);
        }
        else
        {
            path[0] = minEdge.getSource().toString() + " to " + minEdge.getDest().toString() + " by " + minEdge.getTransportType();
            path[1] = Integer.toString(minEdge.getValue());
            itinerary.insertLast(path);
            findPath(minEdge.getSource(), dest, itinerary);
        }
    }
}

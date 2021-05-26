public class UnitTestDSAGraph
{	
    public static void main(String args[])
    {
        DSAGraph graph = null; 
        String fileName1 = "ElectDist1.1.csv";
        String fileName2 = "AirportDist1.0.csv";
        graph = new DSAGraph(fileName1, fileName2);
        System.out.println("Construction of graph: passed");
    } // End of main
} // End of Class


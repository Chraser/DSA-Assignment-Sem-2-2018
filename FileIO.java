/************************************************************
 *Name: Kay Men Yap
 *File name: FileIO.java
 *Purpose: Handle the file input output operations
 *Date last modified: 28/10/2018
 ************************************************************/
//This file contains modified code obtained from OOPD Assignment
import java.io.*;
public class FileIO
{
    private static final String CANDIDATEFILENAME = "HouseCandidatesDownload-20499.txt";
    private static final String[] VOTESFILENAMES ={"HouseStateFirstPrefsByPollingPlaceDownload-20499-ACT.txt",
                                                   "HouseStateFirstPrefsByPollingPlaceDownload-20499-NSW.txt",
                                                   "HouseStateFirstPrefsByPollingPlaceDownload-20499-NT.txt",
                                                   "HouseStateFirstPrefsByPollingPlaceDownload-20499-QLD.txt",
                                                   "HouseStateFirstPrefsByPollingPlaceDownload-20499-SA.txt",
                                                   "HouseStateFirstPrefsByPollingPlaceDownload-20499-TAS.txt",
                                                   "HouseStateFirstPrefsByPollingPlaceDownload-20499-VIC.txt",
                                                   "HouseStateFirstPrefsByPollingPlaceDownload-20499-WA.txt"};
    
    
    public static DSALinkedList<String[]> loadCandidateList()
    {
        DSALinkedList<String[]> candidateList = null;
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        String[] lineArray;
        try
        {
            candidateList = new DSALinkedList<String[]>();
            fileStrm = new FileInputStream(CANDIDATEFILENAME);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            //Another read line to move to the second line that has actual data
            line = bufRdr.readLine();
            while (line != null)
            {
                lineArray = line.split("\t");
                candidateList.insertLast(lineArray);
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
        return candidateList;
    }
    
    
    public static DSALinkedList<String[]> loadVotesList()
    {
        DSALinkedList<String[]> votesList = null;
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        String[] lineArray;
        String[] voteArray;
        votesList = new DSALinkedList<String[]>();
        for(int i = 0; i < VOTESFILENAMES.length; i++)
        {
            try
            {
                fileStrm = new FileInputStream(VOTESFILENAMES[i]);
                rdr = new InputStreamReader(fileStrm);
                bufRdr = new BufferedReader(rdr);
                line = bufRdr.readLine();
                line = bufRdr.readLine();
                //2 read lines to move to the third line that has actual data
                line = bufRdr.readLine();
                while(line != null)
                {
                    lineArray = line.split("\t");
                    voteArray = new String[3];
                    //getting the required fields from line (state-division name, partyAb and votes) to a voteArray
                    voteArray[0] = lineArray[0] + "-"  + lineArray[2];
                    voteArray[1] = lineArray[11];
                    voteArray[2] = lineArray[13];
                    votesList.insertLast(voteArray);
                    line = bufRdr.readLine();
                }
                fileStrm.close();
            }
            catch (IOException e)
            {
                if (fileStrm != null)
                {
                    try
                    {
                        fileStrm.close();
                    }
                    catch (IOException ex2)
                    {
                    }
                }
                System.out.println("Error in file processing: " + e.getMessage());
            }
        }   
        return votesList;
    }
    
    /************************************************************
     *SUBMODULE: readGraphFile
     *IMPORTS: lineArray(DSALinkedList of String), fileName(String)
     *EXPORT: none
     *ASSERTION: file is not empty
     *PURPOSE: To load data from the file into the line array
     ************************************************************/
    public static void readGraphFile(DSALinkedList<String> lineArray, String fileName1, String fileName2)
    {
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;
        try
        {
            fileStrm = new FileInputStream(fileName1);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            //Another read line to move to the second line that has actual data
            line = bufRdr.readLine();
            while (line != null)
            {
                lineArray.insertLast(line);
                line = bufRdr.readLine();
            }
            fileStrm.close();
            
            fileStrm = new FileInputStream(fileName2);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            //Another read line to move to the second line that has actual data
            line = bufRdr.readLine();
            while (line != null)
            {
                lineArray.insertLast(line);
                line = bufRdr.readLine();
            }
            fileStrm.close();
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
    }
    
    /************************************************************
     *PURPOSE: To write the candidates to a file
     */
    public static void saveCandidates(String[][] candidateArray, String fileName)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        try{
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
            for(int i = 0; i < candidateArray.length; i++)
            {
                for(int j = 0; j < candidateArray[0].length; j++)
                {
                    if(j < candidateArray[0].length - 1)
                    {
                        pw.print(candidateArray[i][j] + ",");
                    }
                    else
                    {
                        pw.print(candidateArray[i][j] + "\n");
                    }
                }
            }
            pw.close();
            System.out.println("The candidates has been written to " + fileName);
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
        }
    }
    
    /************************************************************
     *PURPOSE: To save the list by margin to file
     */
    public static void saveListByMargin(DSALinkedList<String[]> listByMargin, String fileName)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        try{
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
            for(String[] margin : listByMargin)
            {
                for(int i = 0; i < margin.length; i++)
                {
                    if(i < margin.length - 1)
                    {
                        pw.print(margin[i] + ",");
                    }
                    else
                    {
                        pw.print(margin[i] + "\n");
                    }
                }
            }
            pw.close();
            System.out.println("The candidates has been written to " + fileName);
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
        }
    }
    
    public static void saveItinerary(DSALinkedList<String[]> itinerary, String fileName)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        try{
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
            int totalTime = 0;
            for(String[] path : itinerary)
            {
                pw.println(path[0]);
                totalTime += Integer.parseInt(path[1]);
            }
            int hours, minutes;
            hours = totalTime / 360;
            minutes = totalTime % 360 / 60;
            pw.println("Total time: " + hours + " hours and " + minutes + " minutes");
            pw.close();
            System.out.println("The candidates has been written to " + fileName);
        }
        catch (IOException e)
        {
            if (fileStrm != null)
            {
                try
                {
                    fileStrm.close();
                }
                catch (IOException ex2)
                {
                }
            }
        }
        int totalTime = 0;
        for(String[] path : itinerary)
        {
            System.out.println(path[0]);
            totalTime += Integer.parseInt(path[1]);
        }
        int hours, minutes;
        hours = totalTime / 360;
        minutes = totalTime % 360 * 60;
        System.out.println("Total time: " + hours + " hours and " + minutes + " minutes");
    }
}
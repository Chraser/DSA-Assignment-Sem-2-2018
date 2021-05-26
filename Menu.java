/************************************************************
 *Name: Kay Men Yap
 *File name: Menu.java
 *Purpose: Handles menu operations
 *Date last modified: 28/10/2018
 ************************************************************/
//This file contains modified code obtained from OOPD Assignment
public class Menu
{
    /************************************************************
     *SUBMODULE: run
     *IMPORT: none    
     *EXPORT: none
     *PURPOSE: Keeps running the program and performs the option
     *         selected by the user until exit option is selected
     ************************************************************/
    public static void run() 
    {
        boolean itineraryExist = false;
        boolean runProgram = true;
        DSALinkedList<String[]> candidateList = FileIO.loadCandidateList();
        DSALinkedList<String[]> votesList = FileIO.loadVotesList();
        DSALinkedList<String[]> listByMargin = new DSALinkedList<String[]>();
        DSALinkedList<String[]> itinerary = new DSALinkedList<String[]>();
        String[][] sortedArray;
        String[][] nomineeSearchResult;
        String fileName;
        DSAGraph graph = new DSAGraph("ElectDist1.1.csv","AirportDist1.0.csv");
        int optionSelected, saveToFileOption;
        do
        {
            optionSelected = UserIO.integerInput("Select an option:\n1. List nominees\n2. Nominee Search\n3. List by Margin\n4. Itinerary\n0. Quit", 0, 4);
            switch(optionSelected)
            {
                case 1:
                    sortedArray = listNominees(candidateList);
                    UserIO.displayCandidates(sortedArray);
                    saveToFileOption = UserIO.integerInput("Do you want to\n1. Save\n0. Don't save", 0, 1);
                    if(saveToFileOption == 1)
                    {
                        fileName = UserIO.stringInput("Enter name of file to be saved to");
                        FileIO.saveCandidates(sortedArray, fileName);
                    }
                    break;
                case 2:
                    nomineeSearchResult = nomineeSearch(candidateList);
                    UserIO.displayCandidates(nomineeSearchResult);
                    saveToFileOption = UserIO.integerInput("Do you want to\n1. Save\n0. Don't save", 0, 1);
                    if(saveToFileOption == 1)
                    {
                        fileName = UserIO.stringInput("Enter name of file to be saved to");
                        FileIO.saveCandidates(nomineeSearchResult, fileName);
                    }
                    break;
                case 3:
                    listByMargin = generateListByMargin(votesList);
                    UserIO.displayListByMargin(listByMargin);
                    saveToFileOption = UserIO.integerInput("Do you want to\n1. Save\n0. Don't save", 0, 1);
                    if(saveToFileOption == 1)
                    {
                        fileName = UserIO.stringInput("Enter name of file to be saved to");
                        FileIO.saveListByMargin(listByMargin, fileName);
                    }
                    itineraryExist = true;
                    break;
                case 4:
                    if (itineraryExist)
                    {
                        //check if list by margin is empty as it may happen so no itinerary can be generated if list by margin is empty
                        if(!(listByMargin.isEmpty()))
                        {
                            itinerary = graph.generateItinerary(listByMargin);
                            UserIO.displayItinerary(itinerary);
                            saveToFileOption = UserIO.integerInput("Do you want to\n1. Save\n0. Don't save", 0, 1);
                            if(saveToFileOption == 1)
                            {
                                fileName = UserIO.stringInput("Enter name of file to be saved to");
                                FileIO.saveItinerary(itinerary, fileName);
                            }
                        }
                        else
                        {
                            System.out.println("ERROR: List by Margin is empty. Unable to generate graph");
                        }
                    }
                    else
                    {
                        System.out.println("ERROR: List by Margin does not exist. Unable to generate graph");
                    }
                    break;
                default:
                    runProgram = false;
                    System.out.println("Program exitted");
            }
        }while(runProgram);
    }
    
    //makes a 2d array of candidates sorted in the way the user specified and filtered
    public static String[][] listNominees(DSALinkedList<String[]> candidateList)
    {
        String[][] candidateArray = filterNomineeList(candidateList);
        
        sortNomineeList(candidateArray);
        
        return candidateArray;
    }
    
    //filters by state, party, divsion, all or none based on user input
    public static String[][] filterNomineeList(DSALinkedList<String[]> candidateList)
    {
        DSALinkedList<String[]> filteredList = new DSALinkedList<String[]>();
        String state = null;
        String party = null;
        String division = null;
        int filterOption = UserIO.integerInput("Filter by:\n1.State \n2.Party \n3.Division \n4.All \n0.None", 0, 4);
        if(filterOption == 1)
        {
            state = UserIO.stringInput("Enter state to filter by:");
            for(String[] candidate : candidateList)
            {
                if(candidate[0].equals(state))
                {
                    filteredList.insertLast(candidate);
                }
            }
        }
        else if(filterOption == 2)
        {
            party = UserIO.stringInput("Enter party to filter by:");
            for(String[] candidate : candidateList)
            {
                if(candidate[3].equals(party))
                {
                    filteredList.insertLast(candidate);
                }
            }
        }
        else if(filterOption == 3)
        {
            division = UserIO.stringInput("Enter division to filter by:");
            for(String[] candidate : candidateList)
            {
                if(candidate[1].equals(division))
                {
                    filteredList.insertLast(candidate);
                }
            }
        }
        else if(filterOption == 4)
        {
            state = UserIO.stringInput("Enter state to filter by:");
            party = UserIO.stringInput("Enter party to filter by:");
            division = UserIO.stringInput("Enter division to filter by:");
            for(String[] candidate : candidateList)
            {
                if((candidate[0].equals(state)) && (candidate[3].equals(party)) && (candidate[1].equals(division)))
                {
                    filteredList.insertLast(candidate);
                }
            }
        }
        else
        {
            for(String[] candidate : candidateList)
            {
                filteredList.insertLast(candidate);
            }
        }
        
        int i = 0;
        String[][] candidateArray = new String[filteredList.getCount()][10];
        for(String[] candidate : filteredList)
        {
            for(int j = 0; j < candidate.length; j++)
            {
                candidateArray[i][j] = candidate[j];
            }
            i++;
        }
        return candidateArray;
    }
    
    //Sorts the nominees list based on what the user specifies when requested of the order by option
    public static void sortNomineeList(String[][] candidateArray)
    {
        int orderByOption = UserIO.integerInput("Do you want to order by \n1.Surname \n2.State \n3.Party \n4.Division \n5.All \n0.Don't sort", 0, 4);
        
        switch(orderByOption)
        {
            case 1:
                Sorts.mergeSort(candidateArray, 6);
                break;
            case 2:
                Sorts.mergeSort(candidateArray, 0);
                break;
            case 3:
                Sorts.mergeSort(candidateArray, 3);
                break;
            case 4:
                Sorts.mergeSort(candidateArray, 4);
                break;
            case 5:
                // sorts the candidate array in the priority of Surname then State then Party then Division 
                Sorts.mergeSort(candidateArray, 4);
                Sorts.mergeSort(candidateArray, 3);
                Sorts.mergeSort(candidateArray, 0);
                Sorts.mergeSort(candidateArray, 6);
                break;
        }
    }
    
    //requests for substring to search by and get all candidates that start with that substring
    public static String[][] nomineeSearch(DSALinkedList<String[]> candidateList)
    {
        String substring = UserIO.stringInput("Enter substring to search by:");
        DSALinkedList<String[]> startsWithSubstringList = new DSALinkedList<String[]>();
        
        for(String[] candidate : candidateList)
        {
            if(candidate[6].startsWith(substring))
            {
                startsWithSubstringList.insertLast(candidate);
            }
        }
        
        String[][] candidateArray = filterNomineeSearch(startsWithSubstringList);
        return candidateArray;
    }
    
    //filters by state, party, all or none based on user input
    public static String[][] filterNomineeSearch(DSALinkedList<String[]> candidateList)
    {
        DSALinkedList<String[]> filteredList = new DSALinkedList<String[]>();
        String state = null;
        String party = null;
        int filterOption = UserIO.integerInput("Filter by:\n1.State \n2.Party \n3.All \n0.None", 0, 3);
        if(filterOption == 1)
        {
            state = UserIO.stringInput("Enter state to filter by:");
            for(String[] candidate : candidateList)
            {
                if(candidate[0].equals(state))
                {
                    filteredList.insertLast(candidate);
                }
            }
        }
        else if(filterOption == 2)
        {
            party = UserIO.stringInput("Enter party to filter by:");
            for(String[] candidate : candidateList)
            {
                if(candidate[3].equals(party))
                {
                    filteredList.insertLast(candidate);
                }
            }
        }
        else if(filterOption == 3)
        {
            state = UserIO.stringInput("Enter state to filter by:");
            party = UserIO.stringInput("Enter party to filter by:");
            for(String[] candidate : candidateList)
            {
                if((candidate[0].equals(state)) && (candidate[3].equals(party)))
                {
                    filteredList.insertLast(candidate);
                }
            }
        }
        else
        {
            for(String[] candidate : candidateList)
            {
                filteredList.insertLast(candidate);
            }
        }
        
        int i = 0;
        String[][] candidateArray = new String[filteredList.getCount()][10];
        for(String[] candidate : filteredList)
        {
            for(int j = 0; j < candidate.length; j++)
            {
                candidateArray[i][j] = candidate[j];
            }
            i++;
        }
        return candidateArray;
    }
    
    //generates list by margin based on party and thresholt entered by user
    public static DSALinkedList<String[]> generateListByMargin(DSALinkedList<String[]> votesList)
    {
        String party =  UserIO.stringInput("Enter party to find marginal seats for:");
        int marginOption = UserIO.integerInput("Select margin threshold to use:\n1. Default of 6% \n2. Custom threshold", 1, 2);
        double marginThreshold;
        if(marginOption == 1)
        {
            marginThreshold = 6.0;
        }
        else
        {
            marginThreshold = UserIO.realInput("Enter custom margin threshold to use:");
        }
        
        DSALinkedList<String[]> allMarginsList = calculateMargin(votesList, party);
        
        //gets all divisions with margins within the threshold
        DSALinkedList<String[]> withinThresholdList = new DSALinkedList<String[]>();
        for(String[] marginArray : allMarginsList)
        {
            if((Double.parseDouble(marginArray[1]) < marginThreshold) && (Double.parseDouble(marginArray[1]) > ((-1)*marginThreshold)))
            {
                withinThresholdList.insertLast(marginArray);
            }
        }
        return withinThresholdList;
    }
    
    //calculates the margins of all divisions of the party
    public static DSALinkedList<String[]> calculateMargin(DSALinkedList<String[]> votesList, String party)
    {
        DSALinkedList<String[]> marginsList = new DSALinkedList<String[]>();
        DSAHashTable votesForHashTable = new DSAHashTable(100);
        DSAHashTable totalVotesHashTable = new DSAHashTable(100);
        int currentNumVotes;
        String division;
        
        // votes is an array in the format (division, party, numVotes)
        for(String[] votes : votesList)
        {
            if(totalVotesHashTable.containsKey(votes[0]))
            {
                currentNumVotes = totalVotesHashTable.get(votes[0]);
                currentNumVotes += Integer.parseInt(votes[2]);
                totalVotesHashTable.put(votes[0], currentNumVotes);
            }
            else
            {
                totalVotesHashTable.put(votes[0], Integer.parseInt(votes[2]));
            }
            
            if(party.equals(votes[1]))
            {
                if(votesForHashTable.containsKey(votes[0]))
                {
                    currentNumVotes = votesForHashTable.get(votes[0]);
                    currentNumVotes += Integer.parseInt(votes[2]);
                    votesForHashTable.put(votes[0], currentNumVotes);
                }
                else
                {
                    votesForHashTable.put(votes[0], Integer.parseInt(votes[2]));
                }
            }
        }
        
        String[] divisionArray = votesForHashTable.getAllKeys();
        double[] marginArray = new double[divisionArray.length];
        for(int i = 0; i < divisionArray.length; i++)
        {
            marginArray[i] = (double)(votesForHashTable.get(divisionArray[i])) / (double)(totalVotesHashTable.get(divisionArray[i])) * 100 - 50;
        }
        
        //convert the division and margin into a string array and put it in the linked list
        String[] marginDivision;
        for(int i = 0; i < divisionArray.length; i++)
        {
            marginDivision = new String[2];
            marginDivision[0] = divisionArray[i];
            marginDivision[1] = Double.toString(marginArray[i]);
            marginsList.insertLast(marginDivision);
        }
        
        return marginsList;
    }
}
Kay Men Yap DSA Assignment Readme

Date created: 28/10/2018

Date last modified: 28/10/2018

Purpose: To have a program that can list all nominees in a sorted and filtered manner, search for nominees with a substring, generate a list by margin for a specific party
         and make an itinerary based on a list by margin.

Files in project: 
AirportDist1.0.csv - Input file
DSAAssignment.java - Starting pointing of program
DSAGraph.java - Constructs a graph and generates an itinerary
DSAHashTable.java - Construcuts a hash table and contains the value associated with a key
DSALinkedList.java - Holds a variable number of values and increases and decreases in size when add or removing elements
ElectDist1.0.csv - Old input file
ElectDist1.1.csv - Input file
FileIO.java - Handles all file input output operations
HouseCandidatesDownload-20499.txt - Input file
HouseStateFirstPrefsByPollingPlaceDownload-20499-ACT.txt - Input file
HouseStateFirstPrefsByPollingPlaceDownload-20499-NSW.txt - Input file
HouseStateFirstPrefsByPollingPlaceDownload-20499-NT.txt - Input file
HouseStateFirstPrefsByPollingPlaceDownload-20499-QLD.txt - Input file
HouseStateFirstPrefsByPollingPlaceDownload-20499-SA.txt - Input file
HouseStateFirstPrefsByPollingPlaceDownload-20499-TAS.txt - Input file
HouseStateFirstPrefsByPollingPlaceDownload-20499-VIC.txt - Input file
HouseStateFirstPrefsByPollingPlaceDownload-20499-WA.txt - Input file
Menu.java - Handles all menu operations
Sorts.java - Handles sorting of data
UserIO.java - Handles user input output operations
asp.csv - Data to check with when running program with DriverCode.txt
nameStartWithAS.csv - Data to check with when running program with DriverCode.txt
ALP_withinMargin_6.csv - Data to check with when running program with DriverCode.txt

Test Files:
DriverCode.txt (this is just an input file to show what to be typed when testing the main program. Check the files saved with asp.csv, nameStartWithAS.csv, ALP_withinMargin_6.csv)
UnitTestDSAGraph.java
UnitTestDSAHashTable.java
UnitTestDSALinkedList.java
UnitTestSorts.java

Dependencies:
DSAAssignment.java -> Menu.java
Menu.java -> Sorts.java, DSAGraph.java, DSAHashTable.java, DSALinkedList.java, UserIO.java, FileIO.java
DSAGraph.java -> DSALinkedList.java, FileIO.java
All other java files have no dependencies 

Functionality: It can list all nominees in a sorted and filtered manner, search for nominees with a substring, generate a list by margin for a specific party but not make an itinerary as of right now

Known bugs:
The itinerary option will throw a stack overflow error when ran.

Notes:
To run the program, type the command "java DSAAssignment"
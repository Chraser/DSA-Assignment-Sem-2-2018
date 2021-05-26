/************************************************************
 *Name: Kay Men Yap
 *File name: Sorts.java
 *Purpose: Sorts data
 *Date last modified: 28/10/2018
 ************************************************************/
//This file contains modified code obtained from DSA Prac 2
class Sorts
{
    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(String[][] stringArray, int compareIdx)
    {
        mergeSortRecurse(stringArray, compareIdx, 0, stringArray.length - 1);
    }//mergeSort()
    
    private static void mergeSortRecurse(String[][] stringArray, int compareIdx, int leftIdx, int rightIdx)
    {
        int midIdx;
        if(leftIdx < rightIdx)
        {
            midIdx = (leftIdx + rightIdx)/2;
            
            mergeSortRecurse(stringArray, compareIdx, leftIdx, midIdx);
            mergeSortRecurse(stringArray, compareIdx, midIdx + 1, rightIdx);

            merge(stringArray, compareIdx, leftIdx, midIdx, rightIdx);
        }
    }//mergeSortRecurse()
    private static void merge(String[][] stringArray, int compareIdx, int leftIdx, int midIdx, int rightIdx)
    {
        String[][] tempArr = new String[rightIdx - leftIdx + 1][10];
        int ii, jj, kk;
        ii = leftIdx;
        jj = midIdx + 1;
        kk = 0;

        while((ii <= midIdx) && (jj <= rightIdx))
        {
            if(stringArray[ii][compareIdx].compareTo(stringArray[jj][compareIdx]) <= 0)
            {
                tempArr[kk] = stringArray[ii];
                ii++;
            }
            else
            {
                tempArr[kk] = stringArray[jj];
                jj++;
            }
            kk++;
        }
        for(ii = ii; ii <= midIdx; ii++)
        {
            tempArr[kk] = stringArray[ii];
            kk++;
        }
        for(jj = jj; jj <= rightIdx; jj++)
        {
            tempArr[kk] = stringArray[jj];
            kk++;
        }
        for(kk = leftIdx; kk <= rightIdx; kk++)
        {
            stringArray[kk] = tempArr[kk-leftIdx];
        }
    }//merge()
}//end Sorts class
//End of code obtained from DSA Prac 2
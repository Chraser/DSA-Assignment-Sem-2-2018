/************************************************************
 *Name: Kay Men Yap
 *File name: DSAHashTable.java
 *Date last modified: 28/10/2018
 ************************************************************/
//This file contains modified code obtained from DSA Prac 8
import java.io.*;
public class DSAHashTable
{
    private class DSAHashEntry
    {
        public String key;
        public int value;
        public int state;
        
        public DSAHashEntry()
        {
            key = "";
            value = 0;
            state = 0;
        }
        
        public DSAHashEntry(String inKey, int inValue)
        {
            key = inKey;
            value = inValue;
            state = 1;
        }
    }
    
    private DSAHashEntry[] m_hashTable;
    private int count;
    
    public DSAHashTable(int maxSize)
    {
        int actualSize = findNextPrime(maxSize);
        m_hashTable = new DSAHashEntry[actualSize];
        for(int ii = 0; ii < actualSize; ii++)
        {
            m_hashTable[ii] = new DSAHashEntry();
        }
    }
    
    public void put(String inKey, int inValue)
    {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp =  false;
        
        while((!found) && (!giveUp))
        {
            if(m_hashTable[hashIdx].state <= 0)
            {
                found = true;
                count++;
            }
            else if(inKey.equals(m_hashTable[hashIdx].key))
            {
                found = true;
            }
            else
            {
                hashIdx = (hashIdx + 1) % m_hashTable.length;
                if(hashIdx == origIdx)
                {
                    giveUp = true;
                }
            }
        }
        
        if(found)
        {
            m_hashTable[hashIdx] = new DSAHashEntry(inKey, inValue);
            double loadFactor = (double)count / (double)m_hashTable.length;
            if(loadFactor > 0.75)
            {
                reSize(m_hashTable.length * 2);
            }
            else if(loadFactor < 0.2)
            {
                reSize(m_hashTable.length / 2);
            }
        }
    }
    
    private void put(DSAHashEntry hashEntry)
    {
        int hashIdx = hash(hashEntry.key);
        int origIdx = hashIdx;
        boolean found = false;
        
        while((!found) )
        {
            if(m_hashTable[hashIdx].state <= 0)
            {
                found = true;
                m_hashTable[hashIdx] = hashEntry;
            }
            else
            {
                hashIdx = (hashIdx + 1) % m_hashTable.length;
            }
        }
    }
    
    public int get(String inKey)
    {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;
        
        while((!found) && (!giveUp))
        {
            if(m_hashTable[hashIdx].state == 0)
            {
                giveUp = true;
            }
            else if(inKey.equals(m_hashTable[hashIdx].key))
            {
                found = true;
            }
            else
            {
                hashIdx = (hashIdx + 1) % m_hashTable.length;
                if(hashIdx == origIdx)
                {
                    giveUp = true;
                }
            }
        }
        
        if(!found)
        {
            throw new IllegalArgumentException(inKey + " does not exist in hash table");
        }
        int retValue = m_hashTable[hashIdx].value;
        return retValue;
    }
    
    public int remove(String inKey)
    {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;
        
        while((!found) && (!giveUp))
        {
            if(m_hashTable[hashIdx].state == 0)
            {
                giveUp = true;
            }
            else if(inKey.equals(m_hashTable[hashIdx].key))
            {
                found = true;
            }
            else
            {
                hashIdx = (hashIdx + 1) % m_hashTable.length;
                if(hashIdx == origIdx)
                {
                    giveUp = true;
                }
            }
        }
        
        if(!found)
        {
            throw new IllegalArgumentException(inKey + " does not exist in hash table");
        }
        int retValue = m_hashTable[hashIdx].value;
        m_hashTable[hashIdx].key = null;
        m_hashTable[hashIdx].value = 0;
        m_hashTable[hashIdx].state = -1;
        count--;
        return retValue;
    }
    
    public boolean containsKey(String inKey)
    {
        int hashIdx = hash(inKey);
        int origIdx = hashIdx;
        boolean found = false;
        boolean giveUp = false;
        
        while((!found) && (!giveUp))
        {
            if(m_hashTable[hashIdx].state == 0)
            {
                giveUp = true;
            }
            else if(inKey.equals(m_hashTable[hashIdx].key))
            {
                found = true;
            }
            else
            {
                hashIdx = (hashIdx + 1) % m_hashTable.length;
                if(hashIdx == origIdx)
                {
                    giveUp = true;
                }
            }
        }
        return found;
    }
    
    private int findNextPrime(int startVal)
    {
        int primeVal;
        if(startVal % 2 ==0)
        {
            primeVal = startVal - 1;
        }
        else
        {
            primeVal = startVal;
        }
        
        boolean isPrime = false;
        int ii;
        while(!isPrime)
        {
            primeVal += 2;
            ii = 3;
            isPrime = true;
            while((ii*ii <primeVal) && isPrime)
            {
                if(primeVal % ii == 0)
                {
                    isPrime = false;
                }
                else
                {
                    ii += 2;    
                }
            }        
        }
        return primeVal;
    }
    
    //resizes the current array to the nearest prime number to size and copy all data from old array
    private void reSize(int size)
    {
        int arraySize = findNextPrime(size);
        DSAHashEntry[] oldArray = m_hashTable;
        m_hashTable= new DSAHashEntry[arraySize];
        
        for(int ii = 0; ii < m_hashTable.length; ii++)
        {
            m_hashTable[ii] = new DSAHashEntry();
        }
        
        for(int ii = 0; ii < oldArray.length; ii++)
        {
            if((oldArray[ii] != null) && (oldArray[ii].state == 1))
            {
                put(oldArray[ii]);
            }
        }
    }
    
    //generates a hash index from the key supplied
    private int hash(String inKey)
    {
        int hashIdx = 0;
        for(int ii = 0; ii < inKey.length(); ii++)
        {
            hashIdx += inKey.charAt(ii);
        }
        return hashIdx % m_hashTable.length;
    }
    
    //gets all keys that exist in hash table to know how to get all values from hash table
    public String[] getAllKeys()
    {
        DSALinkedList<String[]> list = new DSALinkedList<String[]>();
        String[] keyArray = new String[count];
        int jj = 0;
        for(int ii = 0; ii < m_hashTable.length; ii++)
        {
            if(m_hashTable[ii].state == 1)
            {
                keyArray[jj] = m_hashTable[ii].key;
                jj++;
            }
        }
        return keyArray;
    }
    
    //saves the hash table to a file
    public void save(String fileName)
    {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        
        try{
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
            for(int ii = 0; ii < m_hashTable.length; ii++)
            {
                if(m_hashTable[ii].state == 1)
                {
                    pw.println(m_hashTable[ii].key + "," + m_hashTable[ii].value);
                }
            }
            pw.close();
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
}
//end of code obtained from DSA Prac 8
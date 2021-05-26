import java.util.*;
import java.io.*;

public class UnitTestDSAHashTable
{
    public static void main(String[] args)
    {
        DSAHashTable hashTable = null;
        int num1;
        int num2;
        boolean exist;
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");
        try
        {   
            System.out.print("Testing constructor of DSAHashTable: ");
            hashTable = new DSAHashTable(10);
            System.out.println("passed");
            
            System.out.print("Testing put: ");
            hashTable.put("10", 10);
            System.out.println("passed");
            
            System.out.print("Testing get: ");
            num1 = hashTable.get("10");
            if(num1 == 10)
            {
                System.out.println("passed");
            }
            else
            {
                throw new IllegalArgumentException("FAILED");
            }
            
            
            System.out.print("Testing multiple put: ");
            hashTable.put("5", 5);
            hashTable.put("60", 60);
            hashTable.put("50", 50);
            hashTable.put("35", 35);
            System.out.println("passed");
                        
            System.out.print("Testing remove: ");
            num1 = 5;
            num2 = hashTable.remove("5");
            if(num1 == num2)
            {
                System.out.println("passed");
            }
            else
            {
                throw new IllegalArgumentException("FAILED");
            }
            
            System.out.print("Testing get on a key that does not exist in hash table: ");
            try
            {
                hashTable.get("20");
                throw new IllegalArgumentException("FAILED");
            }
            catch(Exception e) { System.out.println("passed"); }
            
            System.out.print("Testing remove on a key that does not exist in hash table: ");
            try
            {
                hashTable.remove("40");
                throw new IllegalArgumentException("FAILED");
            }
            catch(Exception e) { System.out.println("passed"); }
            
            System.out.print("Testing containsKey on a key that exist in hash table: \n");
            exist = hashTable.containsKey("10");
            if(exist)
            {
                System.out.println("passed");
            }
            else
            {
                throw new IllegalArgumentException("FAILED");
            }
            
            System.out.print("Testing containsKey on a key that does not exist in hash table: \n");
            exist = hashTable.containsKey("100");
            if(!exist)
            {
                System.out.println("passed");
            }
            else
            {
                throw new IllegalArgumentException("FAILED");
            }
            
            System.out.print("Testing resize: ");
            hashTable = new DSAHashTable(1);
            hashTable.put("10",10);
            hashTable.put("25",25);
            hashTable.put("30",30);
            System.out.println("passed");
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("Illegal Argument:" + e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println("FAILED\n" + e.getMessage());
        }
        
        // Tests with error conditions (SHOULD throw exceptions)
        System.out.println("\n");
        System.out.println("Testing Error Conditions");
        System.out.println("======================================");
        
        hashTable = new DSAHashTable(1);
        try
        {
            System.out.print("Testing remove on a key that does not exist in hash table: ");
            hashTable.remove("10");
            System.out.println("FAILED");
        }
        catch(Exception e) { System.out.println("passed"); }      
    }
}
/************************************************************
 *Name: Kay Men Yap
 *File name: DSALinkedList.java
 *Purpose: Handle the file input output operations
 *Date last modified: 28/10/2018
 ************************************************************/

//This file contains modified code obtained from DSA Prac 4
import java.util.*;
import java.io.*;
public class DSALinkedList<E extends Serializable> implements Iterable<E>, Serializable
{
    private DSALinkedList<E>.DSAListNode<E> head;
    private DSALinkedList<E>.DSAListNode<E> tail;
    private int count;
    
    private class DSAListNode<E extends Serializable> implements Serializable
    {
        private E value;
        private DSAListNode<E> next;
        private DSAListNode<E> prev;

        /************************************************************
         *DEFAULT CONSTRUCTOR
         *IMPORT: inValue(E), inNext(DSAListNode<E>), inPrev(DSAListNode<E>)
         *EXPORT: none
         *ASSERTION: Creates a DSAListNode
         ************************************************************/
        public DSAListNode(E inValue, DSAListNode<E> inNext, DSAListNode<E> inPrev)
        {
            value = inValue;
            next = inNext;
            prev = inPrev;
        }

        /************************************************************
         *SUBMODULE: getValue
         *IMPORT: none
         *EXPORT: E
         *ASSERTION: Return the value of node
         ************************************************************/
        public E getValue()
        {
            return value;
        }

        public void setValue(E inValue)
        {
            value = inValue;
        }

        public DSAListNode<E> getNext()
        {
            return next;
        }

        public void setNext(DSAListNode<E> newNext)
        {
            next = newNext;
        }

        public DSAListNode<E> getPrev()
        {
            return prev;
        }

        public void setPrev(DSAListNode<E> newPrev)
        {
            prev = newPrev;
        }
    }
    
    /************************************************************
     *SUBMODULE: iterator
	 *IMPORT: none
	 *EXPORT: Iterator<E>
     *ASSERTION: Returns a DSALinkedListIterator of current DSALinkedList
     ************************************************************/
    public Iterator<E> iterator()
    {
        return new DSALinkedListIterator<E>(this);
    }
    
    private class DSALinkedListIterator<E extends Serializable> implements Iterator<E>, Serializable
    {
        private DSALinkedList<E>.DSAListNode<E> iterNext;
        
        /************************************************************
         *DEFAULT CONSTRUCTOR
         *IMPORT: none
         *EXPORT: none
         *ASSERTION: Creates a DSALinkedListIterator
         ************************************************************/
        public DSALinkedListIterator(DSALinkedList<E> theList)
        {
            iterNext = theList.head;
        }
        
        /************************************************************
         *SUBMODULE: hasNext
         *IMPORT: none
         *EXPORT: boolean
         *ASSERTION: Returns true if iterNext is not null and false otherwise
         ************************************************************/
        public boolean hasNext()
        {
            return (iterNext != null);
        }
        
        /************************************************************
         *SUBMODULE: next
         *IMPORT: none
         *EXPORT: value(E)
         *ASSERTION: Returns the value of current node and goes to the next node
         ************************************************************/
        public E next()
        {
            E value;
            if(iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }
        
        /************************************************************
         *SUBMODULE: remove
         *IMPORT: none
         *EXPORT: none
         *ASSERTION: Throws an exception when called as remove is not supported in iterator
         ************************************************************/
        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    /************************************************************
     *DEFAULT CONSTRUCTOR
     *IMPORT: none
     *EXPORT: none
     *ASSERTION: Creates a DSALinkedList that is empty
     ************************************************************/
    public DSALinkedList()
    {
        head = null;
        tail = null;
        count = 0;
    }

    public int getCount()
    {
        return count;
    }
    
    /************************************************************
     *SUBMODULE: insertFirst
     *IMPORT: newValue(E)
     *EXPORT: none
     *ASSERTION: Adds a new node to the start of the list
     ************************************************************/
    public void insertFirst(E newValue)
    {
        DSAListNode<E> newNd, currNd;
        newNd = new DSAListNode<E>(newValue, null , null);
        if(isEmpty())
        {
            head = newNd;
            tail = newNd;
        }
        else
        {
            currNd = head;
            currNd.setPrev(newNd);
            newNd.setNext(head);
            head = newNd;
        }
        count++;
    }

    /************************************************************
     *SUBMODULE: insertLast
     *IMPORT: newValue(E)
     *EXPORT: none
     *ASSERTION: Adds a new node to the end of the list
     ************************************************************/
    public void insertLast(E newValue)
    {
        DSAListNode<E> newNd, currNd;
        newNd = new DSAListNode<E>(newValue, null, null);
        if(isEmpty())
        {
            head = newNd;
            tail = newNd;
        }
        else
        {
            currNd = tail;
            currNd.setNext(newNd);
            newNd.setPrev(currNd);
            tail = newNd;
        }
        count++;
    }

    /************************************************************
     *SUBMODULE: isEmpty
     *IMPORT: none
     *EXPORT: boolean
     *ASSERTION: Returns true if linkedlist is empty and false otherwise
     ************************************************************/
    public boolean isEmpty()
    {
        boolean empty = (head == null);
        return empty;
    }

    /************************************************************
     *SUBMODULE: peekFirst
     *IMPORT: none
     *EXPORT: nodeValue(E)
     *ASSERTION: Returns the value of the first node if not empty and fails otherwise
     ************************************************************/
    public E peekFirst()
    {
        E nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("DSALinkedList is empty");
        }
        else
        {
            nodeValue = head.getValue();
        }
        return nodeValue;
    }

    /************************************************************
     *SUBMODULE: peekLast
     *IMPORT: none
     *EXPORT: nodeValue(E)
     *ASSERTION: Returns the value of the last node if not empty and fails otherwise
     ************************************************************/
    public E peekLast()
    {
        E nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("DSALinkedList is empty");
        }
        else
        {
            nodeValue = tail.getValue();
        }
        return nodeValue;
    }

    /************************************************************
     *SUBMODULE: removeFirst
     *IMPORT: none
     *EXPORT: nodeValue(E)
     *ASSERTION: Returns the value of the first node and remove that node and set the new head if not empty and fails otherwise
     ************************************************************/
    public E removeFirst()
    {
        E nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("DSALinkedList is empty");
        }
        else if(head.getNext() == null)
        {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else
        {
            nodeValue = head.getValue();
            head = head.getNext();
            head.setPrev(null);
        }
        count--;
        return nodeValue;
    }

    /************************************************************
     *SUBMODULE: removeLast
     *IMPORT: none
     *EXPORT: nodeValue(E)
     *ASSERTION: Returns the value of the last node and remove that node and set the new last if not empty and fails otherwise
     ************************************************************/
    public E removeLast()
    {
        E nodeValue = null;
        if(isEmpty())
        {
            throw new IllegalArgumentException("DSALinkedList is empty");
        }
        else if(head.getNext() == null)
        {
            nodeValue = head.getValue();
            head = null;
            tail = null;
        }
        else
        {
            nodeValue = tail.getValue();
            tail = tail.getPrev();
            tail.setNext(null);
        }
        count--;
        return nodeValue;
    }
}

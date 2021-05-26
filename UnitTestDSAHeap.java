public class UnitTest
{
    public static void main(String args[])
    {
        DSAHeap heap = null;
        DSAHeap<String> heap = null;
        DSAQueue<String> keyQueue = null;
        DSAQueue<String> valueQueue = null;
        String string1 = null;
        String string2 = null;
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");
        try
        {   
            System.out.print("Testing default constructor of DSAHeap: ");
            heap = new DSAHeap();
            System.out.println("passed");
            
            System.out.print("Testing add: ");
            heap.add(10, "Ten");
            System.out.println("passed");
            
            System.out.print("Testing remove: ");
            string1 = heap.remove(10, "Ten");
            if(string1.equals("Ten"))
            {
                System.out.println("passed");
            }
            else
            {
                throw new IllegalArgumentException("FAILED");
            }
            
            System.out.print("Testing multiple add: ");
            heap.add(10, "Ten");
            heap.add(5, "Five");
            heap.add(60, "Sixty");
            heap.add(50, "Fifty");
            heap.add(35, "Thirty-five");
            System.out.println("passed");
                        
            System.out.print("Testing find of a key that exists in the heap: ");
            string1 = new String("C");
            string2 = heap.find("C");
            if(string1.equals(string2))
            {
                System.out.println("passed");
            }
            else
            {
                throw new IllegalArgumentException("FAILED");
            }
            
            System.out.print("Testing delete of a heap node: ");
            heap.delete("C");
            try
            {
                heap.find("C");
                throw new IllegalArgumentException("FAILED");
            }
            catch(Exception e) { System.out.println("passed"); }
            
            System.out.print("Testing Pre Order Traversal: \n");
            keyQueue = new DSAQueue<String>();
            valueQueue = new DSAQueue<String>();
            heap.traversePreOrder(keyQueue, valueQueue);
            while(!keyQueue.isEmpty())
            {
                System.out.println(keyQueue.dequeue() + ", " + valueQueue.dequeue());
            }
            System.out.println("passed");
            
            System.out.print("Testing In Order Traversal: \n");
            keyQueue = new DSAQueue<String>();
            valueQueue = new DSAQueue<String>();
            heap.traverseInOrder(keyQueue, valueQueue);
            while(!keyQueue.isEmpty())
            {
                System.out.println(keyQueue.dequeue() + ", " + valueQueue.dequeue());
            }
            System.out.println("passed");
            
            System.out.print("Testing Post Order Traversal: \n");
            keyQueue = new DSAQueue<String>();
            valueQueue = new DSAQueue<String>();
            heap.traversePostOrder(keyQueue, valueQueue);
            while(!keyQueue.isEmpty())
            {
                System.out.println(keyQueue.dequeue() + ", " + valueQueue.dequeue());
            }
            System.out.println("passed");
            
            System.out.print("Testing min of heap: ");
            string1 = new String("A");
            string2 = heap.find("A");
            if(string1.equals(string2))
            {
                System.out.println("passed");
            }
            else
            {
                throw new IllegalArgumentException("FAILED");
            }
            
            System.out.print("Testing max of heap: ");
            string1 = new String("M");
            string2 = heap.find("M");
            if(string1.equals(string2))
            {
                System.out.println("passed");
            }
            else
            {
                throw new IllegalArgumentException("FAILED");
            }
            
            System.out.print("Testing max of heap: ");
            string1 = new String("M");
            string2 = heap.find("M");
            if(string1.equals(string2))
            {
                System.out.println("passed");
            }
            else
            {
                throw new IllegalArgumentException("FAILED");
            }
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
        
        try
        {
            System.out.print("Testing adding a node that is already in heap: ");
            heap.add("A", "A");
            System.out.println("FAILED");
        }
        catch(Exception e) { System.out.println("passed"); }
        try
        {
            System.out.print("Testing finding a node that does not exist in heap: ");
            heap.find("Z");
            System.out.println("FAILED");
        }
        catch(Exception e) { System.out.println("passed"); }
        
        try
        {
            System.out.print("Testing deletion of node that does not exist in heap: ");
            heap.delete("P");
            System.out.println("FAILED");
        }
        catch(Exception e) { System.out.println("passed"); }
    }
}

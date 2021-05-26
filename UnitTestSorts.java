public class UnitTestSorts
{
    public static void main(String[] args)
    {
        System.out.println("Testing Merge Sort");
        String[][] array =  new String[10][1];
        for(int i = array.length - 1; i >= 0; i--)
        {
            array[i][0] = Integer.toString(i);
        }
        Sorts.mergeSort(array, 0);
        
        for(int i = 0; i < array.length; i++)
        {
            System.out.println(array[i][0]);
        }
    }
}
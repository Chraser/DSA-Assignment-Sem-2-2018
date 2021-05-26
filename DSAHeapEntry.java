public class DSAHeapEntry
{
    private int priority;
    private Object value;

    public DSAHeapEntry(int inPriority, Object inValue)
    {
        priority = inPriority;
        if(inValue != null)
        {
            value = inValue;
        }
        else
        {
            throw new IllegalArgumentException("Value for DSAHeapEntry can't be null");
        }
    }

    public int getPriority()
    {
        return priority;
    }

    public Object getValue()
    {
        return value;
    }
    
    public void setPriority(int inPriority)
    {
        priority = inPriority;
    }

    public void setValue(Object inValue)
    {
        if(inValue != null)
        {
            value = inValue;
        }
        else
        {
            throw new IllegalArgumentException("Value can't be null");
        }
    }
}

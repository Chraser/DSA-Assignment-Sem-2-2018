public class DSAHeap
{
    private DSAHeapEntry[] m_heap;
    private int m_count;

    public DSAHeap(int maxSize)
    {
        m_heap = new DSAHeapEntry[maxSize];
        m_count = 0;
    }

    public void add(int priority, Object value)
    {
        DSAHeapEntry entry = new DSAHeapEntry(priority, value);

        m_heap[m_count] = entry;
        m_count++;
        trickleUp(0);
    }
    
    public Object remove()
    {
        Object value;
        m_count--;
        value = m_heap[0].getValue();
        m_heap[0] = m_heap[m_count];
        m_heap[m_count] = null;
        trickleDown(0, m_count);
        return value;
    }

    public void heapSort(DSAHeapEntry[] heapArr, int numItems)
    {
        m_heap = heapArr;
        m_count = numItems;
        heapify();
        for(int ii = numItems - 1; ii >= 1; ii--)
        {
            swap(0, ii);
            trickleDown(0, ii);
        }
    }
    
    private void heapify()
    {
        for(int ii = (m_count/2)-1; ii >=0; ii--)
        {
            trickleDown(ii, m_count);
        }
    }
    
    private void trickleUp(int curIdx)
    {
        int parentIdx = (curIdx-1)/2;
        if(curIdx > 0)
        {
            if(m_heap[curIdx].getPriority() > m_heap[parentIdx].getPriority())
            {
                swap(parentIdx, curIdx);
                trickleUp(parentIdx);
            }
        }
    }

    private void trickleDown(int curIdx, int numItems)
    {
        int largeIdx;
        int lChildIdx = curIdx * 2 + 1;
        int rChildIdx = lChildIdx + 1;

        if(lChildIdx < numItems)
        {
            largeIdx = lChildIdx;
            if(rChildIdx < numItems)
            {
                if(m_heap[lChildIdx].getPriority() < m_heap[rChildIdx].getPriority())
                {
                    largeIdx = rChildIdx;
                }
            }
            if(m_heap[largeIdx].getPriority() > m_heap[curIdx].getPriority())
            {
                swap(largeIdx, curIdx);
                trickleDown(largeIdx, curIdx);
            }
        }
    }
    
    private void swap(int index1, int index2)
    {
        DSAHeapEntry temp = m_heap[index1];
        m_heap[index1] = m_heap[index2];
        m_heap[index2] = temp;
    }
}

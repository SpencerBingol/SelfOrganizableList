public interface SelfOrganizableListInterface<T>{
    public boolean isEmpty();
    public int size();
    
    /**
     * Add item at the beginning of the list, return true (indicates success).
     */
    public boolean add(T item);
   
    /**
     * Add item at given position, where 1 <= position <= numberOfEntries+1, then return true; otherwise, return false.
     */
    public boolean add(int position, T item);
    
    /**
     * Search list for item from firstNode, return number of accesses to find item.
     */
    public int searchElement(T item);
    
    /**
     * Search list for item from firstNode, return number of accesses to find it, then move item in this list based on accessCount strategy.
     */ 
    public int searchElementAccessCount(T item);
    
    /**
     * Search list for item from firstNode, return number of accesses to find it, then move item in this list based on move-to-front strategy.
     */ 
    public int searchElementMTF(T item);
    
    /**
     * Search list for item from firstNode, return number of accesses to find it, then move item in this list based on swap-toward-front strategy.
     */ 
    public int searchElementSwap(T item);
    
    /**
     * Remove item at specified position, 1 <= position <= numberOfEntries, then return a reference to the removed object; otherwise, return null (indicates failure).
     */ 
    public T remove(int position);
}
import java.util.Iterator;

public class SelfOrganizableList<T> implements SelfOrganizableListInterface, Iterable<T> {  
  private int numberOfElements;                          //Keeps track of number of elements stored
  private Node firstNode;                                 //The current first data piece in storage
  
  public SelfOrganizableList() {
    numberOfElements = 0;
    firstNode = null;
  }
  
  public boolean isEmpty() {
    return (firstNode == null);
  }
  
  public int size() {
    return numberOfElements;
  }
  
  /**
   * Add item at the beginning of the list, return true (indicates success).
   */
  public boolean add(Object item) {
    T t = (T) item;
    Node tmp = new Node(t);

    return add(tmp);
  }
  
  private boolean add(Node tmp) {
    if (isEmpty()) 
      firstNode = tmp;
    else {
      tmp.setNext(firstNode);
      if (numberOfElements == 1) {
        tmp.setPrev(firstNode);
        firstNode.setNext(tmp);
      } else {
        tmp.setPrev(firstNode.getPrev());
      } 
      
      firstNode.setPrev(tmp);
      firstNode = tmp;
    } 
    
    numberOfElements++;
    return true;
  }
 
  /**
   * Add item at given position, where 1 <= position <= numberOfEntries+1, then return true; otherwise, return false.
   */  
  public boolean add(int position, Object item) {
    //position--;
    T t = (T) item;
    Node tmp = new Node(t);

    return add(position, tmp);
  }
  
  private boolean add (int position, Node tmp) {
    Node current = firstNode;
    int count = 0;
    if (position < 0 || position > numberOfElements) return false;
    
    else if (position == 0) {
      add (tmp);
      return true;
    }
    
    else if (position == numberOfElements) {
      while (current.getNext() != null) {
        current = current.getNext();
        count++;
      } 
      current.setNext(tmp);
      tmp.setPrev(current);
    }
    
    else {      
      while (count < position) {  
        current = current.getNext();
        count++;
      }
    
      current.getPrev().setNext(tmp);
      tmp.setNext(current);
      tmp.setPrev(current.getPrev());
      current.setPrev(tmp);
    } 
    
    numberOfElements++;
    return true;
  }
  
  /**
   * Search list for item from firstNode, return number of accesses to find item.
   */   
  public int searchElement(Object item) {
    T t = (T) item;
    Node current = firstNode;
    int count = 0;
    
    while (count < numberOfElements) {
      if (t.equals(current.getData())) return count+1;
      
      if (current.getNext() == null) break;
      
      current = current.getNext();
      count++;
    }
    
    return -1;
  }
 
  
  /**
   * Search list for item from firstNode, return number of accesses to find it, then move item in this list based on accessCount strategy.
   */ 
  public int searchElementAccessCount(Object item) {
    T t = (T) item;
    Node current = firstNode;
    int position = -1;
    int count = 0;
    
    while (count < numberOfElements) {
      if (t.equals(current.getData())) {
        current.accessCount++;
        position = count;
        break;
      }
      
      if (current.getNext()==null) break;
      
      current = current.getNext();
      count++;
    }
    
    if (position != -1) { 
      Node n = current;
      while (count > 0 && n.getAccessCount() > current.getPrev().getAccessCount()) {
        current = current.getPrev();
        count--;
      }
      
      if (count == 0 && n.getAccessCount() > current.getAccessCount()) {
        remove(position);
        add(n);
      }
      
      else {
        remove(position);
        add(count, n);
      }
    }
    
    return position;
  }
  
  
  /**
   * Search list for item from firstNode, return number of accesses to find it, then move item in this list based on move-to-front strategy.
   */ 
  public int searchElementMTF(Object item) {
    T t = (T) item;
    Node tmp = new Node (t);
    Node current = firstNode;
    int position = -1;
    int count = 0;
    
    while (count < numberOfElements) {
      if (t.equals(current.getData())) {
        position = count;
        break;
      }
      current = current.getNext();
      count++;
    }
    
    if (position != -1) {
      remove(position);
      add(current);
    }
    
    return position;
  }

  /**
   * Search list for item from firstNode, return number of accesses to find it, then move item in this list based on swap-toward-front strategy.
   */     
  public int searchElementSwap(Object item) {
    T t = (T) item;
    Node tmp = new Node (t);
    Node current = firstNode;
    int position = -1;
    int count = 0;
    Node n = null;
    
    while (count < numberOfElements) {
      if (t.equals(current.getData())) {
        n = current;
        position = count;
        break;
      }
      
      current = current.getNext();
      count++;
    }
    
    if (position != -1) {
      if (position>1) {    
        remove (position);
        add(position-1, n);
      } else if (position == 1) {
        remove(position);
        add(n);
      }
    } return position;
  }
  
  /**
   * Remove item at specified position, 1 <= position <= numberOfEntries, then return a reference to the removed object; otherwise, return null (indicates failure).
   */ 
  public T remove(int position) {
    //position--;
    Node current = firstNode;
    int count = 0;
    
    if (position < 0 || position >= numberOfElements) return null;
    while (count < position) {
      current = current.getNext();
      count++;
    }
    
    T data = current.getData();
    
    if (position == (numberOfElements - 1)) {
      if (position == 0) firstNode = null;
      
      else current.getPrev().setNext(null);
    }
    
    else {
      if (position == 0) firstNode = current.getNext();
      
      else {
        if (current.getNext() != null) {
          current.getNext().setPrev(current.getPrev());
          current.getPrev().setNext(current.getNext());
        }
        
        else current.getPrev().setNext(null);
      }
    }
    numberOfElements--;  
    return current.getData();
  }
  
  public String toString() {
    String s = "[";
    int count = 0;
    Node current = firstNode;
    while (count < numberOfElements) {
      if (current.getNext()==null) s += current.getData().toString() + "]";
      else s += current.getData().toString() + ", ";
      current = current.getNext();
      count++;
    }
    return s;
  }
  
  public String display() {
    String s = "[";
    Iterator itr = iterator();
    int count = 0;
    Node current = firstNode;
    
    while (count < numberOfElements) {
      if (count == numberOfElements-1) s+= current.getData().toString() + " (" + current.getAccessCount() + ")]";
      else s += current.getData().toString() + " (" + current.getAccessCount() + "), ";
      current = current.getNext();
      count++;
    }
    return s;
  }
  
  public int[] accessRecord() {
    int count = 0;
    int[] s = new int[numberOfElements];
    Node current = firstNode;
    while (count < numberOfElements) {
      s[count] = current.getAccessCount();
      current = current.getNext();
      count++;
    }
    return s;
  }
  
  public String[] toArray() {
    int count = 0;
    String[] s = new String[numberOfElements];
    Node current = firstNode;
    while (count < numberOfElements) {
      s[count] = current.toString();
      current = current.getNext();
      count++;
    }
    return s;   
  }
  
  public Iterator<T> iterator() {
    return new SOLIterator();
  }
 
  private class Node {
    private T data;
    private int accessCount;
    private Node prev;
    private Node next;
    
    private Node (T data) {
      this.data = data;
      accessCount = 0;
    }
    
    private T getData () {
      return data;
    }
    
    private int getAccessCount() {
      return accessCount;
    }
    
    private Node getPrev() {
      return prev;
    }
    
    private Node getNext() {
      return next;
    }
    
    private void setPrev(Node prev) {
      this.prev = prev;
    }
    
    private void setNext(Node next) {
      this.next = next;
    }
    
    public String toString() {
      return data.toString();
    }
  }
  
  private class SOLIterator implements Iterator<T>{
    private int count;
    private Node current;
    
    public SOLIterator () {
      count = 0;
      current = firstNode;
    }
    
    public boolean hasNext() {
      return count<numberOfElements;
    }
    
    public T next () {
      T data = null;
      if (hasNext()) {
        data = current.getData();
        current = current.getNext();           
      }
      return data;
    }
      
      
    public void remove () {
      throw new UnsupportedOperationException ();
    }
  }
}

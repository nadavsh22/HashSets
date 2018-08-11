import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by nadav on 30-Apr-17.
 * a wrapper for javas LinkedList class, to be used by the hash sets.
 */
public class LinkedListHelper {
    private LinkedList<String> linkedList;

    /**
     * constructor initiating a linked list.
     */
    public LinkedListHelper() {
        this.linkedList = new LinkedList<String>();
    }

    /**
     * adds a given object to the end of the list
     *
     * @param string
     * @return true if object was added
     */
    public boolean add(String string) {
        return (linkedList.add(string));
    }

    /**
     * removes the first occurrence of an object from the list.
     *
     * @param string
     * @return true if object was found and removed
     */
    public boolean remove(String string) {
        return (linkedList.remove(string));
    }

    /**
     * @return the number of elements on the list.
     */
    public int size() {
        return (linkedList.size());
    }

    /**
     * checks if an object appears on list
     *
     * @param string
     * @return true if list contains object, false otherwise.
     */
    public boolean contains(String string) {
        return linkedList.contains(string);
    }

    public String pop() {
        return linkedList.pop();
    }

}

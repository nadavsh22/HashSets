import java.util.Collection;

/**
 * Created by nadav on 30-Apr-17.
 */
public class CollectionFacadeSet implements SimpleSet {
    protected java.util.Collection<java.lang.String> collection;

    /**
     * Creates a new facade wrapping the specified collection.
     *
     * @param collection
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {
        this.collection = collection;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return
     */
    public boolean add(String newValue) {
        return this.collection.add(newValue);
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(String searchVal){
        return this.collection.contains(searchVal);
}
    /**
     * @return The number of elements currently in the set
     */
    public int size() {
        return this.collection.size();
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        return this.collection.remove(toDelete);
    }


}

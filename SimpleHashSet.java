/**
 * Created by nadav on 30-Apr-17.
 */
public abstract class SimpleHashSet implements SimpleSet {
    public final int INITIAL_CAPACITY = 16;
    protected int capacity;
    private final float defaultUpperLoadFactor = 0.75f;
    private final float defaultLowerLoadFactor = 0.25f;
    private float upperLoadFactor, lowerLoadFactor;
    protected int size;
    protected final boolean INCREASE = true;//represents increasing the capacity of a hash table
    protected final boolean DECREASE = false;//represents decreasing the capacity of a hash table

    public SimpleHashSet() {
        this.lowerLoadFactor = defaultLowerLoadFactor;
        this.upperLoadFactor = defaultUpperLoadFactor;
        size = 0;
    }

    public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
        size = 0;


    }

    public SimpleHashSet(java.lang.String[] data) {
        this.lowerLoadFactor = defaultLowerLoadFactor;
        this.upperLoadFactor = defaultUpperLoadFactor;
        size = 0;

    }

    /**
     *
     * @return the current capacity of the hashTable
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    public abstract boolean add(String newValue);

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public abstract boolean contains(String searchVal);

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public abstract boolean delete(String toDelete);

    /**
     * @return The number of elements currently in the set
     */
    public int size() {
        return this.size;
    }

    ;

    /**
     * @return the upperLoadFactor
     */
    public float getUpperLoadFactor() {
        return this.upperLoadFactor;
    }

    public float getLowerLoadFactor() {
        return this.lowerLoadFactor;
    }

    /**
     * calculates the current loadFactor of a table.
     *
     * @return the current load factor.
     */
    protected float calcLoadFactor() {
        return (((float) this.size()) / this.capacity);
    }


}

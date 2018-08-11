import java.util.Objects;

/**
 * Created by nadav on 30-Apr-17.
 */
public class ClosedHashSet extends SimpleHashSet {
    private String[] hashTable = new String[INITIAL_CAPACITY];
    private final String EMPTY = "";//an empty string representing an empty cell that its value was
    // previously deleted

    /**
     * a constructor initializing a hash table with default size and load factor limits.
     */
    public ClosedHashSet() {
        super();
        capacity = hashTable.length;
    }

    /**
     * a constructor initializing a hash table with default size and specified load factor limits.
     *
     * @param lowerLoadFactor
     * @param upperLoadFactor
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        capacity = hashTable.length;
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values  ignored.
     *
     * @param data values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data) {
        super(data);
        capacity = hashTable.length;
        for (int i = 0; i < data.length; i++) {
            this.add(data[i]);
        }
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(String toDelete) {
        if (this.contains(toDelete)) {
            int attempt = 0;
            int index = clamp(toDelete, attempt);
            while (!Objects.equals(toDelete, hashTable[index])) {
                attempt++;
                index = clamp(toDelete, attempt);
            }
            hashTable[index] = EMPTY;
            size--;
            if (calcLoadFactor() < getLowerLoadFactor()) {
                this.reHash(DECREASE);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns the index a string should be inserted in.
     *
     * @param string
     * @return the index representing the strings location in table.
     */
    public int clamp(String string, int attempt) {
        capacity = hashTable.length;
        int index = (string.hashCode() + (attempt + attempt * attempt) / 2) & (capacity - 1);
        return index;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    public boolean add(String newValue) {
        if (!this.contains(newValue)) {
            this.insert(newValue);
            size++;
            if (calcLoadFactor() > getUpperLoadFactor()) {
                this.reHash(INCREASE);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * inserts a given value in a vacant index in hash table.
     *
     * @param newValue the value to be inserted.
     */
    private void insert(String newValue) {
        int attempt = 0;
        int index = clamp(newValue, attempt);
        while (hashTable[index] != null && (!Objects.equals(hashTable[index], EMPTY))) {
            attempt++;
            index = clamp(newValue, attempt);
        }
        hashTable[index] = newValue;
    }

    /**
     * reHashes the table, increasing/decreasing its size by (*2) or (/2) and re-distributing the elements.
     *
     * @param indicator if true, increases hash table capacity, decreases when false
     */
    private void reHash(boolean indicator) {
        if (indicator) {
            this.capacity = capacity * 2;
        } else {
            this.capacity = capacity / 2;
        }
        String[] tempArray = this.hashTable;
        hashTable = new String[capacity];
        for (int i = 0; i < tempArray.length; i++) {
            if (tempArray[i] != null && tempArray[i] != EMPTY) {
                insert(tempArray[i]);
            }
        }
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        int attempt = 0;
        int index = clamp(searchVal, attempt);
        if (hashTable[index] != null) {
            while (!Objects.equals(searchVal, hashTable[index])) {
                attempt++;
                index = clamp(searchVal, attempt);
                if (hashTable[index] == null) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }

    }
}

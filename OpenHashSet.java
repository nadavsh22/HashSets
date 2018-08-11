/**
 * Created by nadav on 30-Apr-17.
 */
public class OpenHashSet extends SimpleHashSet {
    private LinkedListHelper[] hashTable = new LinkedListHelper[INITIAL_CAPACITY];

    /**
     * a constructor initializing a hash table with default size and load factor limits.
     */
    public OpenHashSet() {
        super();
        capacity = hashTable.length;
    }

    /**
     * a constructor initializing a hash table with default size and specified load factor limits.
     *
     * @param lowerLoadFactor
     * @param upperLoadFactor
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        capacity = hashTable.length;
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values  ignored.
     *
     * @param data values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data) {
        super(data);
        capacity = hashTable.length;
        for (int i = 0; i < data.length; i++) {
            this.add(data[i]);
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set
     * @return False if newValue already exists in the set
     */
    public boolean add(String newValue) {
        if (!this.contains(newValue)) {
            int index = clamp(newValue);
            if (hashTable[index] == null) {
                hashTable[index] = new LinkedListHelper();
            }
            this.hashTable[index].add(newValue);
            this.size++;
            if (calcLoadFactor() > getUpperLoadFactor()) {
                this.reHash(INCREASE);
            }
            return true;
        } else {
            return false;
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
            this.hashTable[clamp(toDelete)].remove(toDelete);
            this.size--;
            if (calcLoadFactor() < getLowerLoadFactor()) {
                this.reHash(DECREASE);
            }
            return true;
        } else {
            return false;
        }
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
        LinkedListHelper[] tempArray = new LinkedListHelper[this.capacity];
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                while (hashTable[i].size() != 0) {
                    String currentString = hashTable[i].pop();
                    int index = clamp(currentString);
                    if (tempArray[index] == null) {
                        tempArray[index] = new LinkedListHelper();
                    }
                    tempArray[index].add(currentString);
                }
            }
        }
        this.hashTable = tempArray;
    }

    /**
     * returns the index a string should be inserted in.
     *
     * @param string
     * @return the index representing the strings location in table.
     */
    public int clamp(String string) {
        return (string.hashCode() & (capacity - 1));
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(String searchVal) {
        int index = clamp(searchVal);
        if (hashTable[index] == null) {
            return false;
        } else if (hashTable[index].contains(searchVal)) {
            return true;
        } else {
            return false;
        }
    }


}

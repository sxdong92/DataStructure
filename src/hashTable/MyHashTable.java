package hashTable;

/*********************************************************
 * 
 * 08-722 Data Structures for Application Programmers Homework 4 HashTable
 * Implementation
 * 
 * Andrew id: Name:
 * 
 *********************************************************/

public class MyHashTable implements MyHTInterface {
    private DataItem[] hashArray;
    // private int tableLength;
    private DataItem deletedItem; // flag to mark deleted spot.

    private int numOfCollisions;

    // record the table size;
    private int tableSize;

    // set the capacity to be 2^5;
    private static final int DEFAULT_CAPACITY = 10;

    private boolean rehashFlag;

    // TODO implement constructor with no initial capacity
    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    // TODO implement constructor with initial capacity
    public MyHashTable(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Input capacity is wrong!");
        }

        // set a default size.
        if (size == 0) {
            // size = DEFAULT_CAPACITY;
        }

        rehashFlag = false;
        tableSize = 0;
        numOfCollisions = 0;
        // tableLength = size;
        hashArray = new DataItem[size];

        // set the value of flagged item to be null;
        deletedItem = new DataItem(null, 0, 0);
    }

    @Override
    public void insert(String value) {
        insert_help(value, hashArray);
    }

    public void insert_help(String value, DataItem[] hashArray) {
        if (value == null) {
            return;
            // throw new IllegalArgumentException("Input capacity is wrong!");
        }

        /* record that the value has exited. */
        // boolean containFlag = contains(value);

        if (tableSize >= hashArray.length) {
            // System.out.printf("TABLE SIZE %d tableLength %d", tableSize,
            // hashArray.length);

            return;
            // throw new IllegalArgumentException("Input capacity is wrong!");
        }

        int hashVal = hashFunc(value);

        // it means that hashfunc has something wrong.
        if (hashVal < 0) {
            System.out.printf("Hash error!\n");
            return;
        }

        DataItem newItem = new DataItem(value, 1, hashVal);

        boolean collitionFlag = false;

        // System.out.printf("\n INSERT FUNC: hashVal: %d value: %s\n", hashVal,
        // value);

        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] != null && hashArray[i] != deletedItem) {
                if (hashArray[i].hash == hashVal) {
                    // System.out
                    // .printf("numOfCollisions: %d Collition: INPUT: %s hashArray[%d]:value %s hash %d \n",
                    // numOfCollisions, value, i,
                    // hashArray[i].value, hashArray[i].hash);
                    if (hashArray[i].value.equals(value) != true) {
                        // numOfCollisions++;
                    }

                }
            }

        }

        // loop through till it finds either empty or flagged cell
        // we need to reuse flagged cells
        while (hashArray[hashVal] != null && hashArray[hashVal] != deletedItem) {
            // if (collitionFlag == false) {
            // if (hashArray[hashVal] != null)
            // collitionFlag = true;
            // numOfCollisions++;

            // System.out.printf("ADD coll number: %d", numOfCollisions);
            // }
            /*
             * System.out.printf(
             * "\nhashArray[hashVal].value:%s hashArray[hashVal].frequency %d "
             * + " tableSIZE(%d) table length:%d\n", hashArray[hashVal].value,
             * hashArray[hashVal].frequency, tableSize, hashArray.length);
             */
            /* when find the item, just return. */
            if (hashArray[hashVal].value.equals(value)) {
                // System.out.printf("Add frequency \n");

                hashArray[hashVal].frequency++;
                return;
            } else if (collitionFlag == false) {
                // only add when different element is added.
                // if (hashArray[hashVal] != null)
                // collitionFlag = true;
                // numOfCollisions++;

                // System.out.printf("ADD coll number: %d", numOfCollisions);

            }

            hashVal++;
            hashVal = hashVal % hashArray.length;

            // System.out.printf("hashVal: %d\n", hashVal);
        }

        hashArray[hashVal] = newItem;
        tableSize++;

        // record the collision.
        // if (collitionFlag == true) {
        // numOfCollisions++;
        // }

        float factor = (float) tableSize / (float) hashArray.length;
        // factor = 0;
        /*
         * System.out.printf(
         * "Insert key:%s tableSize:%d tableLength:%d factor = %f\n", value,
         * tableSize, hashArray.length, factor);
         */
        if (factor > 0.5 && rehashFlag == false) {
            // System.out.printf("CALL REHASH\n");
            rehash();
            rehashFlag = false;
        }

    }

    // translate the char to int.
    private int charToInt(final char value) {
        int ascii = (int) value;
        int result = ascii - (int) 'a' + 1;

        // System.out.printf("result of ascii: %d\n", result);

        return result;
    }

    @Override
    public int size() {
        // return the size.
        return tableSize;
    }

    @Override
    public void display() {
        for (int i = 0; i < hashArray.length; i++) {
            DataItem dataItem = hashArray[i];
            if (dataItem != null) {
                if (dataItem.value != null) {
                    System.out.printf("[%s,%d] ", dataItem.value,
                            dataItem.frequency);
                } else {
                    System.out.printf("#DEL# ");
                }
            } else {
                System.out.printf("** ");
            }
        }

        System.out.printf("\n");
    }

    @Override
    public boolean contains(String key) {
        return findItem(key) != null;
    }

    @Override
    public int numOfCollisions() {
        // int num;

        int hashTemp;

        for (int i = 0; i < hashArray.length - 1; i++) {
            if (hashArray[i] != null && hashArray[i] != deletedItem) {
                hashTemp = hashArray[i].hash;
            } else {
                continue;
            }

            for (int j = i + 1; j < hashArray.length; j++) {
                if (hashArray[j] != null && hashArray[j] != deletedItem) {

                    if (hashArray[j].hash == hashTemp) {
                        // System.out.printf("i = %d j = %d \n", i, j);
                        numOfCollisions++;
                    }
                }
            }
        }
        /*
         * hashArray newCount =
         * 
         * for (int i = 0; i < hashArray.length; i++) { if (hashArray[i] != null
         * && hashArray[i] != deletedItem) { if (hashArray[i].hash == hashVal) {
         * System.out .printf(
         * "numOfCollisions: %d Collition: INPUT: %s hashArray[%d]:value %s hash %d \n"
         * , numOfCollisions, value, i, hashArray[i].value, hashArray[i].hash);
         * if (hashArray[i].value.equals(value) != true) { numOfCollisions++; }
         * 
         * } }
         * 
         * }
         */

        return numOfCollisions;
    }

    @Override
    public int hashValue(String value) {
        // TODO Auto-generated method stub
        // System.out.printf("HASHVALUE: value:%s\n", value);

        return hashFunc(value);
    }

    /* return the dataItem, if not found, return null */
    private DataItem findItem(String key) {
        // can't be null
        if (key == null) {
            return null;
        }

        // it means that how many elements we have scan.
        int countNum = 0;

        int hashVal = hashFunc(key);

        while (hashArray[hashVal] != null) {
            countNum++;

            // the scan count can't be more than countNum
            if (countNum > hashArray.length) {
                return null;
            }

            String str = hashArray[hashVal].value;
            if (str != null && str.equals(key)) {
                return hashArray[hashVal];
            }

            hashVal++;
            hashVal = hashVal % hashArray.length;
        }

        return null;
    }

    @Override
    public int showFrequency(String key) {
        DataItem dataItem = findItem(key);

        if (dataItem == null) {
            System.out.printf("Not found key\n");
            return 0;
        }

        return dataItem.frequency;
    }

    @Override
    public String remove(String key) {
        // TODO Auto-generated method stub

        DataItem dataItem = findItem(key);

        if (dataItem == null) {
            return null;
        }

        String tempStr = dataItem.value;

        dataItem.value = null;
        dataItem.frequency = 0;

        return tempStr;
    }

    /*
     * Instead of using String's hashCode, you are to implement your own here,
     * taking the table length into your account.
     * 
     * In other words, you are to combine the following two steps into one step
     * here. 1. converting Object into integer value 2. compress into the table
     * using modular hashing
     * 
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 +
     * 19*27^0 = 60,337
     * 
     * But, to make the hash process faster, Horner's method should be applied
     * as follows; var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be
     * rewritten as (((var4*n + var3)*n + var2)*n + var1)*n + var0
     * 
     * Note: This is to have you experience of implementing HashTable. 27 is an
     * example given to you to see how it behaves. If you have time, I would
     * encourage you to try with a prime number, not 27. And compare the results
     * but it is not required.
     */
    private int hashFunc(String input) {
        // the number to be mutiply
        int n = 27;

        if (input == null) {
            return -1;
        }

        int strLen = input.length();

        // strin can't be ""
        if (strLen <= 0) {
            return -1;
        }

        // int hashValue = charToInt(input.charAt(0));
        long hashValue = 0;

        // System.out.printf("String leng:%d\n", strLen);

        // ((var4*n + var3)*n + var2)*n...
        for (int i = 0; i < strLen; i++) {
            hashValue = hashValue * n + charToInt(input.charAt(i));
            /*
             * System.out.printf("hashValue:%d count:%c\n", hashValue,
             * input.charAt(i));
             */
        }

        // hashValue should be bigger than 0.
        if (hashValue < 0) {
            hashValue = -hashValue;
        }

        hashValue = hashValue % hashArray.length;
        if (hashValue < 0) {
            System.out.printf("hash: out of range!\n");
            return 0;
        }

        /*
         * if (hashValue % tableLength >= 0) { hashValue = hashValue %
         * tableLength; } else { hashValue = (hashValue % tableLength) +
         * tableLength; }
         */

        /*
         * System.out.printf("hashVal value of %s: %d TableLength: %d\n", input,
         * hashValue, hashArray.length);
         */
        return (int) hashValue;
    }

    // test the number , if it is prime.
    private boolean isPrimeNum(int n) {
        if (n <= 0) {
            return false;
        } else if (n <= 2) {
            return true;
            // } else if (n % 2 == 0) {
            // return false;
        } else {
            // for (int i = 3; i < n; i = i + 2) {
            for (int i = 3; i < n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // doubles array length and rehash items whenever the load factor is reached
    private void rehash() {
        int newSize = hashArray.length * 2;
        if (newSize <= 0) {
            throw new IllegalArgumentException(
                    "Capacity is out of bound! Not enough space");
        }

        rehashFlag = true;

        while (!isPrimeNum(newSize)) { // if the new size is not a prime number
                                       // then check the next number
            newSize++;
        }

        /* reset the tablesize */
        tableSize = 0;
        numOfCollisions = 0;

        DataItem[] hashArrayNew = new DataItem[newSize];

        DataItem[] hashArrayOld = hashArray;
        hashArray = hashArrayNew;

        /* scan the old array and add all the elements. */
        for (int i = 0; i < hashArrayOld.length; i++) {
            DataItem tempDataItem = hashArrayOld[i];
            if (tempDataItem != null && // empty item.
                    tempDataItem.value != null // deteleted item.
            ) {
                /*
                 * System.out.printf("rehash: frequency is %d\n",
                 * tempDataItem.frequency);
                 */
                for (int j = 0; j < tempDataItem.frequency; j++) {
                    this.insert_help(tempDataItem.value, hashArrayNew);
                    // insert
                    // the
                    // data
                    // Frequency times.
                }

            }
        }

        // System.out.printf("Call rehash, new size: %d\n", newSize);

        // refresh the hashArray.
        hashArray = hashArrayNew;
        System.out.printf("* Rehashing %d items, new size is %d\n", tableSize,
                newSize);

        // tableSize = newSize;
    }

    // private static data item nested class
    private static class DataItem {
        private String value;

        // what do we do for it?
        private int frequency;

        private int hash;

        // TODO implement constructor and methods
        private DataItem(String value, int frequency, int hash) {
            this.value = value;

            /* when creating the DataItem, set it to be 1. */
            this.frequency = frequency;
            this.hash = hash;
        }
    }

}

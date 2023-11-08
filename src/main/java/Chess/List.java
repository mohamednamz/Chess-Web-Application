package Chess;

public class List<T> {
    T[] arr = (T[]) new Object[1];


    int size = 0;

    public void add(T element) {
        if (size >= arr.length) {
            grow();
        }
        arr[size] = element;
        size++;
    }

    private void grow() {
        T[] newArr = (T[]) new Object[arr.length * 2];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public void FastRemove(int index) {
        arr[index] = null;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == null) {
                arr[i] = arr[i + 1];
                arr[i + 1] = null;
            }
        }
    }

    public int size() {
        int size = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                size++;
            }
        }
        return size;
    }

    public boolean isEmpty() {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                return false;
            }
        }
        return true;
    }

    public T[] toArray() {
        T[] newArray = (T[]) new Object[arr.length];

        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        return newArray;
    }

    public void appendElementToList(T element) {

        if (size >= arr.length) {
            append();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] = element;
                print();
                return;
            }
        }
    }

    public void append() {
        T[] newArr = (T[]) new Object[arr.length + 1];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public boolean remove(int index) {
        if (index > arr.length) {
            return false;
        }
        arr[index] = null;
        shiftList(index);
        print();
        return true;
    }

    private void shiftList(int index) {
        T[] newArray = (T[]) new Object[arr.length - 1];

        for (int i = 0; i < newArray.length; i++) {
            if (i < index) {
                newArray[i] = arr[i];
            }
            if (i == index || i > index) {
                newArray[i] = arr[i + 1];
            }
        }
        size--;
        arr = newArray;
    }

    public T set(int index, T value) {
        T[] newArray = (T[]) new Object[1];
        int x = 0;

        newArray[x] = arr[index];
        arr[index] = value;
        value = newArray[x];
        print();
        System.out.println("Element that has been replaced: " + value);

        return value;
    }

    public boolean search(T value) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return true;
            }
        }
        return false;
    }


    public T get(int index) {
        return arr[index];
    }


    public void print() {

        //System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                if (i != arr.length - 1) {
                    System.out.print(arr[i] + ",");
                }
                if (i == arr.length - 1) {
                    System.out.print(arr[i]);
                }
            }
        }
        //System.out.print("]");
        System.out.println();
    }

    public void clear() {
        if (arr[0] != null) {
            remove(0);
        }
    }

    public int count() {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            counter++;
        }
        return counter;
    }


}

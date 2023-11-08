package Chess;

public class Stack<T> {

    int size = 0;

    T headOfStack;
    T[] arr = (T[]) new Object[1];

    public void push(T t) {
        headOfStack = t;

        if(size >= arr.length) {
            grow();
        }

        arr[size] = headOfStack;
        size++;
    }

    private void grow() {

        T[] newArr = (T[]) new Object[arr.length * 2];

        for( int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public T pop() {

        T thingToPop = headOfStack;

        if(thingToPop == null) {
            return null;
        }
        if(size == 1) {
            headOfStack = null;
            size--;
            arr[size] = null;
            return thingToPop;
        }

        T[] newArr = (T[]) new Object[size - 1];

        for( int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i];
        }

        arr = newArr;
        size--;
        headOfStack = arr[size - 1];

        return headOfStack;
    }

    public T peek() {return headOfStack;}

    public boolean isEmpty() {
        return arr[0] == null;
    }


}

public class Stack<T> {
    private T[] data;
    private int size;

    private int top = -1;

    public Stack(int capacity) {
        data = (T[]) new Object[capacity];
    }

    public void push(T element) {
        if (top == data.length - 1) top = -1;
        data[++top] = element;
    }

    public T pop() {
        T result = data[top];
        data[top] = null;
        if (--top < 0) {
            top = data.length - 1;
        }
        return result;
    }

    public T peek() {
        return data[top];
    }

    public void print() {
        Integer topReference = top;

        for (int i = 0; i < data.length; i++) {
            if (--topReference == -1) topReference = data.length - 1;
            if (data[topReference] != null) System.out.println("| " + data[topReference]);
        }
    }

}

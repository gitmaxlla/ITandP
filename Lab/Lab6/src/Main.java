public class Main {
    public static void main(String[] args) {
        Top10Words.main(null);
        System.out.println();

        Stack<Double> stack = new Stack<>(3);
        stack.push(20.0);
        stack.push(10.0);
        stack.push(5.0);
        System.out.println(stack.peek());
        stack.print();
        System.out.println("<- " + stack.pop());
        stack.print();
        System.out.println(stack.peek());
        System.out.println("<- " + stack.pop());
        System.out.println("<- " + stack.pop());
        System.out.println(stack.peek());
        System.out.println();

        Sales sales = new Sales();
        sales.addItem("Item0", 200);
        sales.addItem("Item1", 990);
        sales.addItem("Item2", 15);
        sales.printInfo();
        System.out.println(sales.totalSales());
        System.out.println(sales.bestseller());
        System.out.println();
    }
}
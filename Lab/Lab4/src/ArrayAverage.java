public class ArrayAverage {
    // ArithmeticException
    // private static Object[] arr = new Object[] {};
    private static Object[] arr = {1, 2, 3, 4, 5, 0};

    public static void main(String[] args) {
        int sum = 0;
        boolean noErrors = true;

        // ClassCastException
        // arr[5] = new Object();

        // NullPointerException
        //arr[5] = null;

        try {
            // NegativeArraySizeException
            // arr = new Object[sum - 10];
            for(int i = 0; i < getLength(); i++) sum += (Integer) arr[i];
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Error: " + ex.getMessage() + " - check if the length function is correct.");
            noErrors = false;
        } catch (ArithmeticException ex) {
            System.out.println("Error: " + ex.getMessage() + " - check if the array is not empty.");
            noErrors = false;
        } catch (ClassCastException ex) {
            System.out.println("Error: " + ex.getMessage() + " - check if the array elements can be cast to Integer.");
            noErrors = false;
        } catch (NullPointerException ex) {
            System.out.println("Error: " + ex.getMessage() + " - check if the provided values point to existing objects.");
            noErrors = false;
        } catch (NegativeArraySizeException ex) {
            System.out.println("Error: check if the provided array has non-negative size (method received size " + ex.getMessage() + ").");
            noErrors = false;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            noErrors = false;
        } finally {
            if (noErrors) System.out.println("Average value: " + sum / arr.length);
            else System.out.println("-> One or more errors occurred during execution, method cannot calculate the result.");
        }
    }

    private static int getLength() {
        // ArrayIndexOutOfBoundsException
        // return arr.length + 1;
        return arr.length;
    }
}

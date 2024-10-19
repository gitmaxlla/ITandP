import java.io.*;
import java.sql.Timestamp;

public class Arithmetic {
    public static double add(Object a, Object b) {
        a = checkVariable(a);
        b = checkVariable(b);
        return (double)a + (double)b;
    }

    public static double sub(Object a, Object b) {
        a = checkVariable(a);
        b = checkVariable(b);
        return (double)a - (double)b;
    }

    public static double mul(Object a, Object b) {
        a = checkVariable(a);
        b = checkVariable(b);
        return (double)a * (double)b;
    }

    public static double div(Object a, Object b) {
        a = checkVariable(a);
        b = checkVariable(b);
        if ((double)b == 0.0) {
            logMessage("Division by zero: " + a.toString() + " / " + b.toString());
            throw new CustomUnsupportedOperationException("Division by zero");
        }
        return (double)a / (double)b;
    }

    public static Object checkVariable(Object a) {
        if (!(a instanceof Number)) {
            logMessage("Provided object <" + a.getClass().getName() + " | toStr: " + a.toString() + "> can't be used as a number");
            throw new CustomUnsupportedOperationException("Provided object can't be used as a number");
        }
        if (a instanceof Float) return ((Float)a).doubleValue();
        return a;
    }

    private static void logMessage(String message) {
        try (FileWriter fw = new FileWriter("log.txt", true); BufferedWriter bw = new BufferedWriter(fw)) {
            File logFile = new File("log.txt");
            logFile.createNewFile();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            bw.write("[" + timestamp + "] CustomUnsupportedOperationException | " + message);
            bw.newLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

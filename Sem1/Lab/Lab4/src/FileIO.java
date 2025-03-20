import java.io.*;
import java.nio.file.ReadOnlyFileSystemException;

public class FileIO {
    public static void copyContents(String a, String b) {
        boolean noErrors = true;
        try (FileInputStream fin = new FileInputStream(a); FileOutputStream fout = new FileOutputStream(b)) {
            byte[] buffer = new byte[128];
            int count;

            while ((count = fin.read(buffer)) != -1) {
                fout.write(buffer, 0, count);
            }

            // EOFException
            // if (fin.read(buffer) == -1) throw new EOFException();

            // IOException
            // fout.close();
            // fout.write(new byte[] {1, 2, 3}, 0, 3);

        // UnsupportedOperationException ->
        } catch (ReadOnlyFileSystemException ex) {
            noErrors = false;
            System.out.println("This file system is set to be read only, cannot proceed. " + ex.getMessage());
        // IOException ->
        } catch (EOFException ex) {
            noErrors = false;
            System.out.println("End of file was has been reached. " + ex.getMessage());
        } catch (InterruptedIOException ex) {
            noErrors = false;
            System.out.println("Current write operation has been aborted. " + ex.getMessage());
        // Exception ->
        } catch (IOException ex) {
            noErrors = false;
            System.out.println("Input/Output exception has occurred during execution. " + ex.getMessage());
        } finally {
            if (!noErrors) System.out.println("One or more errors were caught during execution.");
            else System.out.println("Contents copied successfully.");
        }
    }
}

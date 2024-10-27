import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class DataManager {
    private ArrayList<Object> processors;
    private String data = "";
    private ArrayList<String> result = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        if (!Arrays.stream(processor.getClass().getMethods()).filter(method -> method.isAnnotationPresent(DataProcessor.class)).allMatch(method -> method.getParameterCount() == 1 && method.getParameters()[0].getType().getCanonicalName().equals("".getClass().getTypeName()) && method.getReturnType().getCanonicalName().equals("".getClass().getCanonicalName())))
            throw new IllegalArgumentException("Every @DataProcessor method should return String and have single String parameter.");
        processors.add(processor);
    }

    public void resetPipeline() {
        processors = new ArrayList<>();
    }

    public void loadData(String sourceURL) throws IOException {
        URL url = URI.create(sourceURL).toURL();
        Scanner scanner =  new Scanner(url.openStream());
        while(scanner.hasNext()) data += scanner.next();
    }

    public void setData(String value) {
        data = value;
    }

    public void processData() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(processors.size());
        ArrayList<Callable<ArrayList<String>>> tasks = new ArrayList<>();

        for(Object obj : processors) {
            List<Method> methods = Arrays.stream(obj.getClass().getMethods()).filter(method -> method.isAnnotationPresent(DataProcessor.class)).toList();

            System.out.println(obj.toString());
            for (Method method : methods) System.out.println(method.getAnnotation(DataProcessor.class).value() + " > " + method.getName());
            System.out.println();

            tasks.add(() -> {
                ArrayList<String> result = new ArrayList<>();
                result.add(obj.getClass().getCanonicalName());

                for (Method method : methods) {
                    try {
                        if (method.getAnnotation(DataProcessor.class).value() != DataProcessor.Type.IGNORE)
                        {
                            result.add("\n\t" + method.getAnnotation(DataProcessor.class).value().toString());
                            result.add(method.getName());
                            result.add((String) method.invoke(obj, data));
                        }
                        else {
                            method.invoke(obj, data);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                return result;
            });
        }

        List<Future<ArrayList<String>>> results = executorService.invokeAll(tasks);

        if (!results.isEmpty()) this.result.add(results.getFirst().get().toString());
        for(int i = 1; i < results.size(); i++) this.result.add("\n" + results.get(i).get().toString());

        executorService.shutdown();
        executorService.close();
    }

    public void saveData(String destination) throws IOException {
        File file = new File(destination);
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write(result.toString().toCharArray());
        fw.close();
    }

    public DataManager() {
        processors = new ArrayList<>();
    }
}

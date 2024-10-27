public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();

        try {
            dataManager.registerDataProcessor(new NumberPipeline());
            dataManager.registerDataProcessor(new StringPipeline("\\d{2}"));
            dataManager.registerDataProcessor(new HashPipeline());

            dataManager.loadData("https://google.com");
            // dataManager.setData("12 2 3 4 51");

            dataManager.processData();
            dataManager.saveData("./file.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
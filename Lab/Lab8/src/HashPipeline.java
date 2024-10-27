public class HashPipeline {
    @DataProcessor(DataProcessor.Type.OTHER)
    public String hash(String input) {
        return String.valueOf(input.hashCode());
    }
}

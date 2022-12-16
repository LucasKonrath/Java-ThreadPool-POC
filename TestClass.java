public class TestClass implements Task<String> {
    @Override
    public String run() throws InterruptedException {
        return "Hello World";
    }
}

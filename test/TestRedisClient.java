import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.After;
import java.io.IOException;
import org.junit.Test;


public class TestRedisClient extends FakeSocket {

    private RedisClient client;

    public TestRedisClient() {
        super();
    }

    @Before public void setUp() throws IOException {
        super.setUp();
        client = new RedisClient("localhost", 6379);
        client.connect();
    }

    @After public void tearDown() throws IOException {
        client.close();
    }

    @FakeSocketFixture(expect = "SET key value\n", send = "+OK")
    @Test public void testSet() throws IOException {
        String result = client.set("key", "value");
    }

    @FakeSocketFixture(expect = "DEL key\n", send = ":1")
    @Test public void testDelete() throws IOException {
        String result = client.delete("key");
    }

}

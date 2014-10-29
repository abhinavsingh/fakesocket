import static org.junit.Assert.assertEquals;
import java.net.SocketImplFactory;
import java.net.SocketImpl;
import java.util.Map;


public class FakeSocketImplFactory implements SocketImplFactory {

    public static String testName;

    private final Map<String, String[]> fixtures;
    private final FakeSocketImpl.FakeSocketImplCallback cb = new FakeSocketImpl.FakeSocketImplCallback() {
        @Override public void onClose(String rcvd) {
            assertEquals(rcvd, fixtures.get(FakeSocketImplFactory.testName)[1]);
        }
    };

    FakeSocketImplFactory(Map<String, String[]> fixtures) {
        this.fixtures = fixtures;
    }

    @Override
    public SocketImpl createSocketImpl() {
        return new FakeSocketImpl(fixtures.get(testName)[0], cb);
    }

}

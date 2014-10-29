import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.JUnit4TestAdapter;


@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestRedisClient.class
})


public class TestSuite {
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(TestSuite.class);
    }
}

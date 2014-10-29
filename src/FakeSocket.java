import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import java.net.Socket;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import java.io.IOException;


public class FakeSocket {

    @Rule public TestName name = new TestName();
    private static boolean setUpIsDone = false;

    @Before public void setUp() throws IOException {
        FakeSocketImplFactory.testName = name.getMethodName();;

        if (setUpIsDone) {
            return;
        }

        // populate fixture
        Class c = this.getClass();
        final Map<String, String[]> fixtures = new HashMap<>();
        for (Method method : c.getDeclaredMethods()) {
            if (method.isAnnotationPresent(FakeSocketFixture.class)) {
                Annotation annotation = method.getAnnotation(FakeSocketFixture.class);
                FakeSocketFixture fixture = (FakeSocketFixture) annotation;

                fixtures.put(method.getName(), new String[]{
                    fixture.send(),
                    fixture.expect()
                });
            }
        }

        // set custom socket factory
        FakeSocketImplFactory fac = new FakeSocketImplFactory(fixtures);
        Socket.setSocketImplFactory(fac);

        // done
        setUpIsDone = true;
    }

}

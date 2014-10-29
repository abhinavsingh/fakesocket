import java.net.SocketImpl;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


class FakeSocketImpl extends SocketImpl {

    public interface FakeSocketImplCallback {
        public void onClose(String rcvd);
    }

    private final FakeSocketImplCallback cb;
    private final InputStream input;
    private final ByteArrayOutputStream output;
    private final Map<Integer, Object> options = new HashMap<Integer, Object>();

    public FakeSocketImpl(String send, FakeSocketImplCallback cb) {
        this.cb = cb;
        this.input = new ByteArrayInputStream(send.getBytes());
        this.output = new ByteArrayOutputStream();
    }

    @Override
    protected void create(boolean stream) throws IOException {
        // no-op
    }

    @Override
    protected void connect(String host, int port) throws IOException {
        // no-op
    }

    @Override
    protected void connect(InetAddress address, int port) throws IOException {
        // no-op
    }

    @Override
    protected void connect(SocketAddress address, int timeout) throws IOException {
        // no-op
    }

    @Override
    protected void bind(InetAddress host, int port) throws IOException {
        // no-op
    }

    @Override
    protected void listen(int backlog) throws IOException {
        // no-op
    }

    @Override
    protected void accept(SocketImpl s) throws IOException {
        // no-op
    }

    @Override
    protected InputStream getInputStream() throws IOException {
        return input;
    }

    @Override
    protected OutputStream getOutputStream() throws IOException {
        return output;
    }

    @Override
    protected int available() throws IOException {
        return getInputStream().available();
    }

    @Override
    protected void close() throws IOException {
        getInputStream().close();
        getOutputStream().close();
        cb.onClose(getOutputStream().toString());
    }

    @Override
    protected void sendUrgentData(int data) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setOption(int optID, Object value) throws SocketException {
        options.put(optID, value);
    }

    @Override
    public Object getOption(int optID) throws SocketException {
        return options.get(optID);
    }

}

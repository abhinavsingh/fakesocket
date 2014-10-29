import java.net.Socket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.IOException;


public class RedisClient {

    private final String host;
    private final int port;

    private Socket s;
    private DataOutputStream out;
    private BufferedReader in;

    public RedisClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {
        s = new Socket(InetAddress.getByName(host), port);
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new DataOutputStream(s.getOutputStream());
    }

    public String set(String key, String val) throws IOException {
        return exec("SET " + key + " " + val + "\n");
    }

    public String delete(String key) throws IOException {
        return exec("DEL " + key + "\n");
    }

    public void close() throws IOException {
        s.close();
    }

    private String exec(String cmd) throws IOException {
        // send command
        out.write(cmd.getBytes());
        out.flush();

        // read response
        return in.readLine();
    }

    public DataOutputStream getOut() {
        return out;
    }

    public static void main(String[] args) throws IOException {
        RedisClient client = new RedisClient("localhost", 6379);
        client.connect();

        String set = client.set("hello", "world");
        System.out.println(set);

        //String del = client.delete("hello");
        //System.out.println(del);
    }

}

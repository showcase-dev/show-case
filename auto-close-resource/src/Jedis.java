import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Jedis implements AutoCloseable {

	private Map<String, Object> data = new ConcurrentHashMap<String, Object>();

	private static final Jedis jedis = new Jedis();

	private static volatile boolean isClose;

	public static Jedis getInstance() {
		isClose = false;
		return jedis;
	}

	private Jedis() {
	}

	public Object get(String key) {
		return key == null ? null : data.get(key);
	}

	public void set(String key, Object value) {
		data.put(key, value);
	}

	public void clear() {
		data.clear();
	}

	@Override
	public void close() throws Exception {
		if (isClose) {
			return;
		}
		System.out.println("closed");
		isClose = true;
	}

}

public class TryTest {

	public static void main(String[] args) {
		try (Jedis jedis = Jedis.getInstance();Jedis test = Jedis.getInstance()) {
			System.out.println(jedis);
			System.out.println(test);
			jedis.set("test", "business");
			System.out.println("处理逻辑");
			System.out.println(jedis.get("test"));
			test.clear();
			System.out.println(jedis.get("test"));
		} catch (Exception e) {
			System.err.println("error msg:" + e.getMessage());
		}

	}

}

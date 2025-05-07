import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class test {
	public static void main(String[] args) {
		Map<String,String> farmlist = new HashMap<>();
		farmlist.put("aa",null);
		Iterator<String> st = farmlist.keySet().iterator();
		System.out.println(farmlist.get("aa"));
		
	}
}

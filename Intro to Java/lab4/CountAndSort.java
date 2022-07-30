import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CountAndSort {
	
	public static class CompareArrays implements Comparator<Object[]> {

		@Override
		public int compare(Object[] o1, Object[] o2) {
			
			Integer i1 = (Integer) o1[1];
			Integer i2 = (Integer) o2[1];
			int result = Integer.compare(i1, i2);
			if (result == 0) {
				//both rows have the same count
				String s1 = (String) o1[0];
				String s2 = (String) o2[0];
				result = Integer.compare(s1.length(), s2.length());
			}
			return result;
		}
		
	}

	public static Map<Object, Integer> count(Object[] a) {
		Map<Object, Integer> c = new LinkedHashMap<>();
		for (Object obj : a) {
			Integer count = c.putIfAbsent(obj, 1);
			if (count != null) {
				count = count + 1;
				c.put(obj, count);
			}
		}
		return c;
	}

	public static void main(String[] args) {
		// Yoda quote
		String[] a = { "fear", "leads", "to", "anger", 
				"anger", "leads", "to", "hatred", 
				"hatred", "leads", "to", "conflict", 
				"conflict", "leads", "to", "suffering" };
		
		Map<Object, Integer> c = count(a);
		System.out.println(c);
		
		Object[][] arr = new Object[c.size()][2];
		int row = 0;
		for (Object key : c.keySet()) {
			Integer value = c.get(key);
			arr[row][0] = key;
			arr[row][1] = value;
			row++;
		}
		System.out.println(Arrays.deepToString(arr));
		
		CompareArrays comp = new CompareArrays();
		Arrays.sort(arr, comp);
		System.out.println(Arrays.deepToString(arr));
		
		
	}
}

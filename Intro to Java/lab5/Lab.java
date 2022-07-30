
public class Lab {

	static MiniMap<Integer, String> stu = new ArrayMiniMap<>();
	static MiniMap<Integer, String> stuSorted = new SortedArrayMiniMap<>();
	
	static void add(int snum, String name) {
		stu.put(snum, name);
		stuSorted.put(snum, name);
	}
	
	static void contains(int snum) {
		boolean in = stu.containsKey(snum);
		boolean inSorted = stuSorted.containsKey(snum);
		System.out.println("ArrayMiniMap containsKey:" + in);
		System.out.println("SortedArrayMiniMap containsKey:" + inSorted);
		System.out.println();
	}
	
	public static void main(String[] args) {
		add(12345678, "Beach, Rocky");
		add(10000000, "Dababi, Carrie");
		add(20000000, "Howe, Annie");
		add(10000001, "O'Shea, Rick");
		
		// ADD YOUR STUDENT NUMBER AND NAME HERE
		int stuNumber = 20202760;
		String name = "Cowie, Jackson";
		
		contains(stuNumber);
		add(stuNumber, name);
		System.out.println("ArrayMiniMap\n" + stu);
		System.out.println("\nSortedArrayMiniMap:\n" + stuSorted);
		System.out.println();
		contains(stuNumber);		
	}

}

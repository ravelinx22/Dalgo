import java.util.ArrayList;

public class Problema2 {

	static double weights[] = {63.0, 37.0};
	
	public static double problema2(double max, int n, ArrayList<Integer> associates) {
		if(calculo(n, associates) > max && associates.size() != 1)
			max = calculo(n, associates);
		
		
		if(associates.size() == weights.length)
			return max;
		
		for(int i = 1; i < weights.length; i++) {
			if(!associates.contains(i))
			{
				ArrayList<Integer> associates2 = new ArrayList<>();
				associates2.addAll(associates);
				associates2.add(i);
				max = problema2(max, n, associates2);
			}
		}
		
		return max;
	}
	
	public static double calculo(int source, ArrayList<Integer> associates) {
		double arriba = (weights[source-1]*100);
		double abajo = 0.0;
		
		for(int i = 0; i < associates.size(); i++) {
			abajo += weights[associates.get(i)-1];
		}
		
		if(abajo < 50)
			return 0.0;
		
		return Math.round((arriba/abajo) * 100.0) / 100.0;
	}
	
	public static void printArrayList(ArrayList<Integer> associates) {
		for(int x : associates) {
			System.out.print(x +" ");
		}
		
		System.out.println();
	}
	
	public static void main(String args[]) {
		ArrayList<Integer> associates = new ArrayList<>();
		associates.add(2);
		double x = problema2(0.0, 2, associates);
		System.out.println(x);
	}
	
	
}

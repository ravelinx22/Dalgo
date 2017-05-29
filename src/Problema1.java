
import java.util.Arrays;
import java.util.Scanner;

public class Problema1 {
	
	public static void problema1(int p, int q, int k, int[] a) {
		printArray(a);
		
		int[] copy = Arrays.copyOf(a, a.length);
		
		for(int i = p; i < q; i++) {
			int len = q-p;
			int nuevaPos = i+(Math.floorMod(k, len));
			int div = (nuevaPos-p)/len;
			int finPos = nuevaPos - len*div;
			
			a[finPos] = copy[i];
		}
		
		printArray(a);
	}
	
	// Helpers
	
	public static int[] toArray(String[] ar) {
		int[] ans = new int[ar.length];
		
		for(int i = 0; i < ans.length; i++) {
			ans[i] = Integer.parseInt(ar[i]); 
		}
		
		return ans;
 	}
	
	public static void printArray(int[] ar) {
		for(int x : ar)
			System.out.print(x +" ");
		
		System.out.println();
	}

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int p = sc.nextInt();
		int q = sc.nextInt();
		int k = sc.nextInt();
		sc.nextLine();
		String ar = sc.nextLine();
		ar = ar.substring(1, ar.length()-1);
		String datos[] = ar.split(",");
		int[] a = toArray(datos);
		
		problema1(p, q, k, a);
	}
}


import java.util.Arrays;
import java.util.Scanner;

public class Problema1 {
	
	/**
	 * Realiza una k-rotacion
	 * <Pre>0<=p<q<=n<10^5, -10^5<k<10^5, Para i tal que 0<=i<n, a[i]=i+1, </Pre>
	 * @param p Posicion inicial del intervalo
	 * @param q Posicion final del intervalo
	 * @param k Numero de rotaciones que se realizan
	 * @param a Arreglo que se va a rotar
	 * <Post>Todos los elementos en el intervalo p...q-1 son movidos k veces</Post>
	 */
	public static void problema1(int p, int q, int k, int[] a) {
		int[] copy = Arrays.copyOf(a, a.length);
		
		for(int i = p; i < q; i++) {
			int len = q-p;
			int nuevaPos = i+(Math.floorMod(k, len));
			int div = (nuevaPos-p)/len;
			int finPos = nuevaPos - len*div;
			
			a[finPos] = copy[i];
		}
	}
	
	// Helpers
	
	/**
	 * <Pre>El arreglo existe. ar != null </Pre>
	 * Muestra en consola un arreglo de enteros
	 * @param ar Arreglo de enteros a mostrar en consola
	 * <Post>El arreglo se mostro en consola</Post>
	 */
	public static void printArray(int[] ar) {
		for(int x : ar)
			System.out.print(x +" ");
		
		System.out.println();
	}
	
	/**
	 * <Pre>n >= 0</Pre>
	 * Crea un arreglo de tamaño n
	 * @param length Tamaño que va a tener el arreglo
	 * @return Arreglo a de tamaño n
	 * <Post>Para i tal que 0<=i<n se cumple que a[i]=i+1</Post>
	 */
	public static int[] darArreglo(int n) {
		int[] ans = new int[n];
		
		for(int i = 1; i <= n; i++) {
			ans[i-1] = i;
		}
		
		return ans;
	}

	// Main
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int nr = sc.nextInt();
		sc.nextLine();
		int[] arreglo = darArreglo(n);
		
		for(int i = 0; i < nr; i++) {
			String datos = sc.nextLine();
			String[] pqk = datos.trim().split(" ");
			
			problema1(Integer.parseInt(pqk[0]), Integer.parseInt(pqk[1]), Integer.parseInt(pqk[2]), arreglo);
		}
		printArray(arreglo);
		
		sc.close();
	}
}

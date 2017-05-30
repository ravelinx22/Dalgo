import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Andrea López – 201531591
 * @author William Ravelo M - 201532093
 */
public class Problema3 {

	// Atributos

	private static ArrayList<Character> charsSinRepetir = new ArrayList<Character>();
	private static int possiblesValores[] = {0,1,2,3,4,5,6,7,8,9};
	private static HashMap<Character, Integer> permutacionActual = new HashMap<Character, Integer>();
	private static boolean haySolucion=false;
	private static ArrayList<ArrayList<Integer>> permutacionesPosibles = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<Character> charsComienzanPalabras = new ArrayList<>();

	// Metodos

	/**
	 * <pre>c != null && length(charsSinRepetir) <= 10</pre>
	 * Halla el numero que representa cada letra en las operaciones
	 * @param c String que contiene la operacion a descifrar
	 * <post></post>
	 */
	public static void hallarSolucion(String c)
	{
		Collections.sort(charsSinRepetir);
		hallarPermutacionesPosibles(possiblesValores, 0);

		for(int i=0;i<permutacionesPosibles.size() && !haySolucion;i++)
		{	
			boolean noVerificar = false;
			for (int j = 0; j < charsComienzanPalabras.size() && !noVerificar ; j++) {
				int x = charsSinRepetir.indexOf(charsComienzanPalabras.get(j));
				if(permutacionesPosibles.get(i).get(x) == 0) {
					noVerificar = true;
				}
			}
			
			if(noVerificar)
				continue;
			
			for(int j=0;j<charsSinRepetir.size() && !haySolucion;j++)
			{
				permutacionActual.put(charsSinRepetir.get(j),permutacionesPosibles.get(i).get(j));			
			}

			if(verificarEcuacion(c))
				haySolucion=true;
		}
	}

	// Helpers

	/**
	 * <pre>s != null</pre>
	 * Convierte un string a numero de acuerdo a los valores numericos asignados a cada uno actualmente.
	 * @param s String a convertir a numero
	 * @return Representacion numerica del string
	 * <post>ans pertenece a los enteros+</post>
	 */
	public static int toNumber(String s)
	{
		String temp="";
		for(int i=0;i<s.length();i++)
		{
			temp += permutacionActual.get(s.charAt(i));
		}
		
		int ans = Integer.parseInt(temp);
		
		return ans;
	}

	/**
	 * <pre>s != null</pre>
	 * Guarda todos los caracteres, una vez en el arreglo charsComienzanPalabras
	 * @param s String que contiene los caracteres
	 * <post>length(charsComienzanPalabras) <= length(s) && Para todo i donde 0<=i<length(s) : s.charAt(i) pertenece a charsComienzanPalabras</post>
	 */
	public static void guardarCaracteresSinRepetir(String s)
	{
		// Agregar caracteres que comienzan palabra
		String[] pos = s.split("\\-|\\+|\\="); 
		for(int i = 0; i < pos.length; i++) {
			char letraC = pos[i].trim().charAt(0);
			
			if(!charsComienzanPalabras.contains(letraC))
				charsComienzanPalabras.add(letraC);
		}
		
		// Agregar caracteres sin repetir
		for(int i=0;i<s.length();i++)
		{
			char x = s.charAt(i);
			if(x != '+' && x != '-' && x != ' ' && x != '=' && !charsSinRepetir.contains(x))
			{
				charsSinRepetir.add(s.charAt(i));
			}
		}
	}

	/**
	 * <pre>s != null</pre>
	 * Verifica si con la permutacion actual se cumple la ecuacion del alphametic
	 * @param s Alphametic a descubrir
	 * @return True si la permutacion actual cumple el alphametic, false de lo contrario
	 * <post>ans = true v false</post>
	 */
	public static boolean verificarEcuacion(String s) {
		String[] operaciones = s.split("=");
		ArrayList<String> palabras = new ArrayList<String>();

		// Primera parte
		String[] sumas = operaciones[0].split("\\+");
		int suma = 0;
		for(int i = 0; i < sumas.length; i++) {
			String[] restas = sumas[i].split("\\-");
			String posi = restas[0].trim();
			int resta = toNumber(posi);
			palabras.add(posi);

			for(int j = 1; j < restas.length; j++) {
				resta -= toNumber(restas[j].trim());
				palabras.add(restas[j].trim());
			}

			suma += resta;
		}

		// Segunda parte
		String[] sumas2 = operaciones[1].split("\\+");
		int suma2 = 0;
		for(int i = 0; i < sumas2.length; i++) {
			String[] restas = sumas2[i].split("\\-");
			int resta = toNumber(restas[0].trim());
			palabras.add(restas[0].trim());

			for(int j = 1; j < restas.length; j++) {
				resta -= toNumber(restas[j].trim());
				palabras.add(restas[j].trim());
			}

			suma2 += resta;
		}

		boolean cumpleCondicion = true;
		for(String pos : palabras) {
			if(pos.length() != String.valueOf(toNumber(pos)).length())
				cumpleCondicion = false;
		}

		boolean ans = (suma == suma2) && cumpleCondicion;
		return ans;
	}

	/**
	 * <pre>k < length(a)</pre>
	 * Halla todas las permutaciones posibles que pueden haber con el arreglo a y comenzando en k
	 * @param a Arreglo al que se quiere hallar las posibles permutaciones
	 * @param k Variable donde comienza la permutacion
	 * <post>length(permutacionesPosibles) >= 0</post>
	 */
	public static void hallarPermutacionesPosibles(int []a,int k )
	{
		if(k==a.length)
		{
			ArrayList<Integer> perm = new ArrayList<Integer>();
			for(int i=0;i<a.length;i++)
			{
				perm.add(a[i]);
			}
			permutacionesPosibles.add(perm);
		}	
		else
		{	
			for (int i = k; i < a.length; i++)
			{
				int temp=a[k];
				a[k]=a[i];
				a[i]=temp;
				hallarPermutacionesPosibles(a,k+1);
				temp=a[k];
				a[k]=a[i];
				a[i]=temp;
			}
		}
	}

	/**
	 * <pre>haySolucion = true v false</pre>
	 * Muestra en consola * si no hay respuesta o un String s tal que para todo i donde 0<=i<length(s) : s.charAt(i) pertenece a la permutacionActual
	 * <post>Se momstro en consola el resultado del alphametic</post> 
	 */
	public static void printAns() {		
		char[] ans = new char[10];

		for(int i = 0; i < ans.length; i++) {
			ans[i] = '*';
		}

		if(haySolucion) {
			for(char x: permutacionActual.keySet()) {
				ans[permutacionActual.get(x)] = x;
			}
		}
		
		System.out.println(String.valueOf(ans));
	}

	// Main

	public static void main(String[] args) throws InterruptedException
	{	
		Scanner sc = new Scanner(System.in);
		String alphametic = sc.nextLine();
		guardarCaracteresSinRepetir(alphametic);

		hallarSolucion(alphametic);
		printAns();
		
		sc.close();
	}
}



import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Problema3 {

	// Atributos

	private static ArrayList<Character> charsSinRepetir = new ArrayList<Character>();
	private static int possiblesValores[] = {0,1,2,3,4,5,6,7,8,9};

	static HashMap<Character, Integer> permutacionActual = new HashMap<Character, Integer>();
	static boolean haySolucion=false;
	static ArrayList<ArrayList<Integer>> permutacionesPosibles = new ArrayList<ArrayList<Integer>>();
	
	private static ArrayList<Character> charsComienzanPalabras = new ArrayList<>();

	// Important

	public static void calculate(String alphametic)
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

			if(verificarEcuacion(alphametic))
				haySolucion=true;
		}
	}

	// Helpers

	public static int toNumber(String s)
	{
		String temp="";
		for(int i=0;i<s.length();i++)
		{
			temp += permutacionActual.get(s.charAt(i));
		}
		return Integer.parseInt(temp);
	}

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

		// MAS
		boolean posi = true;
		for(String pos : palabras) {
			if(pos.length() != String.valueOf(toNumber(pos)).length())
				posi = false;
		}

		return (suma == suma2) && posi;
	}

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

		long start = System.currentTimeMillis();


		calculate(alphametic);


		long end = System.currentTimeMillis();
		double time = (end-start)/1000.0;
		System.out.println("Time required for execution: "+time+" seconds");



		printAns();
	}
}



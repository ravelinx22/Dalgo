import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problema3 {

		// Declaration

		static Scanner sc = new Scanner(System.in);
		static ArrayList<Character> uniquechar = new ArrayList<Character>();
		static int nos[] = {0,1,2,3,4,5,6,7,8,9};
		static HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		static int no1,no2,no3,count=0;
		static boolean solutionfound=false;
		static ArrayList<ArrayList<Integer>> permuts = new ArrayList<ArrayList<Integer>>();
		static String s1;

		// Important

		public static void main(String[] args) throws InterruptedException
		{	
			getInput();
			System.out.println("Calculating. Please wait...");
			long start = System.currentTimeMillis();
			calculate();
			long end = System.currentTimeMillis();
			double time = (end-start)/1000.0;
			System.out.println("Time required for execution: "+time+" seconds");


			if(solutionfound)
				printAns(s1);
		}


		public static void calculate()
		{
			Collections.sort(uniquechar);
			permute(nos, 0);

			for(int i=0;i<permuts.size() && !solutionfound;i++)
			{
				for(int j=0;j<uniquechar.size() && !solutionfound;j++)
				{
					hm.put(uniquechar.get(j),permuts.get(i).get(j));			
				}



				if(satifies(s1))
				{
					solutionfound=true;
					count++;
				}	
			}

			if(!solutionfound)
				System.out.println("No solution found!");

		}

		public static void permute(int []a,int k )
		{
			if(k==a.length)
			{
				ArrayList<Integer> perm = new ArrayList<Integer>();
				for(int i=0;i<a.length;i++)
				{
					perm.add(a[i]);
				}
				permuts.add(perm);
			}	
			else
			{	
				for (int i = k; i < a.length; i++)
				{
					int temp=a[k];
					a[k]=a[i];
					a[i]=temp;
					permute(a,k+1);
					temp=a[k];
					a[k]=a[i];
					a[i]=temp;
				}
			}
		}

		public static void iterateHashMap()
		{	
			for (Map.Entry<Character, Integer> entry : hm.entrySet()) 
			{
				char key = entry.getKey();
				int value = entry.getValue();

				System.out.println("Key:"+key+" Value:"+value);
			}
		}




		// Helpers

		public static int getNumber(String s)
		{

			String temp="";
			for(int i=0;i<s.length();i++)
			{
				temp=temp+hm.get(s.charAt(i));
			}
			return Integer.parseInt(temp);
		}

		public static int getLengthOfInt(int n)
		{
			return String.valueOf(n).length();
		}

		public static boolean found(char c)
		{
			boolean flag=false;
			for(int i=0;i<uniquechar.size();i++)
			{
				if(uniquechar.get(i)==c)
					flag=true;
			}
			if(flag)
				return true;
			else
				return false;	
		}

		public static void addToArrayList(String s)
		{
			for(int i=0;i<s.length();i++)
			{
				char x = s.charAt(i);
				if(x != '+' && x != '-' && x != ' ' && x != '=' && !found(s.charAt(i)))
				{
					uniquechar.add(s.charAt(i));
				}
			}
		}


		public static boolean satifies(String s) {
			String[] operaciones = s.split("=");
			ArrayList<String> palabras = new ArrayList<String>();

			// Primera parte
			String[] sumas = operaciones[0].split("\\+");
			int suma = 0;
			for(int i = 0; i < sumas.length; i++) {
				String[] restas = sumas[i].split("\\-");
				String posi = restas[0].trim();
				int resta = getNumber(posi);
				palabras.add(posi);

				for(int j = 1; j < restas.length; j++) {
					resta -= getNumber(restas[j].trim());
					palabras.add(restas[j].trim());
				}

				suma += resta;
			}

			// Segunda parte
			String[] sumas2 = operaciones[1].split("\\+");
			int suma2 = 0;
			for(int i = 0; i < sumas2.length; i++) {
				String[] restas = sumas2[i].split("\\-");
				int resta = getNumber(restas[0].trim());
				palabras.add(restas[0].trim());

				for(int j = 1; j < restas.length; j++) {
					resta -= getNumber(restas[j].trim());
					palabras.add(restas[j].trim());
				}

				suma2 += resta;
			}

			// MAS
			boolean posi = true;
			for(String pos : palabras) {
				if(pos.length() != getLengthOfInt(getNumber(pos)))
					posi = false;
			}

			return (suma == suma2) && posi;
		}

		public static void printAns(String s) {
			String[] x = s.split("\\+|\\-|\\=");
			for(int i = 0; i < x.length; i++) {
				System.out.println(x[i].trim() +" " +getNumber(x[i].trim()));
			}
		}


		public static void getInput()
		{
			System.out.println("Enter string:");
			s1 = sc.nextLine();

			addToArrayList(s1);
		}
}



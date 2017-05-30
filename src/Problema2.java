
import java.util.Arrays;
import java.util.Scanner;

public class Problema2 {

	/**
	 * <pre>0<n<=size(p) && Para i donde 0<=i<size(p) : p[i] >= 0.0</pre>
	 * Da la ganancia maxima que puede tener un accionista n al asociarse con un grupo.
	 * @param n Accionista que se va a asociar
	 * @param p Porcentajes que tiene de la empresa cada accionista.
	 * <post>Se muestra en consola la maxima ganancia que puede tener el accionista n al asociarse con un grupo</post>
	 */
	public static void problema2(int n, double[] p) {

		double percentagesSource = p[n-1];
		p[n-1] = 0.0;
		Arrays.sort(p);
		
		double conSoloUno = minimoAsociandoseConUno(percentagesSource, p);

		double suma = percentagesSource;
		int i = 0;
		while(suma <= 50 && i < p.length) {
			double valor = p[i];
			suma += valor;

			i++;
		}

		double respuesta = Math.round(((percentagesSource*100)/suma) * 100.0) / 100.0;
		
		respuesta = (conSoloUno > respuesta) ? conSoloUno : respuesta;
		
		System.out.println(respuesta);
	}
	
	/**
	 * <pre>0<n<=size(p) && Para i donde 0<=i<size(p) : p[i] >= 0.0</pre>
	 * Verifica si asociandose con un solo socio se puede cumplir con mas del 50%
	 * @param percentagesSource Porcentaje del accionista que se quiere asociar con los demas
	 * @param p Arreglo que contiene los porcentajes de los accionistas
	 * @return Ganancia maxima que puede tener al asociarse con un solo accionista.
	 * <post>Se retorno la ganancia maxima que puede tener al asociarse con un solo accionista.</post>
	 */
	public static double minimoAsociandoseConUno(double percentagesSource, double[] p) {
		for(int i = 0; i < p.length; i++) {
			double suma = p[i]+percentagesSource;
			if(suma > 50) 
				return Math.round(((percentagesSource*100)/suma) * 100.0) / 100.0;
		}
		
		return 0.0;
	}
	
	// Helpers

	/**
	 * <pre>percentageString != null && percentageString != "" && Para todo i donde 0<=i<length(percentageString) : percentageString.charAt(i) pertenece a los Reales </pre>
	 * Crea un arreglo de double's a partir de una cade de caracteres que contiene los numeros
	 * @param percentageString Cadena de caracteres que tiene los numeros que se quieres convertir a double
	 * @return Un arreglo de doubles que contiene los numeros de la cadena percentageString
	 * <post>Para todo i donde 0<=i<length(ans) : ans[i] pertenece a percentageString</post>
	 */
	public static double[] toArray(String percentageString) {
		String[] percentagesString = percentageString.trim().split(" ");
		double[] ans = new double[percentagesString.length];

		for(int i = 0; i < ans.length; i++) {
			ans[i] = Double.parseDouble(percentagesString[i]);
		}

		return ans;
	}

	// Main

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		String percentString = sc.nextLine();
		double[] p = toArray(percentString);
		problema2(n, p);

		sc.close();
	}


}

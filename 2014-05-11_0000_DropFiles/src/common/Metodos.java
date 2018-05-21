package common;

import java.util.ArrayList;
import java.util.Iterator;

public class Metodos {

	private static ArrayList<String> modifica(ArrayList<String> arreglo) {
		arreglo.add("seis");
		int u = 1;

		Iterator it = arreglo.iterator();

		while (it.hasNext()) {
			String cad = (String) it.next();
			if (cad.equals("uno"))
				it.remove();
		}

		return arreglo;
	}

	
}  // End of class
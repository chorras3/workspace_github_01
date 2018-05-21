package common;

import java.io.*;

public class DirListado {
	public static void main(String args[]) {
		String dirname;

		if (args.length > 0) {
			dirname = args[0];
		} else{
			////dirname = ".";
			dirname = "M:\\User\\WorkspaceEclipseJunoUsb\\Misc\\201212150108_AutomatedActions";
		}

		File f1 = new File(dirname);
		listarDirectorio(f1, "CÉ");
	}

	public static void listarDirectorio(File f, String c) {
		String dirname;

		int Totalficheros = 0;
		int Totaldirectorios = 0;
		int Totalkbytes = 0;

		if (f.isDirectory()) {
			System.out.println(c + "Directorio de " + f);
			File s[] = f.listFiles();

			for (int i = 0; i < s.length; i++) {
				if (s[i].isDirectory()) {
					Totaldirectorios++;
					System.out.println(c + s[i] + " es un directorio");
					listarDirectorio(s[i], c + " ");
				} else if (s[i].isFile()) {
					Totalficheros++;
					Totalkbytes += s[i].length();
					System.out.println(c + s[i] + " es un fichero");
				}
			}
			System.out.println(c + "hay " + Totalficheros + " ficheros");
			System.out.println(c + "ocupan " + Totalkbytes + " kbytes");
			System.out.println(c + "hay " + Totaldirectorios
					+ " subdirectorios");
		}
	}

}

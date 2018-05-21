package common;
import java.io.*;
import java.util.*;

public class RecursivelyListFilesInJava {
    public static Collection<File> ListFilesRecursive(String pathname) {
        return ListFilesRecursive(new File(pathname));
    }

    public static Collection<File> ListFilesRecursive(File file) {
        ArrayList<File> fileList = new ArrayList<File>();

        return ListFilesRecursive(file, fileList);
    }

    public static Collection<File> ListFilesRecursive(File file, Collection<File> dst) {
        if (!file.isDirectory()) {
            dst.add(file);
        } else {
            for (File child : file.listFiles()) {
                if (child.isDirectory()) {
                    dst.addAll(ListFilesRecursive(child));
                } else {
                    dst.add(child);
                }
            }
        }

        return dst;
    }

    public static void main(String[] args) {
//        ////for (File f : ListFilesRecursive(".")) {
//    	for (File f : ListFilesRecursive("M:\\User\\WorkspaceEclipseJunoUsb\\Misc\\201212150108_AutomatedActions")) {
//            System.out.println(f.getPath());
//        }
    	
    	ArrayList lisFiles = (ArrayList) ListFilesRecursive("M:\\User\\WorkspaceEclipseJunoUsb\\Misc\\201212150108_AutomatedActions");
    	for (Iterator iteLisFilesRecursive = lisFiles.iterator(); iteLisFilesRecursive.hasNext();) {
			File filCurrent = (File) iteLisFilesRecursive.next();
			System.out.println("filCurrent="+filCurrent);
		}
    	
//    	Object o = new ListFilesRecursive("M:\\User\\WorkspaceEclipseJunoUsb\\Misc\\201212150108_AutomatedActions");
//    	System.out.println();
    }
}
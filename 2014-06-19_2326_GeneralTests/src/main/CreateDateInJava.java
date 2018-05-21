package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * -References: "", http://stackoverflow.com/questions/32586/how-to-discover-a-files-creation-time-with-java
 */
public class CreateDateInJava {
    public static void main(String args[]) {
//    public static void main() {
        try {

            // get runtime environment and execute child process
            Runtime systemShell = Runtime.getRuntime();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter filename: ");
//            String fname = (String) br1.readLine();
            String fname = "H:\\User_Temp\\_Test_fileDrop\\file_2016-07-24_0005.txt";
            Process output = systemShell.exec("cmd /c dir \"" + fname + "\" /tc");

            System.out.println(output);
            // open reader to get output from process
            BufferedReader br = new BufferedReader(new InputStreamReader(output.getInputStream()));

            String out = "";
            String line = null;

            int step = 1;
            while ((line = br.readLine()) != null) {
                if (step == 6) {
                    out = line;
                }
                step++;
            }

            // display process output
            try {
                out = out.replaceAll(" ", "");
                System.out.println("CreationDate: " + out.substring(0, 10));
                System.out.println("CreationTime: " + out.substring(10, 16) + "m");
            } catch (StringIndexOutOfBoundsException se) {
                System.out.println("File not found");
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
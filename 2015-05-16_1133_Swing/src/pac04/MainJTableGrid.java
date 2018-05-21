package src.pac04;
import javax.swing.JTable;

public class MainJTableGrid {
  public static void main(String[] argv) throws Exception {

    JTable table = new JTable();

    table.setShowGrid(false);
    table.setShowHorizontalLines(true);
  }
}

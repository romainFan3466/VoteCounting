package observer;


/**
 * @author Romain FANARA
 * @ID C00198437
 *
 *Observer interface
 *
 *
 *
 *A class that implements Observer get informations and its methods are called by it.
 * 
 * 
 */
public interface Observer {
  public void update(Object[][] data);
  public void displayInformations(String str);
}
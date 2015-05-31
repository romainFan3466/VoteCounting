package observer;


/**
 * @author Romain FANARA
 * @ID C00198437
 *
 *Observable interface
 *
 * A class that implements Observable send informations to 
 * an Observer implementing class.
 * 
 * Observable manipulates and call methods from Observer implementing classes.
 * 
 */
public interface Observable {
		
  public void addObserver(Observer obs);
  public void removeObserver();
  public void notifyObserver(Object[][] data);
  public void notifyObserver(String informations);
}
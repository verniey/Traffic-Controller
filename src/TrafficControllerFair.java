import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TrafficControllerFair implements TrafficController {
  private final TrafficRegistrar registrar;
  private final Lock lock = new ReentrantLock();
  private final Condition bridgeAvailable = lock.newCondition();
  private boolean bridgeOccupied = false;


  public TrafficControllerFair(TrafficRegistrar registrar) {
    this.registrar = registrar;
  }

  @Override
  public void enterRight(Vehicle v) {
    lock.lock();
    try {
      while (bridgeOccupied) {
        bridgeAvailable.await();
      }
      bridgeOccupied = true;
      registrar.registerRight(v);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void enterLeft(Vehicle v) {
    lock.lock();
    try {
      while (bridgeOccupied) {
        bridgeAvailable.await();
      }
      bridgeOccupied = true;
      registrar.registerLeft(v);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void leaveLeft(Vehicle v) {
    lock.lock();
    try {
      bridgeOccupied = false;
      registrar.registerLeft(v);
      bridgeAvailable.signalAll();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void leaveRight(Vehicle v) {
    lock.lock();
    try {
      bridgeOccupied = false;
      registrar.registerRight(v);
      bridgeAvailable.signalAll();
    } finally {
      lock.unlock();
    }
  }
}

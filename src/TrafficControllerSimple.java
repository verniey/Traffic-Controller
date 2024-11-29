public class TrafficControllerSimple implements TrafficController {
	private TrafficRegistrar registrar;
	private boolean bridgeOccupied = false;

	public TrafficControllerSimple(TrafficRegistrar r) {
		this.registrar = r;
	}

	@Override
	public synchronized void enterRight(Vehicle v) {
		while (bridgeOccupied) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		bridgeOccupied = true;
		registrar.registerRight(v);       
	}

	@Override
	public synchronized void leaveLeft(Vehicle v) {
		bridgeOccupied = false;
		registrar.deregisterLeft(v);
		notifyAll();
	}

	@Override
	public synchronized void enterLeft(Vehicle v) {
		while (bridgeOccupied) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		bridgeOccupied = true;
		registrar.registerLeft(v);
	}

	@Override
	public synchronized void leaveRight(Vehicle v) {
		bridgeOccupied = false;
		registrar.deregisterRight(v);
		notifyAll();
	}
}

# Traffic Controller Simulation

## Description
This project implements a traffic controller system to regulate vehicle movement on a single-lane bridge. The simulation ensures thread-safe access to the bridge using two implementations:
- `TrafficControllerSimple`: Synchronization using `synchronized`, `wait()`, and `notifyAll()`.
- `TrafficControllerFair`: Synchronization using `ReentrantLock` and `Condition` for fair access.

The simulation includes a graphical user interface (GUI) where red and blue cars represent vehicles entering the bridge from opposite directions.

## Features
- Thread-safe bridge access for vehicles.
- Two implementations:
    - Simple controller with no fairness.
    - Fair controller ensuring vehicles cross in the order they arrive.
- Integration with a GUI for simulation visualization.

## Project Structure
- **TrafficControllerSimple**: Implements bridge access with `synchronized` and `wait()/notifyAll()`.
- **TrafficControllerFair**: Implements fair bridge access with `ReentrantLock` and `Condition`.
- **TrafficRegistrarEmpty**: Placeholder implementation for logging vehicle actions.
- **GUI**: Includes `BridgeGUI`, `CarWorld`, and `Car` classes for the simulation.

## Prerequisites
- Java 1.8
- Swing GUI dependencies (included in Java Standard Edition)



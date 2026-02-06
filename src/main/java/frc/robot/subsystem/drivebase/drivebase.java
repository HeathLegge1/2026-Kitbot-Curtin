package frc.robot.subsystem.drivebase;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.drivebase.drivebaseIO.drivebaseIOinputs;

public class drivebase extends SubsystemBase {
  private final drivebaseIO io;
  private final drivebaseIOinputs inputs = new drivebaseIOinputs();

  public drivebase(drivebaseIO expected_io) {
    this.io = expected_io;
  }
  public Command forward(double velocity) {
    return run(() -> io.forward(velocity));
  }
  public Command backward(double velocity) {
    return run(() -> io.backward(velocity));
  }
  public Command turn(double velocity, String direction) {
    return run(() -> io.turn(velocity,direction));
  }
  public Command stop() {
    return run(() -> io.stop());
  }
  public Command forwardincm(double velocity, int centermeters) {
    return run(() -> io.forwardincm(velocity, centermeters));
  }
  public Command turndegress(double velocity, int angle, String direction){
    return run(() -> io.turndegress(velocity, angle, direction));
  }
  public Command backwardincm(double velocity, int centermeters) {
    return run(() -> io.backwardincm(velocity, centermeters));
  }
  public Command forwardininches(double velocity, int centermeters) {
    return run(() -> io.forwardininches(velocity, centermeters));
  }
  public Command backwardininches(double velocity, int centermeters) {
    return run(() -> io.backwardininches(velocity, centermeters));
  }

}

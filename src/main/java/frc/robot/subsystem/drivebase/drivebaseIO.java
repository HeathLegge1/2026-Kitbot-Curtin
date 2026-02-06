package frc.robot.subsystem.drivebase;

import org.littletonrobotics.junction.AutoLog;

public interface drivebaseIO {
  @AutoLog
  public static class drivebaseIOinputs {
    public double AppliedVoltage;
    public double CurrentAmps;
    public double positionRotations;
  }

  public default void updateInputs(drivebaseIOinputs inputs) {}

  public default void setVoltage(double voltage) {}
  public default void forward(double velocity){}
  public default void backward(double velocity){}
  public default void turn(double velocity, String direction){}
  public default void stop(){}
  public default void forwardincm(double velocity, int centermeters){}
  public default void backwardincm(double velocity, int centermeters){}
  public default void turndegress(double velocity, int angle, String direction){}

}

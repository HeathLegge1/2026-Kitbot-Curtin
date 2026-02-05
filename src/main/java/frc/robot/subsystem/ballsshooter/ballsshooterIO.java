package frc.robot.subsystem.ballsshooter;

import org.littletonrobotics.junction.AutoLog;

public interface ballsshooterIO {
  @AutoLog
  public static class ballsshooterIOinputs {
    public double AppliedVoltage;
    public double CurrentAmps;
    public double positionRotations;
  }

  public default void updateInputs(ballsshooterIOinputs inputs) {}

  public default void setVoltage(double voltage) {}
  public default void startshooting(double velocity){}
  public default void stopshooting(){}


}

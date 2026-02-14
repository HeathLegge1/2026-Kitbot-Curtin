package frc.robot.subsystem.ballsasorber;

import org.littletonrobotics.junction.AutoLog;

public interface ballsasorberIO {
  @AutoLog
  public static class ballsasorberIOinputs {
    public double AppliedVoltage;
    public double CurrentAmps;
    public double positionRotations;
  }

  public default void updateInputs(ballsasorberIOinputs inputs) {}

  public default void setVoltage(double voltage) {}

  public default void startsucking(double velocity) {}

  public default void stopsucking() {}
}

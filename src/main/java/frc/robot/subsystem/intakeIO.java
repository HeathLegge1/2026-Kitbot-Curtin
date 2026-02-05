package frc.robot.subsystem;

import org.littletonrobotics.junction.AutoLog;

interface intakeIO {
  @AutoLog
  public class InnerintakeIO {
    public double appiledvoltage;
    public double currentamps;
  }

  public default void updateInputs() {}

  public default void suck() {}

  public default void rawintake() {}
}

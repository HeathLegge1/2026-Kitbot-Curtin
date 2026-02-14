package frc.robot.subsystem.ballsshooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.ballsshooter.ballsshooterIO.ballsshooterIOinputs;

public class ballsshooter extends SubsystemBase {
  private final ballsshooterIO io;
  private final ballsshooterIOinputs inputs = new ballsshooterIOinputs();

  public ballsshooter(ballsshooterIO expected_io) {
    this.io = expected_io;
  }

  public Command startshooting(double velocity) {
    return run(() -> io.startshooting(velocity));
  }

  public Command stopshooting() {
    return run(() -> io.stopshooting());
  }
}

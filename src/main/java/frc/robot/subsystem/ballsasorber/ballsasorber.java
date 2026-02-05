package frc.robot.subsystem.ballsasorber;

import frc.robot.subsystem.ballsasorber.ballsasorberIO.ballsasorberIOinputs;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ballsasorber extends SubsystemBase {
  private final ballsasorberIO io;
  private final ballsasorberIOinputs inputs = new ballsasorberIOinputs();

  public ballsasorber(ballsasorberIO expected_io) {
    this.io = expected_io;
  }
  public Command startsucking(double velocity) {
    return run(() -> io.startsucking(velocity));
  }
  public Command stopsucking() {
    return run(() -> io.stopsucking());
  }

}

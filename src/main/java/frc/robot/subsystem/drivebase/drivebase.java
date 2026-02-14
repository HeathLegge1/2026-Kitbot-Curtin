package frc.robot.subsystem.drivebase;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.drivebase.drivebaseIO.drivebaseIOinputs;

public class drivebase extends SubsystemBase {
  private final drivebaseIO io;
  private final drivebaseIOinputs inputs = new drivebaseIOinputs();
  private final PIDController controller = new PIDController(1, 0, 0);

  public drivebase(drivebaseIO expected_io) {
    this.io = expected_io;
  }
  public Command forward(double voltage) {
    return run(() -> {
      io.setVoltage(voltage);
    
    });
  }
  public Command backward(double voltage) {
        return run(() -> {
      io.setVoltage(-voltage);
    
    });
  }
  public Command getgyroistrue(int angle) {
    return run(() -> io.getgyroistrue(angle));
  }
  public Command turn(double velocity, String direction) {
    return run(() -> {
      if (direction == "left"){
        io.set2Voltage(velocity, -velocity);

      }
    });
  }
  public Command stop() {
    return run(() -> io.stop());
  }
  public Command forwardincm(double velocity, int centermeters) {
       return run(() -> {
      double rotations = io.getrotations() / 7.62;
      double voltage = controller.calculate(rotations, centermeters);
      io.setVoltage(voltage);
    });
  }
  public Command turndegress(double velocity, int angle, String direction){
    return run(() -> {
      if (direction == "left"){
        io.set2Voltage(-velocity, velocity);

      }else if (direction == "right"){
        io.set2Voltage(velocity, -velocity);
      }
    }).until(io.getgyroistrue(angle)).andThen(run(()->io.setVoltage(0)));
  };
  public Command backwardincm(double velocity, int centermeters) {
    return run(() -> {
      double rotations = io.getrotations() / 7.62;
      double voltage = controller.calculate(rotations, centermeters);
      io.setVoltage(-voltage);
    });
  }
  public Command forwardininches(double velocity, int inches) {
    return run(() -> {
      double rotations = io.getrotations() / 3;
      double voltage = controller.calculate(rotations, inches);
      io.setVoltage(voltage);
    });
  }
  public Command backwardininches(double velocity, int inches) {
    return run(() -> {
      double rotations = io.getrotations() / 3;
      double voltage = controller.calculate(rotations, inches);
      io.setVoltage(-voltage);
    });
  }

}

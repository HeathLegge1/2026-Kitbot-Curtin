package frc.robot.subsystem.drivebase;

import java.util.function.BooleanSupplier;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI.Port;

public interface drivebaseIO {
  @AutoLog
  public static class drivebaseIOinputs {
    public double AppliedVoltage;
    public double CurrentAmps;
    public double positionRotations;
  }

  public default void updateInputs(drivebaseIOinputs inputs) {}
  public ADXRS450_Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);

  public default void setVoltage(double voltage) {}
  public default void set2Voltage(double voltageleft, double voltageright){}
  public default void forward(double velocity){}
  public default void backward(double velocity){}
  public default void turn(double velocity, String direction){}
  public default void stop(){}
  public default void forwardincm(double velocity, int centermeters){}
  public default void backwardincm(double velocity, int centermeters){}
  public default void forwardininches(double velocity, int centermeters){}
  public default void backwardininches(double velocity, int centermeters){}
  public default void turndegress(double velocity, int angle, String direction){}
  public default BooleanSupplier getgyroistrue(int anglethatstrue){

      BooleanSupplier condition = () -> false;
      return condition;

  }
  public default double getrotations(){
    return 0;
  };

}

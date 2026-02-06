// Copyright (c) 2021-2026 Littleton Robotics
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by a BSD
// license that can be found in the LICENSE file
// at the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystem.ballsasorber.ballsasorber;
import frc.robot.subsystem.ballsasorber.ballsasorberIO;
import frc.robot.subsystem.ballsasorber.ballsasorberIODev;
import frc.robot.subsystem.ballsshooter.ballsshooter;
import frc.robot.subsystem.ballsshooter.ballsshooterIO;
import frc.robot.subsystem.ballsshooter.ballsshooterIODev;
import frc.robot.subsystem.drivebase.drivebase;
import frc.robot.subsystem.drivebase.drivebaseIODev;
import edu.wpi.first.wpilibj.SPI.Port;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends LoggedRobot {
  private XboxController xboxController;
  private ballsasorberIODev ballsasorberIODev;
  private ballsshooterIODev ballsshooterIODev;
  private drivebaseIODev drivebaseIODev;
  boolean asorbing = false;
  boolean moving = false;
  boolean throwing = false;
  Port gyroport = Port.kOnboardCS0;
  
  public Robot() {
    // Record metadata
    this.xboxController = new XboxController(0);
    this.ballsasorberIODev = new ballsasorberIODev(1, MotorType.kBrushless);
    this.ballsshooterIODev = new ballsshooterIODev(2, MotorType.kBrushless, 1, MotorType.kBrushless);
    this.drivebaseIODev = new drivebaseIODev(3, MotorType.kBrushless, 4, MotorType.kBrushless, 5, MotorType.kBrushless, 6, MotorType.kBrushless, 12, gyroport);
    Logger.recordMetadata("ProjectName", BuildConstants.MAVEN_NAME);
    Logger.recordMetadata("BuildDate", BuildConstants.BUILD_DATE);
    Logger.recordMetadata("GitSHA", BuildConstants.GIT_SHA);
    Logger.recordMetadata("GitDate", BuildConstants.GIT_DATE);
    Logger.recordMetadata("GitBranch", BuildConstants.GIT_BRANCH);
        Logger.recordMetadata(
            "GitDirty",
            switch (BuildConstants.DIRTY) {
              case 0 -> "All changes committed";
              case 1 -> "Uncommitted changes";
              default -> "Unknown";
            });
    
        // Set up data receivers & replay source
        switch (Constants.currentMode) {
          case REAL:
                // Running on a real robot, log to a USB stick ("/U/logs")
                Logger.addDataReceiver(new WPILOGWriter());
                Logger.addDataReceiver(new NT4Publisher());
                break;

              case SIM:
                // Running a physics simulator, log to NT
                Logger.addDataReceiver(new NT4Publisher());
                break;

              case REPLAY:
                // Replaying a log, set up replay source
                setUseTiming(false); // Run as fast as possible
                String logPath = LogFileUtil.findReplayLog();
                Logger.setReplaySource(new WPILOGReader(logPath));
                Logger.addDataReceiver(new WPILOGWriter(
                    LogFileUtil.addPathSuffix(logPath, "_sim")));
                break;
        }
    
        // Start AdvantageKit logger
        Logger.start();
  }

  /** This function is called periodically during all modes. */
  @Override
  public void robotPeriodic() {}

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /**
   * This autonomous runs the autonomous command selected by your {@link
   * RobotContainer} class.
   */
  @Override
  public void autonomousInit() {}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    if (xboxController.getBButtonPressed() == true && throwing == false) {
        ballsasorberIODev.startsucking(1);
        asorbing = true;
    }
    if (xboxController.getBButtonReleased() == true && asorbing == true){
      asorbing = false;
      ballsasorberIODev.stopsucking();
    }
    if (xboxController.getAButtonPressed() == true && asorbing == false) {
        ballsshooterIODev.startshooting(1);
        throwing = true;
    }

    if (xboxController.getAButtonReleased() == true && throwing == true){
      throwing = false;
      ballsshooterIODev.stopshooting();
    }


    if (xboxController.getLeftX() == 1){
      if (moving == false) {
        drivebaseIODev.turn(1,"right");
        moving = true;
      }else{
        drivebaseIODev.stop();
        drivebaseIODev.turn(1,"right");
      }
    }
    if (xboxController.getLeftX() == -1){
      if (moving == false) {
        drivebaseIODev.turn(1,"left");
        moving = true;
      }else{
        drivebaseIODev.stop();
        drivebaseIODev.turn(1,"left");
      }
    }
    if (xboxController.getLeftY() == -1 ){
      if (moving == false) {
        drivebaseIODev.forward(1);
        moving = true;
      }else{
        drivebaseIODev.stop();
        drivebaseIODev.forward(1);
      }
    }
    if (xboxController.getLeftY() == 1 ){
      if (moving == false) {
        drivebaseIODev.backward(1);
        moving = true;
      }else{
        drivebaseIODev.stop();
        drivebaseIODev.backward(1);
      }
    }
    if(xboxController.getLeftY() < -0.2 && xboxController.getLeftY() > 0.2 && xboxController.getLeftX() < -0.2 && xboxController.getLeftX() > 0.2){
      drivebaseIODev.stop();
      moving = false;
    }
  
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}

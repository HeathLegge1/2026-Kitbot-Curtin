package frc.robot.subsystem.drivebase;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI.Port;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;


public class drivebaseIODev implements drivebaseIO {
    private final SparkMax topleft;
    private final SparkMax topright;
    private final SparkMax bottomleft;
    private final SparkMax bottomright;
    private final ADXRS450_Gyro gyro;
    private int WheelDiameterinCM;

    public drivebaseIODev(int topleftID, MotorType topleftmotortype,int toprightID, MotorType toprightmotortype,   int bottomleftID, MotorType bottomleftmotortype,int bottomrightID, MotorType bottomrightmotortype, int WheelDiameterinCM, Port gyroport) {
        this.topleft = new SparkMax(topleftID, topleftmotortype);
        this.topright = new SparkMax(toprightID, toprightmotortype);
        this.bottomleft = new SparkMax(bottomleftID, bottomleftmotortype);
        this.bottomright = new SparkMax(bottomrightID, bottomleftmotortype);
        this.gyro = new ADXRS450_Gyro(gyroport);
        this.WheelDiameterinCM = WheelDiameterinCM;
    }



    @Override
    public void forward(double velocity){
        topleft.set(velocity);
        topright.set(velocity);
        bottomleft.set(velocity);
        bottomright.set(velocity);
    }
    @Override
    public void backward(double velocity){
        topleft.set(-velocity);
        topright.set(-velocity);
        bottomleft.set(-velocity);
        bottomright.set(-velocity);
    }
    @Override
    public void turn(double velocity, String direction){
        if(direction == "left"){
           topleft.set(-velocity);
           topright.set(velocity);
           bottomleft.set(-velocity);
           bottomright.set(velocity);
        }else{
            if(direction == "right"){
                topleft.set(velocity);
                topright.set(-velocity);
                bottomleft.set(velocity);
                bottomright.set(-velocity);
            }else{
                System.out.println("Not valid direction");
            }
        }
    }
    @Override
    public void stop(){
        topleft.stopMotor();
        topright.stopMotor();
        bottomleft.stopMotor();
        bottomright.stopMotor();
    }
    @Override
    public void forwardincm(double velocity, int centermeters){
        topleft.getEncoder().setPosition(0);
        double position = topleft.getEncoder().getPosition();
        double cimcufarance = Math.PI * WheelDiameterinCM;
        double rotationstotake = centermeters / cimcufarance;
        forward(velocity);
        while (position != rotationstotake) {
            System.out.println("not yet");
        }
        stop();
    }
    @Override
    public void backwardincm(double velocity, int centermeters){
        topleft.getEncoder().setPosition(0);
        double position = topleft.getEncoder().getPosition();
        double cimcufarance = Math.PI * WheelDiameterinCM;
        double rotationstotake = centermeters / cimcufarance;
        backward(velocity);
        while (position != rotationstotake) {
            System.out.println("not yet");
        }
        stop();
    }
        @Override
    public void turndegress(double velocity, int angle, String direction){
        if (direction == "left") {
            gyro.reset();
            turn(velocity, direction);
            while (gyro.getAngle() < angle-5) {
                System.out.println("not yet");
            }
            stop();
        }else{
            if (direction == "right") {
                gyro.reset();
                turn(velocity, direction);
                double negativeangle = gyro.getAngle() * -1;
                while (negativeangle < angle-5) {
                    System.out.println("not yet");
                }
                stop();
            }else{
                System.out.println("Not valid direction");
            }
        }
    }
    
}

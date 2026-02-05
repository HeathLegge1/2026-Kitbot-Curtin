package frc.robot.subsystem.ballsshooter;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;



public class ballsshooterIODev implements ballsshooterIO {
    private final SparkMax shootmotor;
    private final SparkMax suckermotor;



    public ballsshooterIODev(int shootmotor, MotorType shootMotorType, int suckmotor, MotorType suckMotorType) {
        this.shootmotor = new SparkMax(shootmotor, shootMotorType);
        this.suckermotor = new SparkMax(suckmotor, suckMotorType);
    }

    @Override
    public void startshooting(double velocity){
        shootmotor.set(velocity);
        suckermotor.set(-velocity);
    }
    @Override
    public void stopshooting(){
        shootmotor.stopMotor();
        suckermotor.stopMotor();
    }
    
}

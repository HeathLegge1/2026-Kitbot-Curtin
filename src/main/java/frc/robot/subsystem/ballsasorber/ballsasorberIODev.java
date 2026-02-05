package frc.robot.subsystem.ballsasorber;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;



public class ballsasorberIODev implements ballsasorberIO {
    private final SparkMax suckmotor;



    public ballsasorberIODev(int suckmotor, MotorType suckMotorType) {
        this.suckmotor = new SparkMax(suckmotor, suckMotorType);
    }

    @Override
    public void startsucking(double velocity){
        suckmotor.set(-velocity);
    }
    @Override
    public void stopsucking(){
        suckmotor.stopMotor();
    }
    
}

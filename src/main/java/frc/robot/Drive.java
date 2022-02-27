import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.other.task.Task;
import frc.robot.other.task.TaskRunner;

class Drive {

    //motor data
    static WPI_TalonFX leftMotor;
    static WPI_TalonFX rightMotor;
    static DifferentialDrive motorControl = new DifferentialDrive(leftMotor, rightMotor);
    static int[] robotPosition = {0,0};


    //tasks
    static TaskRunner driveTaskRunner = new TaskRunner();
    static Task driveTask = new Task();
    static Task calcPosition = new Task();

    public static void initializeDrive(){
        
        calcPosition.addStep(()->{
            robotPosition[0] += leftMotor.get();
        });

    }

    public static void driveToPosition(double motorSpeed, int turnAngle, int[] pos){
        
        driveTask.addStep(() -> {motorControl.arcadeDrive(motorSpeed, turnAngle);}, ()-> (pos==pos));

    }

}
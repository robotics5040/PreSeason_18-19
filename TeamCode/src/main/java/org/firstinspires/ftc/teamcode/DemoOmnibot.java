package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

@TeleOp(name="Demo Omnibot", group="Iterative Opmode")
//@Disabled
public class DemoOmnibot extends OpMode {

    //drive train motors
    public DcMotor leftMotor1 = null;
    public DcMotor leftMotor2 = null;
    public DcMotor rightMotor1 = null;
    public DcMotor rightMotor2 = null;

    @Override
    public void init() {
        //defining motors from config
        leftMotor1 = hardwareMap.dcMotor.get("left_motor1");
        leftMotor2 = hardwareMap.dcMotor.get("left_motor2");
        rightMotor1 = hardwareMap.dcMotor.get("right_motor1");
        rightMotor2 = hardwareMap.dcMotor.get("right_motor2");

        //seting power to the motors to make sure they are not moving
        leftMotor1.setPower(0);
        rightMotor1.setPower(0);
        leftMotor2.setPower(0);
        rightMotor2.setPower(0);

        // Send telemetry message to signify robot is ready to start;
        telemetry.addLine("Robot ready to go");
    }

    @Override
    public void loop() {
        //movement for robot input
        double leftStickY = gamepad1.left_stick_y;
        double leftStickX = gamepad1.left_stick_x;
        //rotation for robot input
        double rightStickX = gamepad1.right_stick_x;

        //movement for robot method being run
        omniDrive(leftStickX, leftStickY, rightStickX);

        RobotLog.ii("5040MSGHW","Motors running");
    }


    //method to move the robot
    public void omniDrive (double sideways, double forward, double rotation)
    {

        //number that slows down the drive train when moving. IT is goign at a third the speed with 3 and so forth
        int rotat = 2;

        try {
            leftMotor1.setPower(((forward - sideways)/rotat) + (-.3 * rotation));
            leftMotor2.setPower(((forward + sideways)/rotat) + (-.3 * rotation));
            rightMotor1.setPower(((-forward - sideways)/rotat) + (-.3* rotation));
            rightMotor2.setPower(((-forward + sideways)/rotat) + (-.3 * rotation));
        } catch (Exception e) {
        }
    }
}

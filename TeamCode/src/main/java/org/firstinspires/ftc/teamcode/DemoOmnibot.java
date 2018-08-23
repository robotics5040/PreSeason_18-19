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

    //bumper speed adjustion for master controls
    private double speed = 2;
    private boolean pressed = false;
    private boolean pressed2 = false;


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
        double leftStickX, leftStickY, rightStickX;
        //movement for robot input kid
        double leftStick1Y = gamepad1.left_stick_y;
        double leftStick1X = gamepad1.left_stick_x;
        //rotation for robot input kid
        double rightStick1X = gamepad1.right_stick_x;

        //movement for robot input master
        double leftStick2Y = gamepad2.left_stick_y;
        double leftStick2X = gamepad2.left_stick_x;
        //rotation for robot input master
        double rightStick2X = gamepad2.right_stick_x;

        //bumper for speeding up and slowing down the robot.
        boolean leftBumper2 = gamepad2.left_bumper;
        boolean rightBumper2 = gamepad2.right_bumper;

        //slows down robot with master bumper
        if(leftBumper2 && !pressed) {
            speed += 0.2;
            pressed = true;
        }
        // resets pressed for when it isnt pressed
        else if(pressed && !leftBumper2) {
            pressed = false;
        }
        //speeds up robot with master bumper
        if(rightBumper2 && !pressed2) {
            speed -= 0.2;
            pressed2 = true;
        }
        // resets pressed for when it isnt pressed
        else if(pressed2 && !rightBumper2) {
            pressed2 = false;
        }

        //stops divide by 0 error, fastest the robot can go
        if(speed < 1) {
            speed = 1;
        }

        if(leftStick2X == 0 && leftStick2Y == 0 && rightStick2X == 0) {
            leftStickX = leftStick1X;
            leftStickY = leftStick1Y;
            rightStickX = rightStick1X;
        }
        else {
            leftStickX = leftStick2X;
            leftStickY = leftStick2Y;
            rightStickX = rightStick2X;
        }

        //movement for robot method being run
        omniDrive(leftStickX, leftStickY, rightStickX);

        RobotLog.ii("5040MSGHW","Motors running");
    }


    //method to move the robot
    public void omniDrive (double sideways, double forward, double rotation)
    {

        //number that slows down the drive train when moving. IT is goign at a third the speed with 3 and so forth
        //double rotat = 2;

        try {
            leftMotor1.setPower(((forward - sideways)/speed/*rotat*/) + (-.3 * rotation));
            leftMotor2.setPower(((forward + sideways)/speed/*rotat*/) + (-.3 * rotation));
            rightMotor1.setPower(((-forward - sideways)/speed/*rotat*/) + (-.3* rotation));
            rightMotor2.setPower(((-forward + sideways)/speed/*rotat*/) + (-.3 * rotation));
        } catch (Exception e) {
        }
    }
}

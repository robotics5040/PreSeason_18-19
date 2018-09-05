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
    private DcMotor leftMotor1 = null;
    private DcMotor leftMotor2 = null;
    private DcMotor rightMotor1 = null;
    private DcMotor rightMotor2 = null;

    //bumper speed adjustion for master controls
    private double speed = 2;
    private boolean pressed = false;
    private boolean pressed2 = false;
    private boolean pressedA = false;
    private boolean move = true;

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

        //a input for stopping the robot working for kid TOGGLE
        boolean a2 = gamepad2.a;

        //toggle to stop robot
        if(a2 && !pressedA) {
            move = !move;
            pressedA = true;
        }
        else if(pressedA && !a2) {
            pressedA = false;
        }

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

        //changing who is driving and move is for stopping kid driving
        if(leftStick2X == 0 && leftStick2Y == 0 && rightStick2X == 0 && move) {
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
    private void omniDrive (double sideways, double forward, double rotation)
    {
        //try {
            leftMotor1.setPower(((forward - sideways)/speed) + (-.3 * rotation));
            leftMotor2.setPower(((forward + sideways)/speed) + (-.3 * rotation));
            rightMotor1.setPower(((-forward - sideways)/speed) + (-.3* rotation));
            rightMotor2.setPower(((-forward + sideways)/speed) + (-.3 * rotation));
        //} catch (Exception e) {
        //}
    }
}

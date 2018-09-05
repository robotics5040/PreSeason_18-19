package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

@TeleOp(name="OmnoBit", group="Iterative Opmode")
public class JeremyTest extends OpMode
{

    public DcMotor leftMotor1;
    public DcMotor leftMotor2;
    public DcMotor rightMotor1;
    public DcMotor rightMotor2;

    public void init()
    {

        leftMotor1 = hardwareMap.dcMotor.get ("leftMotor1");
        leftMotor2 = hardwareMap.dcMotor.get ("leftMotor2");
        rightMotor1 = hardwareMap.dcMotor.get ("rightMotor1");
        rightMotor2 = hardwareMap.dcMotor.get ("rightMotor2");

    }

    public void loop()
    {

        double leftStickY = gamepad1.left_stick_y;
        double leftStickX = gamepad1.left_stick_x;
        double rightStickX = gamepad1.right_stick_x;
        leftMotor1.setPower(leftStickY);
        leftMotor2.setPower(leftStickY);
        rightMotor1.setPower(-leftStickY);
        rightMotor2.setPower(-leftStickY);

    }

}

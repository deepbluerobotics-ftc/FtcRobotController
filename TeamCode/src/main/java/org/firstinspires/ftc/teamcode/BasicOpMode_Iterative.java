package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class BasicOpMode_Iterative extends OpMode
{
    HardwareHandler hardwareHandler;

    double leftPower;
    double rightPower;
    double drive;
    double turn;
    double slidePower;
    boolean clawClosed;

    @Override
    public void init() {
        hardwareHandler = new HardwareHandler(hardwareMap);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void start() {
        hardwareHandler.runtime.reset();
    }

    @Override
    public void loop() {
        drive = -gamepad1.left_stick_y;
        turn  =  gamepad1.right_stick_x;
        slidePower = gamepad1.right_trigger - gamepad1.left_trigger;
        leftPower  = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower = Range.clip(drive - turn, -1.0, 1.0) ;

        if (gamepad1.a && !gamepad1.b) {
            clawClosed = true;
        }
        else if (!gamepad1.a && gamepad1.b) {
            clawClosed = false;
        }

        hardwareHandler.setDrivePower(leftPower, rightPower);
        hardwareHandler.setSlidePower(slidePower);
        hardwareHandler.closeClaw(clawClosed);
    }

    @Override
    public void stop() {
        hardwareHandler.resetPower();
        hardwareHandler.resetSlide();
        hardwareHandler.closeClaw(false);
    }
}

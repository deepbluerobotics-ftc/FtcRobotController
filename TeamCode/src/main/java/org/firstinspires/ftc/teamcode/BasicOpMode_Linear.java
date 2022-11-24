package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
public class BasicOpMode_Linear extends LinearOpMode {
    HardwareHandler hardwareHandler;

    @Override
    public void runOpMode() throws InterruptedException {
        hardwareHandler = new HardwareHandler(hardwareMap);

        telemetry.addData("Status", "Initialized");

        waitForStart();
        hardwareHandler.runtime.reset();

        hardwareHandler.driveToPosition(12);

        hardwareHandler.resetPower();
    }
}

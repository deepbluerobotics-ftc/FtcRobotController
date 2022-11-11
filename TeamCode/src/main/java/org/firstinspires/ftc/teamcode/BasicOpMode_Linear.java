package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class BasicOpMode_Linear extends LinearOpMode {
    HardwareHandler hardwareHandler;

    @Override
    public void runOpMode() throws InterruptedException {
        hardwareHandler = new HardwareHandler(hardwareMap);

        telemetry.addData("Status", "Initialized");

        hardwareHandler.setDrivePower(1, 1);
        Thread.sleep(1000);
        hardwareHandler.setDrivePower(0, 0);

        hardwareHandler.resetPower();
    }
}

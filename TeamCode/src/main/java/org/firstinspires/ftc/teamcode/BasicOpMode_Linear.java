package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
public class BasicOpMode_Linear extends LinearOpMode {
    HardwareHandler hardwareHandler;

    @Override
    public void runOpMode() {
        hardwareHandler = new HardwareHandler(hardwareMap);

        telemetry.addData("Status", "Initialized");

        waitForStart();
        hardwareHandler.runtime.reset();

        hardwareHandler.driveToPosition(12, 12);
        hardwareHandler.rotateTo(0.25);
        hardwareHandler.driveToPosition(12, 12);

        hardwareHandler.resetPower();
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Linear OpMode Red 2", group="Linear Opmode")
public class BasicOpMode_Linear_Red2 extends LinearOpMode {
    HardwareHandler hardwareHandler;

    @Override
    public void runOpMode() {
        hardwareHandler = new HardwareHandler(hardwareMap);

        telemetry.addData("Status", "Initialized");

        waitForStart();
        hardwareHandler.runtime.reset();

        hardwareHandler.closeClaw(true);
        hardwareHandler.driveToPosition(720, 720);
        hardwareHandler.driveToPosition(-720, 720);
        hardwareHandler.driveToPosition(1440, 1440);
        hardwareHandler.driveToPosition(720, -720);
        hardwareHandler.driveToPosition(2880, 2880);
        hardwareHandler.driveToPosition(720, -720);
        hardwareHandler.slideToPosition(2880);
        hardwareHandler.driveToPosition(720, 720);
        hardwareHandler.closeClaw(false);
        hardwareHandler.slideToPosition(0);
        hardwareHandler.driveToPosition(-720, -720);
        hardwareHandler.driveToPosition(720, -720);
        hardwareHandler.driveToPosition(2880, 2880);
        hardwareHandler.driveToPosition(720, -720);
        hardwareHandler.driveToPosition(720, 720);

        hardwareHandler.resetPower();
    }
}

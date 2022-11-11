package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwareHandler {
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor linearSlide;
    public DcMotor claw;

    public HardwareMap hardwareMap;

    public HardwareHandler(HardwareMap hm)
    {
        this.hardwareMap = hm;

        this.leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        this.rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        this.linearSlide = hardwareMap.get(DcMotor.class, "linear_slide");
        this.claw = hardwareMap.get(DcMotor.class, "claw");

        this.resetPower();
        this.setZeroPowerBehavior();
        this.setMotorDirection();
        this.runUsingEncoder();
    }

    public void setMotorDirection(){
        this.leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        this.linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        this.claw.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void setZeroPowerBehavior() {
        this.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.claw.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void resetPower() {
        this.leftDrive.setPower(0);
        this.rightDrive.setPower(0);
        this.linearSlide.setPower(0);
        this.claw.setPower(0);
    }

    public void runUsingEncoder() {
        this.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.claw.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setDrivePower(double left, double right) {
        this.leftDrive.setPower(left);
        this.rightDrive.setPower(right);
    }
}

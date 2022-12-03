package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareHandler {
    public final int LINEAR_SLIDE_MAXIMUM_POSITION = 2880;

    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor linearSlide;
    public Servo claw;

    public HardwareMap hardwareMap;
    public ElapsedTime runtime;

    public HardwareHandler(HardwareMap hm)
    {
        this.hardwareMap = hm;
        this.runtime = new ElapsedTime();

        this.leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        this.rightDrive = hardwareMap.get(DcMotor.class, "right_drive");
        this.linearSlide = hardwareMap.get(DcMotor.class, "linear_slide");
        this.claw = hardwareMap.get(Servo.class, "claw");

        this.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        this.linearSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        this.claw.setDirection(Servo.Direction.REVERSE);

        this.resetPower();

        this.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetPower() {
        this.leftDrive.setPower(0);
        this.rightDrive.setPower(0);
        this.linearSlide.setPower(0);
        this.claw.setPosition(0.4);
    }

    public void setDrivePower(double left, double right) {
        this.leftDrive.setPower(left);
        this.rightDrive.setPower(right);
    }

    public void driveToPosition(int left, int right) {
        this.setDrivePower(0, 0);
        this.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.leftDrive.setTargetPosition(left);
        this.rightDrive.setTargetPosition(right);
        this.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.setDrivePower(1, 1);
        while ((leftDrive.isBusy() || rightDrive.isBusy()));
        this.setDrivePower(0, 0);
        this.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setSlidePower(double power) {
        if ((power > 0 && this.linearSlide.getCurrentPosition() >= LINEAR_SLIDE_MAXIMUM_POSITION) || (power < 0 && this.linearSlide.getCurrentPosition() <= 0)) {
            this.linearSlide.setPower(0);
        }
        else {
            this.linearSlide.setPower(power);
        }
    }

    public void slideToPosition(int position) {
        this.linearSlide.setPower(0);
        this.linearSlide.setTargetPosition(position);
        this.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.linearSlide.setPower(1);
        while (linearSlide.isBusy());
        this.linearSlide.setPower(0);
        this.linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void closeClaw(boolean closed) {
        if (closed) {
            this.claw.setPosition(0.7);
        }
        else {
            this.claw.setPosition(0.4);
        }
    }
}

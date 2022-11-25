package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareHandler {
    public final double WHEEL_RADIUS = 2; //inches
    public final double WHEEL_CIRCUMFERENCE = 2 * WHEEL_RADIUS * Math.PI; //inches
    public final double ROBOT_TURN_RADIUS = 8.25; //inches
    public final double ROBOT_TURN_CIRCUMFERENCE = 2 * ROBOT_TURN_RADIUS * Math.PI; //inches
    public final int ENCODER_COUNTS_PER_REVOLUTION = 1440;
    public final int LINEAR_SLIDE_MAXIMUM_POSITION = 5760;
    final double ACCELERATION_STEP = 0.02;

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

        this.resetPower();

        this.leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        this.linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);

        this.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void resetPower() {
        this.leftDrive.setPower(0);
        this.rightDrive.setPower(0);
        this.linearSlide.setPower(0);
    }

    public void setDrivePower(double left, double right) {
        this.leftDrive.setPower(left);
        this.rightDrive.setPower(right);
    }

    public void driveToPosition(double leftInches, double rightInches) {
        double drivePower = 0;
        int leftTargetPosition = (int) ((leftInches / WHEEL_CIRCUMFERENCE) * ENCODER_COUNTS_PER_REVOLUTION);
        int rightTargetPosition = (int) ((rightInches / WHEEL_CIRCUMFERENCE) * ENCODER_COUNTS_PER_REVOLUTION);
        this.setDrivePower(0, 0);
        this.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.leftDrive.setTargetPosition(leftTargetPosition);
        this.rightDrive.setTargetPosition(rightTargetPosition);
        this.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.setDrivePower(0, 0);
        do {
            drivePower += ACCELERATION_STEP;
            this.setDrivePower(drivePower, drivePower);
        }
        while ((leftDrive.isBusy() || rightDrive.isBusy()) && drivePower < 1);
        this.setDrivePower(0, 0);
        this.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void rotateTo(double rotations) {
        double inches = rotations * ROBOT_TURN_CIRCUMFERENCE;
        this.driveToPosition(inches, -inches);
    }

    public void setSlidePower(double power) {
        if ((power > 0 && this.linearSlide.getCurrentPosition() >= LINEAR_SLIDE_MAXIMUM_POSITION) || (power < 0 && this.linearSlide.getCurrentPosition() <= 0)) {
            this.linearSlide.setPower(0);
        }
        else {
            this.linearSlide.setPower(power);
        }
    }

    public void resetSlide() {
        this.linearSlide.setPower(0);
        this.linearSlide.setTargetPosition(0);
        this.linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.linearSlide.setPower(1);
        while (this.linearSlide.isBusy()) {}
        this.linearSlide.setPower(0);
        this.linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void closeClaw(boolean closed) {
        if (closed) {
            this.claw.setPosition(1);
        }
        else {
            this.claw.setPosition(0);
        }
    }
}

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
    public final int ACCELERATION_ENCODER_COUNT_MARGIN = 1440;

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
        int leftEndAcceleration;
        int leftStartDeceleration;
        int rightEndAcceleration;
        int rightStartDeceleration;
        int leftTargetPosition = (int) ((leftInches / WHEEL_CIRCUMFERENCE) * ENCODER_COUNTS_PER_REVOLUTION);
        int rightTargetPosition = (int) ((rightInches / WHEEL_CIRCUMFERENCE) * ENCODER_COUNTS_PER_REVOLUTION);
        if (Math.abs(leftTargetPosition) < ACCELERATION_ENCODER_COUNT_MARGIN * 2) {
            leftEndAcceleration = leftTargetPosition / 2;
            leftStartDeceleration = leftTargetPosition / 2;
        }
        else {
            leftEndAcceleration = ACCELERATION_ENCODER_COUNT_MARGIN;
            leftStartDeceleration = leftTargetPosition - ACCELERATION_ENCODER_COUNT_MARGIN;
        }
        if (Math.abs(rightTargetPosition) < ACCELERATION_ENCODER_COUNT_MARGIN * 2) {
            rightEndAcceleration = rightTargetPosition / 2;
            rightStartDeceleration = rightTargetPosition / 2;
        }
        else {
            rightEndAcceleration = ACCELERATION_ENCODER_COUNT_MARGIN;
            rightStartDeceleration = rightTargetPosition - ACCELERATION_ENCODER_COUNT_MARGIN;
        }
        this.setDrivePower(0, 0);
        this.leftDrive.setTargetPosition(leftTargetPosition);
        this.rightDrive.setTargetPosition(rightTargetPosition);
        this.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (leftDrive.isBusy() || rightDrive.isBusy()) {
            if (leftDrive.getCurrentPosition() < leftEndAcceleration) {
                leftDrive.setPower((double) leftDrive.getCurrentPosition() / leftEndAcceleration + 0.1);
            }
            else if (leftDrive.getCurrentPosition() > leftStartDeceleration) {
                leftDrive.setPower((double) (leftTargetPosition - leftDrive.getCurrentPosition()) / leftStartDeceleration + 0.1);
            }
            else {
                leftDrive.setPower(1);
            }
            if (rightDrive.getCurrentPosition() < rightEndAcceleration) {
                rightDrive.setPower((double) rightDrive.getCurrentPosition() / rightEndAcceleration + 0.1);
            }
            else if (rightDrive.getCurrentPosition() > rightStartDeceleration) {
                rightDrive.setPower((double) (rightTargetPosition - rightDrive.getCurrentPosition()) / rightStartDeceleration + 0.1);
            }
            else {
                rightDrive.setPower(1);
            }
        }
        this.setDrivePower(0, 0);
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

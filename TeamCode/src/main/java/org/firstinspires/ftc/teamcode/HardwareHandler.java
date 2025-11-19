package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;

public class HardwareHandler {
    public static DcMotor FLDrive = null;
    public static DcMotor BLDrive = null;
    public static DcMotor FRDrive = null;
    public static DcMotor BRDrive = null;
    public static DcMotor Intake = null;
    public static CRServo AdjustUpL = null;
    public static CRServo AdjustUpR = null;
    public static CRServo AdjustForwL = null;
    public static CRServo AdjustForwR = null;
    public static DcMotor FlyWheelL = null;
    public static DcMotor FlyWheelR = null;

    public HardwareMap hardwareMap;

    public HardwareHandler(HardwareMap hm)
    {
        hardwareMap = hm; 
        FLDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        FRDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        BLDrive  = hardwareMap.get(DcMotor.class, "back_left_drive");
        BRDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        Intake = hm.get(DcMotor.class, "Intake");
        AdjustUpL = hm.get(CRServo.class, "adjustment_upward_left");
        AdjustUpR = hm.get(CRServo.class, "adjustment_upward_right");
        AdjustForwL = hm.get(CRServo.class, "adjustment_forward_left");
        AdjustForwR = hm.get(CRServo.class, "adjustment_forward_right");
        FlyWheelL = hm.get(DcMotor.class, "fly_wheel_left");
        FlyWheelR = hm.get(DcMotor.class, "fly_wheel_right");

        //set behavior when power is 0 all of them BRAKE
        BLDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BRDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FLDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FRDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FlyWheelL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FlyWheelR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //set direction
        BLDrive.setDirection(DcMotor.Direction.REVERSE);
        BRDrive.setDirection(DcMotor.Direction.FORWARD);
        FLDrive.setDirection(DcMotor.Direction.REVERSE);
        FRDrive.setDirection(DcMotor.Direction.FORWARD);
        Intake.setDirection(DcMotor.Direction.REVERSE);
        AdjustForwL.setDirection(CRServo.Direction.REVERSE);
        AdjustForwR.setDirection(CRServo.Direction.FORWARD);
        AdjustUpL.setDirection(CRServo.Direction.REVERSE);
        AdjustUpR.setDirection(CRServo.Direction.FORWARD);
        FlyWheelL.setDirection(DcMotor.Direction.REVERSE);
        FlyWheelR.setDirection(DcMotor.Direction.FORWARD);

        resetPower();
        resetRunModes();
    }
    public static void resetPower()
    {
        BLDrive.setPower(0);
        BRDrive.setPower(0);
        FLDrive.setPower(0);
        FRDrive.setPower(0);
        Intake.setPower(0);
        FlyWheelL.setPower(0);
        FlyWheelR.setPower(0);

        AdjustForwL.setPower(0); 
        AdjustForwR.setPower(0);
        AdjustUpL.setPower(0); 
        AdjustUpR.setPower(0);
    }
    public static void setPower(double frontLeft, double frontRight, double backLeft, double backRight,
                                double IntakePower, double flyWheel, double adjustUp, double adjustForw)
    {
        BLDrive.setPower(backLeft);
        BRDrive.setPower(backRight);
        FLDrive.setPower(frontLeft);
        FRDrive.setPower(frontRight);
        Intake.setPower(IntakePower);
        FlyWheelL.setPower(flyWheel);
        FlyWheelR.setPower(flyWheel);

        AdjustForwL.setPower(adjustForw);
        AdjustForwR.setPower(adjustForw);
        AdjustUpL.setPower(adjustUp);
        AdjustUpR.setPower(adjustUp);
    }
    public static void resetRunModes() {
        BLDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BRDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FLDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FRDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FlyWheelL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FlyWheelR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public static void resetTargetPositions() {
        BLDrive.setTargetPosition(0);
        BRDrive.setTargetPosition(0);
        FLDrive.setTargetPosition(0);
        FRDrive.setTargetPosition(0);

        FlyWheelL.setTargetPosition(0);
        FlyWheelR.setTargetPosition(0);
        
        Intake.setTargetPosition(0);
    }
    public static void setAutoRunModes() {
        BLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FlyWheelL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FlyWheelR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        BLDrive.setPower(1);
        BRDrive.setPower(1);
        FLDrive.setPower(1);
        FRDrive.setPower(1);

        FlyWheelL.setPower(0);
        FlyWheelR.setPower(0);

        Intake.setPower(0);
    }
    public static void setAutoRunModesSimple() {
        BLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        BRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FlyWheelL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FlyWheelR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        Intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public static void setDrivePower(double leftFront, double leftBack, double rightFront, double rightBack) {
        FLDrive.setPower(leftFront);
        FRDrive.setPower(rightFront);
        BRDrive.setPower(rightBack);
        BLDrive.setPower(leftBack);
    }

    public static void inchesForward(double distance) {
        int counts = (int) (distance * 1440 / 3.77953);
        FLDrive.setTargetPosition(FLDrive.getCurrentPosition() + counts);
        FRDrive.setTargetPosition(FRDrive.getCurrentPosition() + counts);
        BLDrive.setTargetPosition(BLDrive.getCurrentPosition() + counts);
        BRDrive.setTargetPosition(BRDrive.getCurrentPosition() + counts);
    }

    public static void inchesRotate(double distance) {
        int counts = (int) (distance * 1440 / 3.77953);
        FLDrive.setTargetPosition(FLDrive.getCurrentPosition() + counts);
        FRDrive.setTargetPosition(FRDrive.getCurrentPosition() + counts);
        BLDrive.setTargetPosition(BLDrive.getCurrentPosition() - counts);
        BRDrive.setTargetPosition(BRDrive.getCurrentPosition() - counts);
    }

    public static void inchesSideways(double distance) {
        int counts = (int) (distance * 1440 / 3.77953);
        FLDrive.setTargetPosition(FLDrive.getCurrentPosition() + counts);
        FRDrive.setTargetPosition(FRDrive.getCurrentPosition() - counts);
        BLDrive.setTargetPosition(BLDrive.getCurrentPosition() - counts);
        BRDrive.setTargetPosition(BRDrive.getCurrentPosition() + counts);
    }
}

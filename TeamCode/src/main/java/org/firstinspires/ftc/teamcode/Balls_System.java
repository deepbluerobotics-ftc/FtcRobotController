/* Copyright (c) 2021 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Basic: Balls_System", group="Linear OpMode")

public class Balls_System extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor FlyWheelL = null;
    private DcMotor FlyWheelR = null;
    private DcMotor Intake = null;
    private CRServo AdjustForwL = null;
    private CRServo AdjustBackL = null;
    private CRServo AdjustForwR = null;
    private CRServo AdjustBackR = null;

    private double IntakePower = 0;
    private double MidtakePower = 0;
    private double FlyWheelPower = 0;
    
    @Override
    public void runOpMode() {

        FlyWheelL = hardwareMap.get(DcMotor.class, "fly_wheel_left");
        FlyWheelR = hardwareMap.get(DcMotor.class, "fly_wheel_right");

        Intake = hardwareMap.get(DcMotor.class, "intake");

        AdjustUpL = hardwareMap.get(CRServo.class, "adjustment_upward_left");
        AdjustUpR = hardwareMap.get(CRServo.class, "adjustment_upward_right");
        AdjustForwL = hardwareMap.get(CRServo.class, "adjustment_forward_left");
        AdjustForwR = hardwareMap.get(CRServo.class, "adjustment_forward_right");

        FlyWheelL.setDirection(DcMotor.Direction.REVERSE);
        FlyWheelR.setDirection(DcMotor.Direction.FORWARD);

        Intake.setDirection(DcMotor.Direction.FORWARD);

        AdjustBackL.setDirection(CRServo.Direction.FORWARD);
        AdjustForwL.setDirection(CRServo.Direction.FORWARD);
        AdjustBackR.setDirection(CRServo.Direction.REVERSE);
        AdjustBackL.setDirection(CRServo.Direction.REVERSE);

        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //Balls System Logic

            if (gamepad1.right_trigger > 0.3){
                FlyWheelPower = 1;
            } else{
                FlyWheelPower = 0;
            }
            
            if (gamepad1.left_trigger > 0.3){
                IntakePower = .5;
            } else {
                IntakePower = 0;
            }
            if (gamepad1.right_bumper){
                MidtakePower = 1;
            } else {
                MidtakePower = 0;
            }
            //set motors to correct power
            FlyWheelL.setPower(FlyWheelPower);
            FlyWheelR.setPower(-FlyWheelPower);

            Intake.setPower(IntakePower);

            AdjustBackL.setPower(MidtakePower);
            AdjustForwL.setPower(MidtakePower);
            AdjustBackR.setPower(-MidtakePower);
            AdjustForwR.setPower(-MidtakePower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Intake:", IntakePower);
            telemetry.addData("Midtake", MidtakePower);
            telemetry.addData("FlyWheel", FlyWheelPower);

            telemetry.update();
        }
    }}

/* Copyright (c) 2017 FIRST. All rights reserved.
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

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;


// Ex: "Hardware hardware = new Hardware(hardwareMap, telemetry)"
// https://github.com/SuitBots/ftc_app/blob/isaac5-resq/FtcRobotController/src/main/java/com/suitbots/resq/Isaac5.java
public class Hardware
{
    public DcMotorEx lf, lb, rf, rb;

    public final RobotConstants constants = new RobotConstants();

    public Telemetry telemetry;
    public IMU imu;
    Hardware(HardwareMap hardwareMap, Telemetry _telemetry) {
        telemetry = _telemetry;

        lf = hardwareMap.get(DcMotorEx.class, "lf");
        lb = hardwareMap.get(DcMotorEx.class, "lb");
        rf = hardwareMap.get(DcMotorEx.class, "rf");
        rb = hardwareMap.get(DcMotorEx.class, "rb");

        lf.setDirection(constants.lf_direction);
        lb.setDirection(constants.lb_direction);
        rf.setDirection(constants.rf_direction);
        rb.setDirection(constants.rb_direction);

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                        RevHubOrientationOnRobot.UsbFacingDirection.UP
                )
        );
        imu.initialize(parameters);
        // may require some calibration period here?
        imu.resetYaw();
    }

    public void setDriveMotors(double _lf, double _lb, double _rf, double _rb) {
        lf.setVelocity((int)(_lf * constants.MOTOR_VELOCITY));
        lb.setVelocity((int)(_lb * constants.MOTOR_VELOCITY));
        rf.setVelocity((int)(_rf * constants.MOTOR_VELOCITY));
        rb.setVelocity((int)(_rb * constants.MOTOR_VELOCITY));

    }
    public void mecanumDrive(double forward, double strafe, double rotate) {
        double denominator = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(rotate), 1);

        double LF = (forward + strafe + rotate) / denominator;
        double LB = (forward - strafe + rotate) / denominator;
        double RF = (forward - strafe - rotate) / denominator;
        double RB = (forward + strafe - rotate) / denominator;

        setDriveMotors(LF, LB, RF, RB);
    }
    public void updateTelemetry() {
        telemetry.addData("LF: ", lf.getVelocity());
        telemetry.addData("LB: ", lb.getVelocity());
        telemetry.addData("RF: ", rf.getVelocity());
        telemetry.addData("RB: ", rb.getVelocity());
    }

}

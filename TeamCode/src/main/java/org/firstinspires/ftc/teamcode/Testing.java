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

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.ArrayList;
import java.util.List;


@TeleOp(name="Testing", group="Teleop")
public class Testing extends OpMode
{

    private Hardware hardware;
    private int deviceIndex = 0;
    private boolean rightBumperPrev = false;
    private boolean leftBumperPrev = false;
    private List<DcMotorEx> devices = null;

    @Override
    public void init() {
        hardware = new Hardware(hardwareMap, telemetry);

        devices = new ArrayList<>();
        devices.add(hardware.lf);
        devices.add(hardware.rf);
        devices.add(hardware.lb);
        devices.add(hardware.rb);

        telemetry.addLine("Status: Initialized");

    }

    @SuppressLint("DefaultLocale")
    @Override
    public void loop() {
        if (gamepad1.right_bumper && !rightBumperPrev) {
            deviceIndex = (deviceIndex + 1) % devices.size();
        }
        if (gamepad1.left_bumper && !leftBumperPrev) {
            deviceIndex = (deviceIndex - 1 + devices.size()) % devices.size();
        }

        telemetry.addLine("Current Device");
        switch (deviceIndex) {
            case 0:
                telemetry.addLine("Left Front Drive");
                break;
            case 1:
                telemetry.addLine("Right Front Drive");
                break;
            case 2:
                telemetry.addLine("Left Back Drive");
                break;
            case 3:
                telemetry.addLine("Right Back Drive");
                break;
        }
        devices.get(deviceIndex).setPower(-gamepad1.left_stick_y);

        telemetry.addLine("Velocities:");
        telemetry.addLine(String.format("LF: %.2f, RF: %.2f, LB: %.2f, RB: %.2f",
                hardware.lf.getVelocity(),
                hardware.rf.getVelocity(),
                hardware.lb.getVelocity(),
                hardware.rb.getVelocity()
        ));
        telemetry.update();

        rightBumperPrev = gamepad1.right_bumper;
        leftBumperPrev = gamepad1.left_bumper;
    }

}

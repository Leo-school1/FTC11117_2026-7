
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Teleop", group="Teleop")
public class Teleop extends OpMode
{
    private Hardware hardware;

    @Override
    public void init() {
        hardware = new Hardware(hardwareMap, telemetry);
        telemetry.addLine("Status: Initialized");
        telemetry.update();

    }

    @Override
    public void loop() {
        hardware.mecanumDrive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        hardware.updateTelemetry();

        telemetry.update();
    }
}

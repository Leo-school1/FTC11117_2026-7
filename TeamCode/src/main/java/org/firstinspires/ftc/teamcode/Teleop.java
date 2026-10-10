
package org.firstinspires.ftc.teamcode;

import android.media.audiofx.Visualizer;

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
        Intake();
        Transfer();

        hardware.updateTelemetry();
        telemetry.update();
    }

    public void Intake() {
        if (gamepad1.x) {
            hardware.intake.setPower(1);
        } else if (gamepad1.b) {
            hardware.intake.setPower(-1);
        } else {
            hardware.intake.setPower(0);
        }
    }

    public void Transfer() {
        if (gamepad1.y) {
            hardware.transfer.setPower(1);
        } else if (gamepad1.b) {
            hardware.transfer.setPower(-1);
        } else {
            hardware.transfer.setPower(0);
        }
    }



}

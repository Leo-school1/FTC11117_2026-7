package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Localizer {
    private final Telemetry telemetry;
    private final RobotConstants constants = new RobotConstants();
    public IMU imu;

    public Localizer(HardwareMap hardwareMap, Telemetry _telemetry) {
        telemetry = _telemetry;

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

    public void updateTelemetry() {
        telemetry.addData("Yaw (degrees):", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addData("Yaw (radians):", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
    }

}
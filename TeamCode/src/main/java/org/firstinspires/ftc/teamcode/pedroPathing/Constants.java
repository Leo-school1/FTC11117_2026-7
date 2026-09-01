package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.ftc.localization.constants.TwoWheelConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.RobotConstants;


public class Constants {
    public static final RobotConstants constants = new RobotConstants();

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(4.6);



    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .leftFrontMotorName("lf")
            .rightFrontMotorName("rf")
            .leftRearMotorName("lb")
            .rightRearMotorName("rb")
            .leftFrontMotorDirection(constants.lf_direction)
            .leftRearMotorDirection(constants.lb_direction)
            .rightFrontMotorDirection(constants.rf_direction)
            .rightRearMotorDirection(constants.rb_direction);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 0.8, 1);

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(1.427)
            .strafePodX(0.472)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .pinpointLocalizer(localizerConstants)
                .mecanumDrivetrain(driveConstants)
                .build();
    }
}

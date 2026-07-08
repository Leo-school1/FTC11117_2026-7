package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.ivy.CommandBuilder;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.util.Timer;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;



import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "MainAuto", group = "Auto")
public class MainAuto extends OpMode {

    private Follower follower;

    // ===== POSES =====
    private final Pose startPose = new Pose(72, 72, Math.toRadians(90));
    private final Pose shootPose = new Pose(100, 115, Math.toRadians(40));


    // ===== PATHS =====
    private PathChain testPath;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);

        follower.setPose(startPose);

        buildPaths();
    }

    private void buildPaths() {
        testPath = follower.pathBuilder()
                .addPath(new BezierLine(startPose, shootPose))
                .setLinearHeadingInterpolation(
                        startPose.getHeading(),
                        shootPose.getHeading()
                )
                .build();
    }

    @Override
    public void start() {
        // Run path ONCE
        follower.followPath(testPath, true);


    }

    @Override
    public void loop() {
        // Always update follower
        follower.update();

        // ===== TELEMETRY =====
        Pose pose = follower.getPose();

        telemetry.addData("Busy", follower.isBusy());
        telemetry.addData("X", pose.getX());
        telemetry.addData("Y", pose.getY());
        telemetry.addData("Heading (deg)", Math.toDegrees(pose.getHeading()));


        telemetry.update();
    }
}
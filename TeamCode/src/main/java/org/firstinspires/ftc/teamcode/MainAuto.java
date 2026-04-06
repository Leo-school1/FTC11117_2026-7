package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.util.Timer;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "MainAuto", group = "Auto")
public class MainAuto extends OpMode {

    private Follower follower;

    // ===== POSES =====
    private final Pose startPose = new Pose(72, 72, Math.toRadians(90));
    private final Pose forwardPose = new Pose(72, 96, Math.toRadians(90));

    // ===== PATHS =====
    private PathChain testPath;

    private boolean pathStarted = false;

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);

        follower.setPose(startPose);

        buildPaths();
    }

    private void buildPaths() {
        testPath = follower.pathBuilder()
                .addPath(new BezierLine(startPose, forwardPose))
                .setLinearHeadingInterpolation(
                        startPose.getHeading(),
                        forwardPose.getHeading()
                )
                .build();
    }

    @Override
    public void start() {
        // Run path ONCE
        follower.followPath(testPath, true);
        pathStarted = true;
    }

    @Override
    public void loop() {
        // Always update follower
        follower.update();

        // ===== TELEMETRY =====
        Pose pose = follower.getPose();

        telemetry.addData("Started", pathStarted);
        telemetry.addData("Busy", follower.isBusy());
        telemetry.addData("X", pose.getX());
        telemetry.addData("Y", pose.getY());
        telemetry.addData("Heading (deg)", Math.toDegrees(pose.getHeading()));

        if (!follower.isBusy() && pathStarted) {
            telemetry.addLine("Path Complete");
        }

        telemetry.update();
    }
}
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


@Autonomous(name = "MainAuto", group = "Autonomous")
public class MainAuto extends OpMode {
    Hardware hardware;

    private Follower follower;
    public enum PathState {
        MOVE_FORWARD,
        HOLD,
    }
    PathState pathstate;
    // POSES
    private final Pose startPose = new Pose(72,72, Math.toRadians(90));
    private final Pose forwardPose = new Pose(84, 72, Math.toRadians(90));

    private PathChain forwardChain;
    public void buildPaths() {
        forwardChain = follower.pathBuilder()
                .addPath(new BezierLine(startPose, forwardPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), forwardPose.getHeading())
                .build();
    }

    public void pathStateUpdate() {
        switch(pathstate) {
            case MOVE_FORWARD:
                follower.followPath(forwardChain, true);
                pathstate = PathState.HOLD;
                break;
            case HOLD:
                if (!follower.isBusy()) {
                    telemetry.addLine("Done");
                }
                break;
            default:
                telemetry.addLine("Enountered Default Case");
                break;
        }
    }

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
    }

    @Override
    public void loop() {
        follower.update();
        pathStateUpdate();
        hardware.updatePose();
        follower.setPose(hardware.getRobotPose());
        // TODO add localization

        telemetry.addData("Path State", pathstate.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
    }



}

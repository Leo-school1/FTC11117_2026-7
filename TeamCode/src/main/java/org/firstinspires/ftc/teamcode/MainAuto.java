package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous(name = "Main Opmode", group = "Autonomous")
public class MainAuto extends LinearOpMode {

    Follower follower;

    @Override
    public void runOpMode() {
        follower = Constants.createFollower(hardwareMap);

        follower.setStartingPose(new Pose(0, 0, 0));

        /*
        Path path = new PathBuilder(follower)

                .build();
        */

        waitForStart();

    }

}

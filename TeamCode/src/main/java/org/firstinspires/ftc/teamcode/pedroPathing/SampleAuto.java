package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.qualcomm.robotcore.util.RobotLog;


@Autonomous
public class SampleAuto extends OpMode {
    private Follower follower;
    private Timer pathTimer;
    private Timer opModeTimer;

    public enum PathState {
        // START POSITION_
        ENDPOSITION,
        FIRST_MOVE,
        STARTPOSITION
    }

    PathState pathState;

    private final Pose startPose = new Pose(56, 36, Math.toRadians(90));
    private final Pose endPose = new Pose(80, 36, Math.toRadians(90));

    private PathChain drive;

    public void buildPath() {
        drive = follower.pathBuilder()
                .addPath(new BezierLine(startPose, endPose))
//                .setTangentHeadingInterpolation()
                .setLinearHeadingInterpolation(startPose.getHeading(), endPose.getHeading())
                .build();
    }

    public void UpdateState() {
        switch (pathState) {
            case STARTPOSITION:
                follower.followPath(drive);
                pathState = PathState.FIRST_MOVE;
                break;
            case FIRST_MOVE:

                if (!follower.isBusy()) {
                    telemetry.addLine("DONE");
                            telemetry.update();

                }

                break;
        }


    }

    @Override
    public void init() {
        pathState = PathState.STARTPOSITION;
        follower = Constants.createFollower(hardwareMap);

        buildPath();
        follower.setPose(startPose);
    }


    @Override
    public void loop() {

        follower.update();
        UpdateState();
        telemetry.addData("PathState", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("Heading", follower.getPose().getHeading());
        RobotLog.ii(pathState.toString(), "x=%.2f y=%.2f heading=%.1f",
                follower.getPose().getX(),  follower.getPose().getY(), follower.getPose().getHeading());

    }
}

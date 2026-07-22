package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.Encoder;
import com.pedropathing.ftc.localization.constants.ThreeWheelIMUConstants;
import com.pedropathing.ftc.localization.localizers.ThreeWheelIMULocalizer;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(6);

    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {

        MecanumConstants mecanumConstants = new MecanumConstants()
                .leftFrontMotorName("FLeft")
                .rightFrontMotorName("FRight")
                .leftRearMotorName("BLeft")
                .rightRearMotorName("BRight")
                .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
                .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
                .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
                .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD);

        ThreeWheelIMUConstants localizerConstants = new ThreeWheelIMUConstants()
//                      .forwardTicksToInches(.001989436789)
//                      .strafeTicksToInches(.001989436789)
//                      .turnTicksToInches(.001989436789)
                .leftPodY(8.125)
                .rightPodY(-8.125)
                .strafePodX(-6.75)
                // ?? add IMU name
                .leftEncoder_HardwareMapName("FRight")
                .rightEncoder_HardwareMapName("BRight")
                .strafeEncoder_HardwareMapName("FLeft")
                .IMU_Orientation(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.RIGHT));
                // ?? hey maybe change odometry encoder direction
//                         .leftEncoderDirection(Encoder.FORWARD)
//                         .rightEncoderDirection(Encoder.FORWARD)
//                         .strafeEncoderDirection(Encoder.FORWARD);

        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(mecanumConstants)
                .pathConstraints(pathConstraints)
                .threeWheelIMULocalizer(localizerConstants)

                .build();
//                      .setLocalizer(new ThreeWheelIMULocalizer(hardwareMap, constants)).build();

    }
}

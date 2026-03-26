package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.robot.Robot;


public class RobotConstants {
    public final DcMotor.Direction lf_direction, lb_direction, rf_direction, rb_direction;
    public final int MOTOR_VELOCITY = 2700;


    public RobotConstants() {
        // the current robot requires all motors to be reversed
        lf_direction = DcMotorSimple.Direction.REVERSE;
        lb_direction = DcMotorSimple.Direction.REVERSE;
        rf_direction = DcMotorSimple.Direction.REVERSE;
        rb_direction = DcMotorSimple.Direction.REVERSE;
    }

}

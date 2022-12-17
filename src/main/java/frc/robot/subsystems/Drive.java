// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
	
	private static Drive instance;

	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();
		}
		return instance;
	}

	private PWMVictorSPX frontRightMotor;
	private PWMVictorSPX backRightMotor;
	private PWMVictorSPX frontLeftMotor;
	private PWMVictorSPX backLeftMotor;
	private SpeedControllerGroup leftDrive;
	private SpeedControllerGroup rightDrive;
	private DifferentialDrive drive;
	private double speed, radius, turnMult;

	public Drive() {
		frontRightMotor = new PWMVictorSPX(9);
		backRightMotor = new PWMVictorSPX(8);
		frontLeftMotor = new PWMVictorSPX(7);
		backLeftMotor = new PWMVictorSPX(6);
		rightDrive = new SpeedControllerGroup(frontRightMotor, backRightMotor);
		leftDrive = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);
		drive = new DifferentialDrive(leftDrive, rightDrive);
		turnMult = 1;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public void setTurnMult(double turnMult) {
		this.turnMult = turnMult;
	}

	public double getTurnMult() {
		return turnMult;
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
		drive.curvatureDrive(-speed, radius * turnMult, ((speed < 0.05 && speed > -0.05) && radius != 0));
	}
}

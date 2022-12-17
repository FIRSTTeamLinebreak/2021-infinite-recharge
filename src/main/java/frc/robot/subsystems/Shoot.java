// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shoot extends SubsystemBase {
	private static Shoot instance;

	public static Shoot getInstance() {
		if (instance == null) {
			instance = new Shoot();
		}
		return instance;
	}

	private PWMSparkMax guideWheel;
	private PWMSparkMax flyWheel;
	private boolean guideToggle, flyToggle;
	private double guideSpeed, flySpeed;

	public Shoot() {
		guideWheel = new PWMSparkMax(4);
		flyWheel = new PWMSparkMax(5);
		guideToggle = flyToggle = false;
		guideSpeed = flySpeed = 0;
	}

	public void setGuideWheelSpeed(double speed) {
		guideSpeed = speed;
	}

	public void setGuideWheelEnabled(boolean enabled) {
		guideToggle = enabled;
	}

	public void setFlyWheelSpeed(double speed) {
		flySpeed = speed;
	}

	public void setFlyWheelEnabled(boolean enabled) {
		flyToggle = enabled;
	}

	public double getGuideWheelSpeed() {
		return guideSpeed;
	}

	public boolean getGuideWheelEnabled() {
		return guideToggle;
	}

	public double getFlyWheelSpeed() {
		return flySpeed;
	}

	public boolean getFlyWheelEnabled() {
		return flyToggle;
	}

	@Override
	public void periodic() {
		//control fly wheel
		if(flyToggle) {
			flyWheel.set(flySpeed);
		}
		else {
			flyWheel.set(0);
		}

		//contol guide wheel
		if(guideToggle) {
			guideWheel.set(guideSpeed);
		}
		else {
			guideWheel.set(0);
		}
	}
}

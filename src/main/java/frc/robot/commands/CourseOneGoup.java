// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class CourseOneGoup extends SequentialCommandGroup {

	private static CourseOneGoup instance;

	public static CourseOneGoup getInstance() {
		if (instance == null) {
			instance = new CourseOneGoup();
		}
		return instance;
	}

	private CourseOneGoup() {
		addCommands(new toggleFlyWheel(true), new blankCommand().withTimeout(.75), new toggleGuideWheel(true), new blankCommand().withTimeout(1), new toggleGuideWheel(false), new toggleFlyWheel(false), new AutoDriveCommand(-.25).withTimeout(.5));
	}
}

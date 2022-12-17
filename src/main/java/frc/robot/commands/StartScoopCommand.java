// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Scoop;

public class StartScoopCommand extends CommandBase {

    private Scoop scoopSub;
    private boolean executed = false;

    /** Creates a new StartScoopCommand. */
    public StartScoopCommand() {
        scoopSub = Scoop.getInstance();
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        scoopSub.setEnabled(true);
        executed = true;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return executed;
    }
}

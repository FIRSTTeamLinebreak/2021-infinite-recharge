package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Robot;
import frc.robot.commands.DualTurnCommand;
import frc.robot.commands.StartScoopCommand;
import frc.robot.commands.StopScoopCommand;
import frc.robot.commands.TeleTurnMultCommand;
import frc.robot.commands.ToggleScoopDirection;
import frc.robot.commands.toggleFlyWheel;
import frc.robot.commands.toggleGuideWheel;

//Operator Interface
public class OI extends SubsystemBase {

	private static OI instance;

	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}

	private Joystick driveJoystick;
	private JoystickButton drift, guideOn, guideOff, flyOn, flyOff, startScoop1, startScoop2, stopScoop, switchScoop;

	public OI() {
		driveJoystick = new Joystick(1);
		drift = new JoystickButton(driveJoystick, 2);
		guideOn = new JoystickButton(driveJoystick, 3);
		guideOff = new JoystickButton(driveJoystick, 4);
		flyOn = new JoystickButton(driveJoystick, 5);
		flyOff = new JoystickButton(driveJoystick, 6);
		startScoop1 = new JoystickButton(driveJoystick, 1);
		startScoop2 = new JoystickButton(driveJoystick, 9);
		stopScoop = new JoystickButton(driveJoystick, 7);
		switchScoop = new JoystickButton(driveJoystick, 8);

		drift.whenPressed(new TeleTurnMultCommand(.85));
		drift.whenReleased(new TeleTurnMultCommand(.57));
		guideOn.whenPressed(new toggleGuideWheel(true));
		guideOff.whenPressed(new toggleGuideWheel(false));
		flyOn.whenPressed(new toggleFlyWheel(true));
		flyOff.whenPressed(new toggleFlyWheel(false));
		startScoop1.whenPressed(new StartScoopCommand());
		startScoop2.whenPressed(new StartScoopCommand());
		stopScoop.whenPressed(new StopScoopCommand());
		switchScoop.whenPressed(new ToggleScoopDirection());
	}

	public double getAxis(int axis) {
		return driveJoystick.getRawAxis(axis);
	}

	@Override
	public void periodic() {
		double mult = -(this.getAxis(3)-1)/2;
		if(Robot.getInstance().isOperatorControlEnabled()){
			new DualTurnCommand(this.getAxis(1) * mult, mult != 0 ? Math.pow(this.getAxis(2), 2) * (Math.signum(this.getAxis(2))) : 0).schedule();
		}
	}

}

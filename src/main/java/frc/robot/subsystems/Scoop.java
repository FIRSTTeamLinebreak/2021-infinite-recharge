package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Scoop extends SubsystemBase {

    public enum DIRECTIONS {
        UP, DOWN
    }

    private static Scoop instance;

    public static Scoop getInstance() {
        if (instance == null) {
            instance = new Scoop();
        }
        return instance;
    }

    private PWMSparkMax scoopM;
    private DigitalInput upSwitch, downSwitch;

    public Scoop() {
        scoopM = new PWMSparkMax(3);
        upSwitch = new DigitalInput(8);
        downSwitch = new DigitalInput(9);
        scoopM.setSafetyEnabled(false);
    }

    // true: down, false: up
    private DIRECTIONS direction = DIRECTIONS.UP;

    private boolean enabled = false;
    private final double targetSpeed = .4;

    public void setDirection(DIRECTIONS direction) {
        this.direction = direction;
    }

    public void toggleDirection() {
        switch (this.direction) {
            case UP:
                this.direction = DIRECTIONS.DOWN;
                break;
            case DOWN:
                this.direction = DIRECTIONS.UP;
                break;
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if ((!upSwitch.get() && this.direction == DIRECTIONS.UP)
                || (!downSwitch.get() && this.direction == DIRECTIONS.DOWN)) {
            this.setEnabled(false);
            this.toggleDirection();
            return;
        }
        if (enabled) {
            switch (direction) {
                case UP:
                    scoopM.set(targetSpeed + 0.1);
                    return;
                case DOWN:
                    scoopM.set(targetSpeed * -1);
                    return;
            }
        } else {
            switch (direction) {
                case UP:
                    scoopM.set(0);
                    return;
                case DOWN:
                    scoopM.set(0);
                    return;
            }
        }
    }

}
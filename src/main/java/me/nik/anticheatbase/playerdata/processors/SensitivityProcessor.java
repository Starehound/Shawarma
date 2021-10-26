package me.nik.anticheatbase.playerdata.processors;

import me.nik.anticheatbase.playerdata.Profile;
import me.nik.anticheatbase.playerdata.data.RotationData;
import me.nik.anticheatbase.utils.MathUtils;

/**
 * A sensitivity processor class that we'll be using in order to hold certain data
 *
 * NOTE: This does not include a way to grab the player's sensitivity,
 * Feel free to add your own method since every person does it differently.
 */
public class SensitivityProcessor {

    private final Profile profile;

    private double mouseX, mouseY, constantYaw, constantPitch, yawGcd, pitchGcd;

    public SensitivityProcessor(Profile profile) {
        this.profile = profile;
    }

    public void handle() {

        RotationData data = profile.getRotationData();

        final float deltaYaw = data.getDeltaYaw();
        final float deltaPitch = data.getDeltaPitch();

        final float lastDeltaYaw = data.getLastDeltaYaw();
        final float lastDeltaPitch = data.getLastDeltaPitch();

        this.yawGcd = MathUtils.getAbsoluteGcd(deltaYaw, lastDeltaYaw);
        this.pitchGcd = MathUtils.getAbsoluteGcd(deltaPitch, lastDeltaPitch);

        this.constantYaw = this.yawGcd / MathUtils.EXPANDER;
        this.constantPitch = this.pitchGcd / MathUtils.EXPANDER;

        this.mouseX = (int) (deltaYaw / this.constantYaw);
        this.mouseY = (int) (deltaPitch / this.constantPitch);

        handleSensitivity();
    }

    private void handleSensitivity() {

        //Your sensitivity processing here
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public double getConstantYaw() {
        return constantYaw;
    }

    public double getConstantPitch() {
        return constantPitch;
    }

    public double getYawGcd() {
        return yawGcd;
    }

    public double getPitchGcd() {
        return pitchGcd;
    }
}
package com.hbm.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AudioDynamic extends MovingSound {

	public float intendedVolume;
	public int keepAlive;
	public int timeSinceKA;;
	public boolean shouldExpire = false;;

	protected AudioDynamic(SoundEvent loc, SoundCategory cat) {
		super(loc, cat);
		this.repeat = true;
		this.attenuationType = ISound.AttenuationType.NONE;
		this.intendedVolume = 10;
	}
	
	public void setPosition(float x, float y, float z) {
		this.xPosF = x;
		this.yPosF = y;
		this.zPosF = z;
	}

	public void setAttenuation(ISound.AttenuationType type){
		this.attenuationType = type;
		volume = intendedVolume;
	}
	
	@Override
	public void update() {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		float f = 0;
		if(player != null) {
			if(attenuationType == ISound.AttenuationType.LINEAR){
				/*float f3 = intendedVolume;
                float f2 = 16.0F;

                if (f3 > 1.0F)
                {
                    f2 *= f3;
                }
                f = (float)Math.sqrt(Math.pow(xPosF - player.posX, 2) + Math.pow(yPosF - player.posY, 2) + Math.pow(zPosF - player.posZ, 2));
                volume = 1-f2/f;
                System.out.println(volume);*/
			} else {
				f = (float)Math.sqrt(Math.pow(xPosF - player.posX, 2) + Math.pow(yPosF - player.posY, 2) + Math.pow(zPosF - player.posZ, 2));
				volume = func(f, intendedVolume);
			}
		} else {
			volume = intendedVolume;
		}
	}
	
	public void start() {
		Minecraft.getMinecraft().getSoundHandler().playSound(this);
	}
	
	public void stop() {
		Minecraft.getMinecraft().getSoundHandler().stopSound(this);
	}
	
	public void setVolume(float volume) {
		this.intendedVolume = volume;
	}
	
	public void setPitch(float pitch) {
		this.pitch = pitch;
	}
	public void setKeepAlive(int keepAlive) {
		this.keepAlive = keepAlive;
		this.shouldExpire = true;
	}

	public void keepAlive() {
		this.timeSinceKA = 0;
	}
	
	public float func(float f, float v) {
		return (f / v) * -2 + 2;
	}

	public boolean isPlaying() {
		return Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(this);
	}
}

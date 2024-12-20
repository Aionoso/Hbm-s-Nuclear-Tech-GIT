package com.hbm.sound;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AudioWrapperClient extends AudioWrapper {

	AudioDynamic sound;
	
	public AudioWrapperClient(SoundEvent source, SoundCategory cat) {
		if(source != null)
			sound = new AudioDynamic(source, cat);
	}
	
	public void updatePosition(float x, float y, float z) {
		if(sound != null)
			sound.setPosition(x, y, z);
	}
	
	public void updateVolume(float volume) {
		if(sound != null)
			sound.setVolume(volume);
	}
	
	public void updatePitch(float pitch) {
		if(sound != null)
			sound.setPitch(pitch);
	}
	
	public float getVolume() {
		if(sound != null)
			return sound.getVolume();
		else
			return 1;
	}
	
	public float getPitch() {
		if(sound != null)
			return sound.getPitch();
		else
			return 1;
	}

	@Override
	public void keepAlive() {
		if(sound != null)
			sound.keepAlive();
	}
	
	public void startSound() {
		if(sound != null)
			sound.start();
	}
	
	public void stopSound() {
		if(sound != null)
			sound.stop();
	}

	@Override
	public boolean isPlaying() {
		return sound.isPlaying();
	}
}

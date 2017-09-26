package logic.audio;

import java.io.IOException;

import javax.sound.sampled.*;

public class AudioPlayer {
	private Clip clip;
	private AudioInputStream dais;
	
	public AudioPlayer(String s) throws IOException {
			AudioInputStream ais;
			try {
				ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
			} catch (UnsupportedAudioFileException e) {
				throw new IOException(e);
			}
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					baseFormat.getSampleRate(),
					16,
					baseFormat.getChannels(),
					baseFormat.getChannels() * 2,
					baseFormat.getSampleRate(),
					false
			);
			
			dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
			try {
				clip = AudioSystem.getClip();
				clip.open(dais);
			} catch (LineUnavailableException e) {
				throw new IOException(e);
			}

	}
	
	public void play() {
		if (clip == null) 
			return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		if (clip.isRunning())
			clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
	
	public void setVolume(int maxVol, int currentVol) {
		FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);		
		double gain = (1/((double)maxVol)) * currentVol;
		float db = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
		volume.setValue(db);
	}
	
	public AudioInputStream getAudioInputStream() {
		return dais;
	}
}

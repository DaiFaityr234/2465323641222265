package spelling;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioPlayer {
	static double durationInSeconds;
	static Clip clip;
	public static void playLoopSound(String file) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);

			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-15.0f); 
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	
	public static void playSound(String file) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
			AudioFormat format = audioInputStream.getFormat();
			long frames = audioInputStream.getFrameLength();
			durationInSeconds = (frames+0.0) / format.getFrameRate();  
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-30.0f); 
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	static double getLength() {
		return durationInSeconds;
	}
	static void stopSound() {
		clip.stop();
	}
}

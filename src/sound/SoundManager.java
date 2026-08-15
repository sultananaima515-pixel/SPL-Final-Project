
package sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.*;
import java.net.URL;

public class SoundManager {

        private static Clip backgroundClip;

        private static boolean soundEnabled = true;

        public static void setSoundEnabled(boolean enabled) {
            soundEnabled = enabled;

            if (!enabled) {
                stopBackgroundMusic();
            }
        }

        public static boolean isSoundEnabled() {
            return soundEnabled;
        }

        // Sound effects
        public static void playSound(String file) {

            if (!soundEnabled) return;

            try {

                URL url = SoundManager.class.getResource("/sound/" + file);

                AudioInputStream audio =
                        AudioSystem.getAudioInputStream(url);

                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Background music
        public static void playBackgroundMusic(String file) {

            if (!soundEnabled) return;

            try {

                stopBackgroundMusic();

                URL url =
                        SoundManager.class.getResource("/sound/" + file);

                AudioInputStream audio =
                        AudioSystem.getAudioInputStream(url);

                backgroundClip = AudioSystem.getClip();
                backgroundClip.open(audio);

                backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
                backgroundClip.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static void stopBackgroundMusic() {

            if (backgroundClip != null) {

                backgroundClip.stop();
                backgroundClip.close();
            }
        }
    }



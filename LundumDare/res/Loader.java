import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class Loader {

	static Loader l = new Loader();

	static BufferedImage getTexture(String name) {
		try {
			return ImageIO.read(l.getClass().getResource("/textures/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static String getLevelFile() {
		try {
			String tot = "";
			BufferedReader br = new BufferedReader(
					new InputStreamReader(l.getClass().getResourceAsStream("/rooms.lvl")));
			String s = br.readLine();
			while (s != null) {
				tot += s + "\n";
				s = br.readLine();
			}
			return tot;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

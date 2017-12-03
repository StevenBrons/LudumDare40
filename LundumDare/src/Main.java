
public class Main {

	static Level level;
	static Frame f = new Frame();
	static boolean running = false;

	public static void main(String args[]) {

		Generator g = new Generator();
		level = g.generate();
		start();
	}

	public static void start() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					long milis = System.currentTimeMillis();

					if (running) {
						level.update();
					}
					f.screen.drawAll(level);
					try {
						int t = (int) (100 - (System.currentTimeMillis() - milis));
						if (t < 0) {
							t = 0;
						}
						Thread.sleep(t);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}

}

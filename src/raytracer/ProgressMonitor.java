package raytracer;

public class ProgressMonitor {
	/**
	 * The increment of the progress monitor in percent.
	 */
	public static final int PROGRESS_INCREMENT_PERCENT = 5;
	/**
	 * The size of one step of the progress monitor.
	 */
	private int progressStep;
	/**
	 * The maximum value that will be set with the value parameter in the showProgress method
	 */
	private int maximumValue;
	/**
	 * Start time of ProgressMonitor, generated when value is changed externally
	 */
	private long startTime;
	/**
	 * Endtime, generated when value reaches indicated maximum
	 */
	private long endTime;
	/**
	 * Name of the activity. Start with uppercase letter, please.
	 */
	private String activity;
	
	public ProgressMonitor(String activity, int maximumValue, int progressStep) {
		this.maximumValue = maximumValue;
		this.progressStep = maximumValue * PROGRESS_INCREMENT_PERCENT / 100;
		this.startTime = -1;
		this.endTime = -1;
		this.activity = activity;
	}
	
	/**
	 * Displays the current progress according to the specified 
	 * value in a simple text progress bar.
	 * 
	 * @param value Current value e.g. of a loop. Used together with the specified <code>maximumValue</code>
	 * to generate percentage of the ProgressBar.
	 */
	public void showProgress(final int value) {
		if (value > 0 && startTime == -1) {
			System.out.println(activity + "...");
			startTime = System.currentTimeMillis();
		}
		
		if (value % progressStep == 0 || value >= maximumValue) {
			String progr = "|";
			for (int i = 0; i < value / progressStep; i++) {
				progr = progr + "|";
			}
			System.out.printf("%3d%% %s%n", (value * PROGRESS_INCREMENT_PERCENT / progressStep + PROGRESS_INCREMENT_PERCENT), progr);
		}
		
		if (value >= maximumValue - 1) {
			endTime = System.currentTimeMillis() - startTime;
			final int timeSek = (int)(endTime / 1000);
			System.out.printf("Done " + activity.toLowerCase() + " after %02d min %02d sec %02d msec%n", timeSek / 60, timeSek % 60, endTime % 1000);
		}
	}

	/**
	 * Time when progressBar receives first value > 0. 
	 * 
	 * @return startTime Time stamp in milliseconds
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * Time when progressBar reaches specified maximumValue.
	 * @return endTime Time stamp in milliseconds.
	 */
	public long getEndTime() {
		return endTime;
	}
	
	
}

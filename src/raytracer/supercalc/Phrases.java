package raytracer.supercalc;

public enum Phrases {
	NULL,
	/*
	 * Server States
	 */
	DONE_READING_RENDER_RESULT,
	
	/*
	 * Client States
	 */
	READING_DATA,
	TRANSMITTING_DATA,
	STARTED_RENDERING,
	CLOSING_CONNECTION,
	
	/*
	 * Errors
	 */
	
	DATA_ERROR,
	NO_RENDER_RESULT;
	
}

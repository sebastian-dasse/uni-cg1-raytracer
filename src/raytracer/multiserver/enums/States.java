package raytracer.multiserver.enums;

public enum States {
	/*
	 * Server:
	 * 1) Transmitting Data (Embeds dataStore)
	 * 2) Waiting for Render
	 * 3) Reading Data 
	 * 4) Closing Connection (Embeds dataStore)
	 * 
	 * Client:
	 * 1) Reading Data 
	 * 2) Executing Render (Embeds dataStore)
	 * 3) Transmitting Data (Embeds dataStore)
	 * 4) Closing Connection
	 */
	
	
	NULL,
	/*
	 * Shared States
	 */
	
	READING_DATA,
	TRANSMITTING_DATA,
	CLOSING_CONNECTION,
	
	/*
	 * Server States
	 */
	
	WAITING_FOR_RENDER,
	
	
	/*
	 * Client States
	 */
	
	EXECUTING_RENDER,
	
	
	/*
	 * Errors
	 */
	
	// (= ERROR)
	DATA_ERROR,
	NO_RENDER_RESULT;
	
}

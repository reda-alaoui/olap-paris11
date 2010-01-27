/**
 * 
 */
package query.utility;

import query.OlapQuery;

/**
 * @author Reda
 */
public class OlapQueryValidationException extends Exception {

	private static final long serialVersionUID = -3478402298883954264L;

	public OlapQueryValidationException(OlapQuery query) {
		super("Invalid OlapQuery "+query.toString());
	}
}

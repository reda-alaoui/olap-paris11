/**
 * 
 */
package schema;

/**
 * @author Reda
 *
 */
public interface Function {

	/**
	 * @return Function's name
	 */
	String getName();
	
	/**
	 * @return Function's domain
	 */
	Attribute getDomain();
	
	/**
	 * @return Function's range
	 */
	Attribute getRange();
}

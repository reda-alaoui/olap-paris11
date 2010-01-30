/**
 * 
 */
package schema;

/**
 * A function is defined by a name, an attribute as a domain and an attribute as a range
 * @author Reda
 * @see Attribute
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

/**
 * 
 */
package schema;

/**
 * @author Reda
 *
 */
public interface Function {

	String getName();
	
	Attribute getDomain();
	
	Attribute getRange();
}

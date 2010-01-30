/**
 * 
 */
package schema;

/**
 * An attribute is defined by a name and a data type
 * @author Reda
 * @see DataType
 */
public interface Attribute {
	
	/**
	 * @return Attribute's name
	 * 
	 */
	String getName();
	
	/**
	 *	Enumeration of data types that an attribute can take:
	 *	<pre>	- ID
	 *	- INTEGER
	 *	- NUMBER
	 *	- STRING
	 *</pre>
	 */
	enum DataType{
		ID,
		INTEGER,
		NUMBER,
		STRING
	}
	
	/**
	 * @return Attribute's data type
	 */
	DataType getDataType();
}

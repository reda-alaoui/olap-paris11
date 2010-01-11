/**
 * 
 */
package schema;

/**
 * @author Reda
 *
 */
public interface Attribute {
	
	/**
	 * @return Attribute's name
	 */
	String getName();
	
	/**
	 * @author Reda
	 *
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

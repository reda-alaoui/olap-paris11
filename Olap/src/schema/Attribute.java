/**
 * 
 */
package schema;

/**
 * @author Reda
 *
 */
public interface Attribute {
	
	String getName();
	
	enum DataType{
		ID,
		INTEGER,
		NUMBER,
		STRING
	}
	
	DataType getDataType();
}

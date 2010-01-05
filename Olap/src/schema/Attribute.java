/**
 * 
 */
package schema;

/**
 * @author Reda
 *
 */
public interface Attribute {
	
	public String getName();
	
	public enum DataType{
		ID,
		INTEGER,
		NUMBER,
		STRING
	}
	
	public DataType getDataType();
}

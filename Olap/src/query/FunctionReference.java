/**
 * 
 */
package query;
import schema.Function;

/**
 * FunctionReference is a PathExpression that refers to a SchemaFunction. </br>
 * For example the simple path "g" is a FunctionReference.</br>
 * Objects of this class are immutable
 * @author Julien.C
 */
public interface FunctionReference extends PathExpression{
	/**
	 * @return the referred function
	 */
	Function getRefferedFunction();
	
}

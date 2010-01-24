/**
 * 
 */
package query;
import schema.Function;

/**
 * FunctionReference is a PathExpression that only referred to a SchemaFunction
 * For example the simple path "g" is a FunctionReference. Object of this class
 * are immutable
 */
public interface FunctionReference extends PathExpression{
	/**
	 * @return the referredFunction this object referred to
	 */
	Function getRefferedFunction();
	
}

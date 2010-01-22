/**
 * 
 */
package query;
import schema.Function;

/**
 * @author Reda
 *
 */
public interface FunctionReference extends PathExpression{
	/**
	 * @return the referredFunction this object referred to
	 */
	Function getRefferedFunction();
	
}

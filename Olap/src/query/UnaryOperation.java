/**
 * 
 */
package query;

/**
 * UnaryOperation is composed of only one operand.
 * @author Julien.C
 */
public interface UnaryOperation extends PathExpression{
	
	/**
	 * @return the only operand
	 */
	PathExpression getOperand();

}

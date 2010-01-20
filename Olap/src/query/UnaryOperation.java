/**
 * 
 */
package query;

/**
 * UnaryOperation is composed of only one operand.
 */
public interface UnaryOperation extends PathExpression{
	
	/**
	 * @return the only operand
	 */
	PathExpression getOperand();

}

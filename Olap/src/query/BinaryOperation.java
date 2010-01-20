/**
 * 
 */
package query;


/**
 * BinaryOperation is an operator from part of an PathExpression
 * that gather 2 others PathExpression.
 * The BinaryOperation is directed, so its composed of a left and a right operand
 */
public interface BinaryOperation extends PathExpression{

	/**
	 * The left operand
	 * @return
	 */
	PathExpression getLeftOperand();
	
	/**
	 * The right operand
	 * @return
	 */
	PathExpression getRightOperand();
	
}

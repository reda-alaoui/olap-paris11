/**
 * 
 */
package query;


/**
 * BinaryOperation is an operator from part of an PathExpression
 * that gather 2 others PathExpression. </br>
 * The BinaryOperation is directed, so its composed of a left and a right operand.
 * @author Julien.C
 */
public interface BinaryOperation extends PathExpression{

	/**
	 * @return The left operand
	 */
	PathExpression getLeftOperand();
	
	/**
	 * @return The right operand
	 */
	PathExpression getRightOperand();
	
}

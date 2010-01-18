/**
 * 
 */
package query;


/**
 * @author Reda
 *
 */
public interface BinaryOperation extends PathExpression{

	PathExpression getLeftOperand();
	
	PathExpression getRightOperand();
	
}

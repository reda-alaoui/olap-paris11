package query.utility;

import query.PathExpression;

/**
 * @author Julien.C
 */
public class PathExpressionValidationException extends Exception {
	private static final long serialVersionUID = -6272716257828212778L;

	public PathExpressionValidationException(PathExpression p1) {
		super("Invalid PathExpression "+p1.toString());
	}

}

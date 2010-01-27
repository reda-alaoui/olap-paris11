package query.utility;

import query.PathExpression;

public class PathExpressionValidationException extends Exception {

	/**
	 * @author Julien.C
	 */
	private static final long serialVersionUID = -6272716257828212778L;

	public PathExpressionValidationException(PathExpression p1) {
		super("Invalid PathExpression "+p1.toString());
	}

}

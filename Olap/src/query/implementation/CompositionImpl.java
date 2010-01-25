/**
 * 
 */
package query.implementation;

import java.util.Iterator;
import query.Composition;
import query.PathExpression;
import schema.Attribute;


public class CompositionImpl implements Composition {
	
	PathExpression leftOperand;
	
	PathExpression rightOperand;
	
	public static Composition createComposition(PathExpression leftOperand,
			PathExpression rightOperand){
		return new CompositionImpl(leftOperand,rightOperand);
	}

	private CompositionImpl(PathExpression leftOperand,
			PathExpression rightOperand) {
		super();
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositionImpl other = (CompositionImpl) obj;
		if (leftOperand == null) {
			if (other.leftOperand != null)
				return false;
		} else if (!leftOperand.equals(other.leftOperand))
			return false;
		if (rightOperand == null) {
			if (other.rightOperand != null)
				return false;
		} else if (!rightOperand.equals(other.rightOperand))
			return false;
		return true;
	}

	@Override
	public Iterator<Attribute> getDomain() {
		return rightOperand.getDomain();
	}

	@Override
	public PathExpression getLeftOperand() {
		return leftOperand;
	}

	@Override
	public Iterator<Attribute> getRange() {
		return leftOperand.getRange();
	}

	@Override
	public PathExpression getRightOperand() {
		return rightOperand;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((leftOperand == null) ? 0 : leftOperand.hashCode());
		result = prime * result
				+ ((rightOperand == null) ? 0 : rightOperand.hashCode());
		return result;
	}

}

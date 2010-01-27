/**
 * 
 */
package query.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import query.Pairing;
import query.PathExpression;
import schema.Attribute;

/**
 * Implements {@link Pairing}
 * @author Julien.C
 * @author Reda
 */
public class PairingImpl implements Pairing {
	
	public static Pairing createPairing(PathExpression p1, PathExpression p2){
		return new PairingImpl(p1, p2);
	}

	private PathExpression leftOperand;

	private PathExpression rightOperand;
	private PairingImpl(PathExpression leftOperand, PathExpression rightOperand){
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
		PairingImpl other = (PairingImpl) obj;
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
		//or right Operand cause left and right 
		//have to have the same domain
		return leftOperand.getDomain();
	}
	
	@Override
	public PathExpression getLeftOperand() {
		return leftOperand;
	}

	@Override
	public Iterator<Attribute> getRange() {
		List<Attribute> range_list = new ArrayList<Attribute>();
		Iterator<Attribute> left_range = leftOperand.getRange();
		Iterator<Attribute> right_range = rightOperand.getRange();
		while(left_range.hasNext()){
			range_list.add(left_range.next());
		}
		while(right_range.hasNext()){
			range_list.add(right_range.next());
		}

		return range_list.iterator();
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

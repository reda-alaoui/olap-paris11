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
 * @author Reda
 *
 */
public class PairingImpl implements Pairing {
	
	private PathExpression leftOperand;
	private PathExpression rightOperand;
	
	private PairingImpl(PathExpression leftOperand, PathExpression rightOperand){
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	public static Pairing createPairing(PathExpression p1, PathExpression p2){
		return new PairingImpl(p1, p2);
		
	}
	
	@Override
	public PathExpression getLeftOperand() {
		return leftOperand;
	}

	@Override
	public PathExpression getRightOperand() {
		return rightOperand;
	}

	@Override
	public Iterator<Attribute> getDomain() {
		return leftOperand.getDomain();
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

}

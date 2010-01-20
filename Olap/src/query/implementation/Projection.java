/**
 * 
 */
package query.implementation;

import java.util.Iterator;
import java.util.List;

import query.PathExpression;
import query.UnaryOperation;
import schema.Attribute;

/**
 * The projection operator is used to restrict request on specific attributes.
 * It is composed of an Attribute List and a PathExpression
 */
public class Projection implements UnaryOperation {

	/**
	 * The list of attributes to restrict on
	 */
	List<Attribute> selectedAttribute;
	
	/**
	 * The PathExprresion on which apply the projection
	 */
	PathExpression pathExpression;
	
	/**
	 * Construct from attribute list and {@link PathExpression}
	 * @param selectedAttribute
	 * @param pathExpression
	 */
	public Projection(List<Attribute> selectedAttribute,
			PathExpression pathExpression) {
		super();
		this.selectedAttribute = selectedAttribute;
		this.pathExpression = pathExpression;
	}

	@Override
	public Iterator<Attribute> getDomain() {
		return null;
	}

	@Override
	public Iterator<Attribute> getRange() {
		return null;
	}
	
	List<Attribute> getSelectList(){
		return selectedAttribute;
	}

	@Override
	public PathExpression getOperand() {
		return pathExpression;
	};

}

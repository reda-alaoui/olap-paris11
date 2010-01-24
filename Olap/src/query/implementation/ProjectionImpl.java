/**
 * 
 */
package query.implementation;

import java.util.Iterator;
import java.util.List;

import query.PathExpression;
import query.Projection;
import schema.Attribute;

/**
 * The projection operator is used to restrict request on specific attributes.
 * It is composed of an Attribute List and a PathExpression
 */
public class ProjectionImpl implements Projection {

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
	public ProjectionImpl(List<Attribute> selectedAttribute,
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
	
	@Override
	public Iterator<Attribute> getSelectList(){
		return selectedAttribute.iterator();
	}

	@Override
	public PathExpression getOperand() {
		return pathExpression;
	};

}

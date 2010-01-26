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
	 * Construct from attribute list and {@link PathExpression}
	 * @param selectedAttribute
	 */
	private ProjectionImpl(List<Attribute> selectedAttribute) {
		super();
		this.selectedAttribute = selectedAttribute;
	}

	@Override
	public Iterator<Attribute> getDomain() {
		//TODO replace null
		return null;
	}

	@Override
	public Iterator<Attribute> getRange() {
		return selectedAttribute.iterator();
	}
	
	@Override
	public Iterator<Attribute> getSelectList(){
		return selectedAttribute.iterator();
	}

	public static Projection createProjection(List<Attribute> selectedAttribute) {
		return new ProjectionImpl(selectedAttribute);
	}

}

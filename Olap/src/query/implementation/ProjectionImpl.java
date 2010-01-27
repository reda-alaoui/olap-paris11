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
 * Implements {@link Projection}
 * @author Julien.C
 * @author Reda
 */
public class ProjectionImpl implements Projection {

	/**
	 * The list of attributes to restrict on
	 */
	List<Attribute> selectedAttribute;
	
	List<Attribute> domain;
	
	
	/**
	 * Construct from attribute list and {@link PathExpression}
	 * @param selectedAttribute
	 */
	private ProjectionImpl(List<Attribute> selectedAttribute, List<Attribute> domain) {
		super();
		this.selectedAttribute = selectedAttribute;
		this.domain = domain;
	}

	@Override
	public Iterator<Attribute> getDomain() {
		return domain.iterator();
	}

	@Override
	public Iterator<Attribute> getRange() {
		return selectedAttribute.iterator();
	}

	public static Projection createProjection(List<Attribute> selectedAttribute, List<Attribute> domain) {
		return new ProjectionImpl(selectedAttribute, domain);
	}

}

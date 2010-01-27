/**
 * 
 */
package query.implementation;

import java.util.Iterator;
import java.util.List;

import query.Projection;
import schema.Attribute;

/**
 * Implements {@link Projection}
 * @author Julien.C
 * @author Reda
 */
public class ProjectionImpl implements Projection {

	/**
	 * Construct from attribute list and domain
	 * @param selectedAttribute - attributes to project on
	 * @param domain - domain of the operator
	 * @return
	 */
	public static Projection createProjection(List<Attribute> selectedAttribute, List<Attribute> domain) {
		return new ProjectionImpl(selectedAttribute, domain);
	}
	
	/**
	 * The list of attributes to project on
	 */
	List<Attribute> selectedAttribute;
	
	/**
	 * The domain of the operator
	 */
	List<Attribute> domain;

	/**
	 * Construct from attribute list and domain
	 * @param selectedAttribute
	 */
	private ProjectionImpl(List<Attribute> selectedAttribute, List<Attribute> domain) {
		super();
		this.selectedAttribute = selectedAttribute;
		this.domain = domain;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectionImpl other = (ProjectionImpl) obj;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		if (selectedAttribute == null) {
			if (other.selectedAttribute != null)
				return false;
		} else if (!selectedAttribute.equals(other.selectedAttribute))
			return false;
		return true;
	}
	
	@Override
	public Iterator<Attribute> getDomain() {
		return domain.iterator();
	}

	@Override
	public Iterator<Attribute> getRange() {
		return selectedAttribute.iterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime
				* result
				+ ((selectedAttribute == null) ? 0 : selectedAttribute
						.hashCode());
		return result;
	}

}

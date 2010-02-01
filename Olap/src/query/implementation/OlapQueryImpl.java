/**
 * 
 */
package query.implementation;

import query.OlapQuery;
import query.PathExpression;
import query.QueryFactory.AggregationFunction;

/**
 * Implements {@link OlapQuery}
 * @author Julien.C
 * @author Reda
 */
public class OlapQueryImpl implements OlapQuery {
	
	public static OlapQuery createOlapQuery(PathExpression classifier, PathExpression measure,
			AggregationFunction aggregate){
		return new OlapQueryImpl(classifier, measure, aggregate);
	}
	private PathExpression classifier;
	
	private PathExpression measure;
	
	private AggregationFunction aggregate;
	
	private OlapQueryImpl(PathExpression classifier, PathExpression measure,
			AggregationFunction aggregate){
		this.classifier = classifier;
		this.measure = measure;
		this.aggregate = aggregate;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OlapQueryImpl other = (OlapQueryImpl) obj;
		if (aggregate == null) {
			if (other.aggregate != null)
				return false;
		} else if (!aggregate.equals(other.aggregate))
			return false;
		if (classifier == null) {
			if (other.classifier != null)
				return false;
		} else if (!classifier.equals(other.classifier))
			return false;
		if (measure == null) {
			if (other.measure != null)
				return false;
		} else if (!measure.equals(other.measure))
			return false;
		return true;
	}

	@Override
	public AggregationFunction getAggregate() {
		return aggregate;
	}

	@Override
	public PathExpression getClassifier() {
		return classifier;
	}

	@Override
	public PathExpression getMeasure() {
		return measure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aggregate == null) ? 0 : aggregate.hashCode());
		result = prime * result
				+ ((classifier == null) ? 0 : classifier.hashCode());
		result = prime * result + ((measure == null) ? 0 : measure.hashCode());
		return result;
	}

}

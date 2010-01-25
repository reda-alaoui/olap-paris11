/**
 * 
 */
package query.implementation;

import java.util.Iterator;

import query.OlapQuery;
import query.PathExpression;
import query.QueryFactory.AggregationFunction;
import schema.Attribute;

/**
 * @author Reda
 *
 */
public class OlapQueryImpl implements OlapQuery {
	
	private PathExpression classifier;
	private PathExpression measure;
	private AggregationFunction aggregate;
	
	private OlapQueryImpl(PathExpression classifier, PathExpression measure,
			AggregationFunction aggregate){
		this.classifier = classifier;
		this.measure = measure;
		this.aggregate = aggregate;
	}
	
	public static OlapQuery createOlapQuery(PathExpression classifier, PathExpression measure,
			AggregationFunction aggregate){
		return new OlapQueryImpl(classifier, measure, aggregate);
	}
	
	@Override
	public Iterator<Attribute> getDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Attribute> getRange() {
		// TODO Auto-generated method stub
		return null;
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

}

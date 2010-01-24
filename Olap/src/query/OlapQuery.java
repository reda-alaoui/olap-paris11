/**
 * 
 */
package query;

import query.QueryFactory.AggregationFunction;

/**
 *	An OLAP query is composed of :
 *  - 1 path as classifier
 *  - 1 path as measure
 *  - 1 aggregate function as aggregate {@link AggregationFunction}
 */
public interface OlapQuery extends PathExpression {
	
	PathExpression getClassifier();
	
	PathExpression getMeasure();
	
	AggregationFunction getAggregate();
}

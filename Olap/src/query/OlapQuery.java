/**
 * 
 */
package query;

import query.QueryFactory.AggregationFunction;

/**
 * Let S be a schema. An OLAP Query over S is a (ordered) triple Q = (u, v, op), satisfying the following 
 * conditions: </br></br>
 * &nbsp;&nbsp;&nbsp; -> u and v are path expressions such that source(u) = source(v) = O </br>
 * &nbsp;&nbsp;&nbsp; -> op is an operation over the target of v </br></br>
 * The expression u will be referred to as the classifier of Q and the expression v as the measure of Q. 
 * @author Reda
 */
public interface OlapQuery {
	
	/**
	 * @return the query's classifier
	 */
	PathExpression getClassifier();
	
	/**
	 * @return the query's measure
	 */
	PathExpression getMeasure();
	
	/**
	 * @return the aggregate's operation
	 */
	AggregationFunction getAggregate();
}

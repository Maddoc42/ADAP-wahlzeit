package de.bitdroid.adap.model;


public interface LocationVisitable {

	/**
	 * Used by the visitor pattern of {@link LocationVisitor}
	 * @param visitor The visitor to execute.
	 * @param param The parameter to pass to the visitor.
	 * @return The result of the visitor.
	 */
	public <P,R> R accept(LocationVisitor<P, R> visitor, P param);

}

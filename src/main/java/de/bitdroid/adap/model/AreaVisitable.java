package de.bitdroid.adap.model;


public interface AreaVisitable {

	/**
	 * Used by the visitor pattern of {@link AreaVisitor}
	 * @param visitor The visitor to execute.
	 * @param param The parameter to pass to the visitor.
	 * @return The result of the visitor.
	 */
	public <P,R> R accept(AreaVisitor<P, R> visitor, P param);

}

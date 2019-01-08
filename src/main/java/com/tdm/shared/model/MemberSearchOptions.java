package com.preauth.shared.model;

public class MemberSearchOptions {
	private boolean DataLoaded;
	private boolean FullyFunded; 
	private boolean PCCAEnabled;
	
	public boolean isDataLoaded() {
		return DataLoaded;
	}
	public void setDataLoaded(boolean isManual) {
		DataLoaded = isManual;
	}	
	public boolean isFullyFunded() {
		return FullyFunded;
	}
	public void setFullyFunded(boolean fullyFunded) {
		FullyFunded = fullyFunded;
	}
	public boolean isPCCAEnabled() {
		return PCCAEnabled;
	}
	public void setPCCAEnabled(boolean pCCAEnabled) {
		PCCAEnabled = pCCAEnabled;
	}
		
}

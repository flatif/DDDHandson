package dddhandson.domain.support;


public abstract class ConcurrencySafeEntity extends IdentifiedDomainObject {
	
	private Integer concurrencyVersion = 0;
	
	private transient boolean changed = false;
	
	protected ConcurrencySafeEntity() {
		super();
	}
	
	public Integer concurrencyVersion() {
		return this.concurrencyVersion;
	}
	
	public void stateChanged() {
		if (!changed) {
			changed = true;
			concurrencyVersion++;
		}
	}
	
	//TODO: A cosa serve il controllo manuale su un setter?
	
//    public void setConcurrencyVersion(int aVersion) {
//        this.failWhenConcurrencyViolation(aVersion);
//        this.concurrencyVersion = aVersion;
//    }
//
//    public void failWhenConcurrencyViolation(int aVersion) {
//        if (aVersion != this.concurrencyVersion()) {
//            throw new IllegalStateException(
//                    "Concurrency Violation: Stale data detected. Entity was already modified.");
//        }
//    }
}

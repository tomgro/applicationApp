package pl.application.comm;

/**
 *
 * @author tomek
 */
public enum ApplicationState {
    CREATED("CREATED"),
    DELETED("DELETED"),
    VERIFIED("VERIFIED"),
    REJECTED("REJECTED"),
    ACCEPTED("ACCEPTED"),
    PUBLISHED("PUBLISHED");
    
    private final String name;
    
    private ApplicationState(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}

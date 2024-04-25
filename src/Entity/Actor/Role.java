package Entity.Actor;

/**
 * Enumerates the various roles that an individual can hold within the system.
 * Each role represents a set of permissions and responsibilities unique to that position.
 * <p>
 * Roles include:
 * <ul>
 *     <li>{@code CUSTOMER} - Represents a customer, typically someone who uses the services provided by the organization.</li>
 *     <li>{@code STAFF} - Represents a basic employee within the organization.</li>
 *     <li>{@code MANAGER} - Represents a managerial position with authority over certain areas or functions.</li>
 *     <li>{@code ADMIN} - Represents an administrator with high-level control over system settings and configurations.</li>
 *     <li>{@code PERSON} - A generic role for ordinary people who might not fit into other specific categories.</li>
 * </ul>
 */
public enum Role {
    CUSTOMER,
    STAFF,
    MANAGER,
    ADMIN,
    PERSON
}

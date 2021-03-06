package ro.teamnet.bootstrap.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Clasa reprezinta functionalitatile comune pentru toate strategiile de securitate.
 * Este utlizatata atat de modulul de securitate prin ApplicationRole cat si prin Function din modulu de organizational Unit
 */
@Entity
@Table(name = "T_ROLE")
@DiscriminatorColumn(name = "ROLE_TYPE",discriminatorType = DiscriminatorType.STRING)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public abstract class RoleBase extends AbstractAuditingEntity implements Serializable, GrantedAuthority {

    @Id
    @Column(name = "ID_ROLE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;



    @NotNull
    @Column(name = "CODE", length = 100, unique = true)
    protected String code;

    @Column(name = "DESCRIPTION")
    protected String description;


    @NotNull
    @Column(name = "VALID_FROM")
    @Temporal(TemporalType.DATE)
    protected Date validFrom;

    @NotNull
    @Column(name = "VALID_TO")
    @Temporal(TemporalType.DATE)
    protected Date validTo;

    @NotNull @Column(name = "IS_ACTIVE")
    protected Boolean active;


    @ManyToMany
    @JoinTable(
            name = "T_ROLE_MODULE_RIGHTS",
            joinColumns = {@JoinColumn(name = "fk_role", referencedColumnName = "id_role")},
            inverseJoinColumns = {@JoinColumn(name = "fk_module_right", referencedColumnName = "id_module_right")})
    private Set<ModuleRight> moduleRights = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }



    public Set<ModuleRight> getModuleRights() {
        return moduleRights;
    }

    public void setModuleRights(Collection<ModuleRight> moduleRights) {
        this.moduleRights.clear();
        this.moduleRights.addAll(moduleRights);
    }

    /* (non-Javadoc)
                  * @see org.springframework.security.core.GrantedAuthority#getAuthority()
                  */
    @Override
    @Transient
    public String getAuthority() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RoleBase applicationRole = (RoleBase) o;

        if (code != null ? !code.equals(applicationRole.code) : applicationRole.code != null) return false;
        if (description != null ? !description.equals(applicationRole.description) : applicationRole.description != null) return false;
        if (validFrom != null ? !validFrom.equals(applicationRole.validFrom) : applicationRole.validFrom != null) return false;
        if (validTo != null ? !validTo.equals(applicationRole.validTo) : applicationRole.validTo != null) return false;
        if (active != null ? !active.equals(applicationRole.active) : applicationRole.active != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (validFrom != null ? validFrom.hashCode() : 0);
        result = 31 * result + (validTo != null ? validTo.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "Role{" +
                "id='+" + id + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", validFrom='" + validFrom + '\'' +
                ", validTo='" + validTo + '\'' +
                ", active='" + active + '\'' +
                "}";
    }
}

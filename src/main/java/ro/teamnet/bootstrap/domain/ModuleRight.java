package ro.teamnet.bootstrap.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.security.core.GrantedAuthority;
import ro.teamnet.bootstrap.domain.util.ModuleRightTypeEnum;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A ModuleRight. This entity is used as a {@link GrantedAuthority} and represents a permission.
 */
@Entity
@Table(name = "T_MODULE_RIGHT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ModuleRight implements Serializable, GrantedAuthority {

    @Id
    @Column(name = "ID_MODULE_RIGHT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

//    @NotNull
    @Column(name = "VERSION")
    private Long version;

    @Column( name = "MODULE_RIGHT" )
    private Short right;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "FK_MODULE", updatable = true, insertable = true)
    @JsonBackReference
    private Module module;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Short getRight() {
        return right;
    }

    public void setRight(Short right) {
        this.right = right;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public String getModuleRightCode(){
        return ModuleRightTypeEnum.getCodeByValue(getRight());
    }

    /* (non-Javadoc)
           * @see org.springframework.security.core.GrantedAuthority#getAuthority()
       */
    @Override
    @Transient
    public String getAuthority() { return ModuleRightTypeEnum.getCodeByValue(getRight()) + '_' + module.getCode(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleRight that = (ModuleRight) o;

        if (module != null ? !module.equals(that.module) : that.module != null) return false;
        if (!right.equals(that.right)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = right.hashCode();
        result = 31 * result + (module != null ? module.hashCode() : 0);
        return result;
    }
}

package ro.teamnet.bootstrap.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Clasa incapsuleaza proprietatile unui utlizator al platformei.
 */
@Entity
@Table(name = "T_ACCOUNT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name = "ID_ACCOUNT")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 0, max = 50)
    @Column(length = 50, unique = true)
    private String login;

    @JsonIgnore
    @Size(min = 0, max = 100)
    @Column(name="password", length = 100)
    private String password;

    @Size(min = 0, max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(min = 0, max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Email
    @Size(min = 0, max = 100)
    @Column(length = 100, unique = true)
    private String email;

    @Column(name = "IS_ACTIVE")
    private boolean activated = true;

    @Size(min = 2, max = 5)
    @Column(name = "lang_key", length = 5)
    private String langKey;

    @Size(min = 0, max = 20)
    @Column(name = "activation_key", length = 20)
    private String activationKey;


    @Size(min = 0, max = 20)
    @Column(name = "gender", length = 20)
    private String gender;

    @ManyToMany
    @JoinTable(
            name = "T_ACCOUNT_ROLES",
            joinColumns = {@JoinColumn(name = "fk_account", referencedColumnName = "id_account")},
            inverseJoinColumns = {@JoinColumn(name = "fk_role", referencedColumnName = "id_role")})
    private Set<RoleBase> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "T_ACCOUNT_MODULE_RIGHTS",
            joinColumns = {@JoinColumn(name = "fk_account", referencedColumnName = "id_account")},
            inverseJoinColumns = {@JoinColumn(name = "fk_module_right", referencedColumnName = "id_module_right")})
    private Set<ModuleRight> moduleRights = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "account")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PersistentToken> persistentTokens = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<RoleBase> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleBase> roles) {
        this.roles = roles;
    }

    public Set<ModuleRight> getModuleRights() {
        return moduleRights;
    }

    public void setModuleRights(Set<ModuleRight> moduleRights) {
        this.moduleRights = moduleRights;
    }

    public Set<PersistentToken> getPersistentTokens() {
        return persistentTokens;
    }

    public void setPersistentTokens(Set<PersistentToken> persistentTokens) {
        this.persistentTokens = persistentTokens;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account Account = (Account) o;

        if (!login.equals(Account.login)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", activated='" + activated + '\'' +
                ", langKey='" + langKey + '\'' +
                ", activationKey='" + activationKey + '\'' +
                "}";
    }

}

package org.example.sweater.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) //Допомагає зформувати нову таблицю для зберігання Enum,
                                                                          // targetClass - вказує, що саме зберігаємо,
                                                                          // fetch - як саме будуть завантажуватись дані (жадний та лінивий спосіб завантаження, лінивий дял великої кількості записів)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id")) //Описує, що дане поле буде зберігатись в окремій таблиці,
    // для якої не описувався меппінг. Це дозволяє на створити таблицю user_role, яка буде з'єднуватись з поточною по колонці user_id
    @Enumerated(EnumType.STRING)//enum
    private Set<Role> roles; //Набір ролей користувача

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

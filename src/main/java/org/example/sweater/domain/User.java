package org.example.sweater.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
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
    @Enumerated(EnumType.STRING)//
    private Set<Role> roles; //Набір ролей користувача
}

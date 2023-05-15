package pro.sky.diplom.entity;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Класс сущности "Пользователь"
 *
 * @author Одокиенко Екатерина
 */

@Entity
@Data
@Table(name = "users")

public class User {
    /**
     * поле - айди пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    /**
     * поле - имя пользователя
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * поле - фамилия пользователя
     */
    @Column(name = "last_name")
    private String lastName;
    /**
     * поле - email пользователя
     */
    @Column(name = "email")
    private String email;
    /**
     * поле - пароль пользователя
     */
    @Column(name = "password")
    private String password;
    /**
     * поле - номер телефона пользователя
     */
    @Column(name = "phone")
    private String phone;

    /**
     * поле - объект сущности "Avatar"
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
    /**
     * поле - тип пользователя - его роль
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;


package pro.sky.diplom.entity;


import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Класс сущности "Avatar"
 *
 * @author Одокиенко Екатерина
 */
@Entity
@Data
@Table(name = "avatar")
public class Avatar {
    /**
     * поле - айди аватара
     */
    @Id
    @Column(name = "avatar_id")
    private String id;
    /**
     * поле - тип дата-данных аватара
     */
    @Column(name = "image")
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

    private static String test;
}
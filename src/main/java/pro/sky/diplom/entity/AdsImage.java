package pro.sky.diplom.entity;


import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Класс сущности "AdsImage"
 *
 * @author Одокиенко Екатерина
 */
@Entity
@Data
@Table(name = "ads_image")
public class AdsImage {
    /**
     * поле - айди картинки
     */
    @Id
    @Column(name = "ads_image_id")
    private String id;
    /**
     * поле - тип дата-данных картинки
     */
    @Lob
    @Column(name = "image")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] data;

}


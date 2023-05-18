package pro.sky.diplom.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс сущности "Объявление"
 *
 * @author Одокиенко Екатерина
 */
@Entity
@Data
@Table(name = "ads")
public class Ads {
    /**
     * поле - айди объявления
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ads_id")
    private Integer id;
    /**
     * поле - заголовок объявления
     */
    @Column(name = "title")
    private String title;
    /**
     * поле - описание объявления
     */
    @Column(name = "description")
    private String description;
    /**
     * поле - цена в объявлении
     */
    @Column(name = "price")
    private int price;
    /**
     * поле - автор объявления
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    /**
     * поле - фото в объявлении
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ads_image_id")
    private AdsImage adsImage;

}


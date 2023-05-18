package pro.sky.diplom.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс сущности "Комментарий"
 *
 * @author Одокиенко Екатерина
 */

@Data
@Entity
@Table(name = "comment")
public class Comment {
    /**
     * поле - айди комментария
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;
    /**
     * поле - время создания комментария
     */
    @Column(name = "date")
    private LocalDateTime createdAt;
    /**
     * поле - текст комментария
     */
    @Column(name = "text")
    private String text;
    /**
     * поле - автор комментария
     */
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
    /**
     * поле - объект сущности "Объявление"
     */
    @ManyToOne
    @JoinColumn(name = "ads_id")
    private Ads ads;
}


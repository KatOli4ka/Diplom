package pro.sky.diplom.dto;

import lombok.Data;

/**
 * Класс, описывающий параметры объявлений
 *
 * @author Одокиенко Екатерина
 */
@Data
public class AdsDto {
    /**id объявления**/
    private int pk;

    /**id автора объявления**/
    private int author;

    /**ссылка на картинку объявления**/
    private String image;

    /**цена объявления**/
    private int price;

    /**заголовок объявления**/
    private String title;

}

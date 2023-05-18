package pro.sky.diplom.dto;

import lombok.Data;

/**
 * Класс, описывающий параметры создания объявления
 *
 * @author Одокиенко Екатерина
 */
@Data
public class CreateAdsDto {

    /**описание объявления**/
    private String description;

    /**цена объявления**/
    private int price;

    /**заголовок объявления**/
    private String title;
}
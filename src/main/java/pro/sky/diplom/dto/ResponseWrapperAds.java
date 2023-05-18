package pro.sky.diplom.dto;

import lombok.Data;

import java.util.Collection;

/**
 * Класс обертки ответа для объекта "Объявление"
 *
 * @author Одокиенко Екатерина
 */
@Data
public class ResponseWrapperAds {
    /**
     * поле - количество объявлений
     */
    private int count;
    /**
     * поле - коллекция объявлений
     */
    private Collection<AdsDto> results;


}
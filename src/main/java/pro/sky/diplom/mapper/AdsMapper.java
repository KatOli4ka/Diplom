package pro.sky.diplom.mapper;

import pro.sky.diplom.dto.AdsDto;
import pro.sky.diplom.dto.CreateAdsDto;
import pro.sky.diplom.dto.FullAdsDto;
import pro.sky.diplom.entity.Ads;

import java.util.Collection;

/**
 * Интерфейс AdsMapper для класса Ads
 *
 * @author Одокиенко Екатерина
 */
public interface AdsMapper {
    AdsDto toDto(Ads entity);
    Ads fromDto(AdsDto dto);
    FullAdsDto toFullAdsDto(Ads entity);
    Ads fromDto(CreateAdsDto dto);
    Collection<AdsDto> toDto(Collection<Ads> adsCollection);

}
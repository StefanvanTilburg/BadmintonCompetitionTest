package nl.badminton.competition.badminton.competition.converter;

import org.modelmapper.PropertyMap;
import java.util.List;

public interface SuperConverter<A, B> {
    PropertyMap<A, B> getConversionMap();
    List<B> convertToListDto(final List<A> input);
    List<A> convertToListEntity(final List<B> input);
    B convertToDto(A input);
    A convertToEntity(B input);
}

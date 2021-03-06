package ro.teamnet.bootstrap.converter;

import org.springframework.stereotype.Component;

/**
 * Convertor din Boolean la sString si invers
 */
@Component
public class BooleanConverter extends BaseConverter<Boolean> {
    @Override
    public ConverterType type() {
        return ConverterType.BOOLEAN;
    }

    @Override
    public Boolean from(String someStringValue) {
        if(someStringValue==null)
            return null;
        return Boolean.getBoolean(someStringValue);


    }

}

package com.shop.helper;

import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vazgen on 08/04/2016.
 */
public class DozerExtension {

    public static <T, U> ArrayList<U> map(final Mapper mapper, final List<T> source, final Class<U> destType) {

        final ArrayList<U> dest = new ArrayList<U>();

        for (T element : source) {
            if (element == null) {
                continue;
            }
            dest.add(mapper.map(element, destType));
        }

        // finally remove all null values if any
        List s1 = new ArrayList();
        s1.add(null);
        dest.removeAll(s1);

        return dest;
    }
}

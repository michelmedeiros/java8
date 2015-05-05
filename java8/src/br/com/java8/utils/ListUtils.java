package br.com.java8.utils;

import java.util.List;

import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;

public class ListUtils {

    @SuppressWarnings("unchecked")
    public static <P, T> List<P> extrairListaPropriedades(List<T> lista, String propriedade,
            boolean ignoreNull) {

    	 List<P> retorno =
               (List<P>) CollectionUtils.collect(lista, new BeanToPropertyValueTransformer(propriedade, ignoreNull));
    	 
        // Remove os elementos nulos da lista.
        CollectionUtils.filter(retorno, PredicateUtils.notNullPredicate());
        return retorno;
    }
        
}

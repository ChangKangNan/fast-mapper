package cn.ft.ckn.fastmapper.component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * bean property.
 *
 * @author ckn
 */
public class BeanProperty {
    Method getter;
    Method setter;
    Class<?> propertyType;
    String propertyName;
    String columnName;

    public BeanProperty(PropertyDescriptor pd) {
        this.getter = pd.getReadMethod();
        this.setter = pd.getWriteMethod();
        this.propertyType = pd.getReadMethod().getReturnType();
        this.propertyName = pd.getName();
    }
}

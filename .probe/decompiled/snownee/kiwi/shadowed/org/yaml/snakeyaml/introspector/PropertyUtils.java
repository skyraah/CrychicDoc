package snownee.kiwi.shadowed.org.yaml.snakeyaml.introspector;

import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import snownee.kiwi.shadowed.org.yaml.snakeyaml.error.YAMLException;
import snownee.kiwi.shadowed.org.yaml.snakeyaml.util.PlatformFeatureDetector;

public class PropertyUtils {

    private final Map<Class<?>, Map<String, Property>> propertiesCache = new HashMap();

    private final Map<Class<?>, Set<Property>> readableProperties = new HashMap();

    private BeanAccess beanAccess = BeanAccess.DEFAULT;

    private boolean allowReadOnlyProperties = false;

    private boolean skipMissingProperties = false;

    private final PlatformFeatureDetector platformFeatureDetector;

    private static final String TRANSIENT = "transient";

    public PropertyUtils() {
        this(new PlatformFeatureDetector());
    }

    PropertyUtils(PlatformFeatureDetector platformFeatureDetector) {
        this.platformFeatureDetector = platformFeatureDetector;
        if (platformFeatureDetector.isRunningOnAndroid()) {
            this.beanAccess = BeanAccess.FIELD;
        }
    }

    protected Map<String, Property> getPropertiesMap(Class<?> type, BeanAccess bAccess) {
        if (this.propertiesCache.containsKey(type)) {
            return (Map<String, Property>) this.propertiesCache.get(type);
        } else {
            Map<String, Property> properties = new LinkedHashMap();
            boolean inaccessableFieldsExist = false;
            if (bAccess == BeanAccess.FIELD) {
                for (Class<?> c = type; c != null; c = c.getSuperclass()) {
                    for (Field field : c.getDeclaredFields()) {
                        int modifiers = field.getModifiers();
                        if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers) && !properties.containsKey(field.getName())) {
                            properties.put(field.getName(), new FieldProperty(field));
                        }
                    }
                }
            } else {
                try {
                    for (PropertyDescriptor property : Introspector.getBeanInfo(type).getPropertyDescriptors()) {
                        Method readMethod = property.getReadMethod();
                        if ((readMethod == null || !readMethod.getName().equals("getClass")) && !this.isTransient(property)) {
                            properties.put(property.getName(), new MethodProperty(property));
                        }
                    }
                } catch (IntrospectionException var11) {
                    throw new YAMLException(var11);
                }
                for (Class<?> c = type; c != null; c = c.getSuperclass()) {
                    for (Field fieldx : c.getDeclaredFields()) {
                        int modifiers = fieldx.getModifiers();
                        if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                            if (Modifier.isPublic(modifiers)) {
                                properties.put(fieldx.getName(), new FieldProperty(fieldx));
                            } else {
                                inaccessableFieldsExist = true;
                            }
                        }
                    }
                }
            }
            if (properties.isEmpty() && inaccessableFieldsExist) {
                throw new YAMLException("No JavaBean properties found in " + type.getName());
            } else {
                this.propertiesCache.put(type, properties);
                return properties;
            }
        }
    }

    private boolean isTransient(FeatureDescriptor fd) {
        return Boolean.TRUE.equals(fd.getValue("transient"));
    }

    public Set<Property> getProperties(Class<? extends Object> type) {
        return this.getProperties(type, this.beanAccess);
    }

    public Set<Property> getProperties(Class<? extends Object> type, BeanAccess bAccess) {
        if (this.readableProperties.containsKey(type)) {
            return (Set<Property>) this.readableProperties.get(type);
        } else {
            Set<Property> properties = this.createPropertySet(type, bAccess);
            this.readableProperties.put(type, properties);
            return properties;
        }
    }

    protected Set<Property> createPropertySet(Class<? extends Object> type, BeanAccess bAccess) {
        Set<Property> properties = new TreeSet();
        for (Property property : this.getPropertiesMap(type, bAccess).values()) {
            if (property.isReadable() && (this.allowReadOnlyProperties || property.isWritable())) {
                properties.add(property);
            }
        }
        return properties;
    }

    public Property getProperty(Class<? extends Object> type, String name) {
        return this.getProperty(type, name, this.beanAccess);
    }

    public Property getProperty(Class<? extends Object> type, String name, BeanAccess bAccess) {
        Map<String, Property> properties = this.getPropertiesMap(type, bAccess);
        Property property = (Property) properties.get(name);
        if (property == null && this.skipMissingProperties) {
            property = new MissingProperty(name);
        }
        if (property == null) {
            throw new YAMLException("Unable to find property '" + name + "' on class: " + type.getName());
        } else {
            return property;
        }
    }

    public void setBeanAccess(BeanAccess beanAccess) {
        if (this.platformFeatureDetector.isRunningOnAndroid() && beanAccess != BeanAccess.FIELD) {
            throw new IllegalArgumentException("JVM is Android - only BeanAccess.FIELD is available");
        } else {
            if (this.beanAccess != beanAccess) {
                this.beanAccess = beanAccess;
                this.propertiesCache.clear();
                this.readableProperties.clear();
            }
        }
    }

    public void setAllowReadOnlyProperties(boolean allowReadOnlyProperties) {
        if (this.allowReadOnlyProperties != allowReadOnlyProperties) {
            this.allowReadOnlyProperties = allowReadOnlyProperties;
            this.readableProperties.clear();
        }
    }

    public boolean isAllowReadOnlyProperties() {
        return this.allowReadOnlyProperties;
    }

    public void setSkipMissingProperties(boolean skipMissingProperties) {
        if (this.skipMissingProperties != skipMissingProperties) {
            this.skipMissingProperties = skipMissingProperties;
            this.readableProperties.clear();
        }
    }

    public boolean isSkipMissingProperties() {
        return this.skipMissingProperties;
    }
}
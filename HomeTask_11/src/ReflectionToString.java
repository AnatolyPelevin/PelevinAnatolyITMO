import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;

public class  ReflectionToString<T>  {
    private T currentObject;
    private Class<?> currentClass;

    public ReflectionToString (T currentObject){
        setCurrentObject(currentObject);
        currentClass  = currentObject.getClass();
    }

    public void setCurrentObject(T currentObject) {
        this.currentObject = currentObject;
    }


    public String getObjectInfo() throws IllegalAccessException {
        return getObjectInfo(true, true,true, true, true, true, true);
    }

    public String getObjectInfo(boolean pack, boolean inter, boolean constr
            , boolean publicFields, boolean privateFields
            , boolean publicMethods, boolean privateMethods) throws IllegalAccessException {

        if (currentObject.getClass().isPrimitive()){
            return "Class: " + currentClass + " is a primitive type with value = " + currentObject;
        } else if (currentObject.getClass().isArray()){
            return "Class: " + currentClass + " is array.";
        }
        return prepareName( pack, inter, constr, publicFields, privateFields, publicMethods, privateMethods);
    }

    private String  prepareName(boolean pack, boolean inter, boolean constr
                           , boolean publicFields, boolean privateFields
                           , boolean publicMethods, boolean privateMethods) throws IllegalAccessException {

        StringBuilder stringBuilder = new StringBuilder();
        String simpleClassName = currentClass.getSimpleName();
        String classModifier = getClassModifierName();

        stringBuilder.append("[CLASS] : ")
                .append(classModifier)
                .append(" ")
                .append(simpleClassName)
                .append(";")
                .append(System.lineSeparator());

        if (pack){
            String packName =  getPackage();
            stringBuilder.append(packName);

        }

        if (inter){
            String interfacesName =  getInterfaces();
            stringBuilder.append("Class emplements interfaces: ")
                    .append(System.lineSeparator())
                    .append("       ")
                    .append(interfacesName);

        }

        if(constr){
            String constructorsName =  getConstructors();
            stringBuilder.append("Class's constructors: ")
                    .append(System.lineSeparator())
                    .append(constructorsName);
        }

        if(publicFields){
            String fieldsNamePublic =  getPublicFields();
            stringBuilder.append("Class has public fields: ")
                    .append(System.lineSeparator())
                    .append(fieldsNamePublic);
        }
        if(privateFields){
            String fieldsNamePrivate =  getPrivateFields();
            stringBuilder.append("Class has private fields: ")
                    .append(System.lineSeparator())
                    .append(fieldsNamePrivate);
        }
        if(publicMethods){
            String methodsNamePublic =  getPublicMethods();
            stringBuilder.append("Class has public methods: ")
                    .append(System.lineSeparator())
                      .append(methodsNamePublic);
        }
        if(privateMethods){
            String methodsNamePrivate =  getPrivateMethods() ;
            stringBuilder.append("Class has private methods: ")
                    .append(System.lineSeparator())
                    .append(methodsNamePrivate);
        }

        return stringBuilder.toString();
    }

    private String getClassModifierName(){
        int classModifier = currentClass.getModifiers();

        String result = "";

        if (Modifier.isPublic(classModifier)){
            result+="public ";
        }
        if (Modifier.isPrivate(classModifier)){
            result+="private ";
        }
        if (Modifier.isProtected(classModifier)){
            result+="protected ";
        }
        if(Modifier.isAbstract(classModifier)){
            result+="abstract ";
        }
        if(Modifier.isFinal(classModifier)){
            result+="final ";
        }
        if(Modifier.isStatic(classModifier)){
            result+="static ";
        }
        if(Modifier.isInterface(classModifier)){
            result+="interface ";
        }
        if(Modifier.isNative(classModifier)){
            result+="native ";
        }
        return result;
    }

    private String getPackage(){
        Package aPackage= currentClass.getPackage();
        return "Class package: " + aPackage + System.lineSeparator();
    }

    private String getInterfaces() {
        Class[] interfaces = currentClass.getInterfaces();
        if (interfaces == null || interfaces.length == 0) {
            return "Class doesn't implements any interfaces." + System.lineSeparator();
        }
        return "Class interfaces: " + Arrays.toString(interfaces);
    }

    private String getConstructors(){
        Constructor[] constructors = currentClass.getConstructors();
        if (constructors == null || constructors.length == 0) {
            return "Class doesn't have constructors." + System.lineSeparator();
        }
        String result = "";
        for(Constructor constructor:constructors){
            result += "       Constructor: " + constructor.getName();
            Class[] paramTypes = constructor.getParameterTypes();
            if (paramTypes != null || paramTypes.length != 0) {
                result+= " (" + Arrays.toString(paramTypes) + ");";
            }
            result+=System.lineSeparator();
        }
        return result;
    }


    private String getPublicFields() throws IllegalAccessException{
        Field[] fields = currentClass.getFields();
        return  getFields(fields);
    }

    private String getPrivateFields() throws IllegalAccessException{
        Field[] fields = currentClass.getDeclaredFields();
        Field[] fieldsExclude = Arrays.asList(fields).stream().filter(field -> excludePublicField(field)).toArray(Field[]::new);
        return  getFields(fieldsExclude);
    }

    private boolean excludePublicField(Field field){
        field.setAccessible(true);
        int modifier = field.getModifiers();
        field.setAccessible(false);
         if (Modifier.isPublic(modifier)){
             return false;
         } else {
             return true;
         }

    }

    private String getFields(Field[] fields) throws IllegalAccessException {
        if (fields == null || fields.length == 0) {
            return "Class doesn't have any fields." + System.lineSeparator();
        }
        String result = "";

        for(Field field: fields){
            field.setAccessible(true);
            ExcludeFieldAnnotation excludeFieldAnnotation = field.getAnnotation(ExcludeFieldAnnotation.class);
            if(excludeFieldAnnotation  != null){
                continue;
            }
            String fieldName = field.getName();
            Object fieldType = field.getType();
            Object val = field.get((Object)currentObject);
            field.setAccessible(false);
            result += "       Field: " + fieldType + " " + fieldName + " has value = [ " + val +" ] "+ System.lineSeparator();
        }
        return result;
    }

    private String getPublicMethods() throws IllegalAccessException{
        Method[] methods = currentClass.getMethods();
        return  getMethods(methods);
    }

    private String getPrivateMethods() throws IllegalAccessException{
        Method[] methods = currentClass.getDeclaredMethods();
        return  getMethods(methods);
    }

    private String getMethods(Method[] methods ) throws IllegalAccessException {
        if (methods == null || methods.length == 0) {
            return "Class doesn't have any methods." + System.lineSeparator();
        }
        String result = "";
        for(Method method: methods){
            method.setAccessible(true);
            String methodName = method.getName();
            Class returnType = method.getReturnType();
            method.setAccessible(false);
            result += "       Method: " + methodName + " returns " + returnType + System.lineSeparator();
        }
        return result;
    }
}

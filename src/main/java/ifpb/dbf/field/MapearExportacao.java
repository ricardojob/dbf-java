package ifpb.dbf.field;

import com.linuxense.javadbf.DBFField;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo Job
 */
public class MapearExportacao {

    public List<FieldDescriptor> mapear(Class classe) {
        List<FieldDescriptor> mapa = new ArrayList<>();
        for (Field field : classe.getDeclaredFields()) {
            FieldDescriptor campo = converteCampo(field);
            mapa.add(campo);
        }
        return mapa;
    }

    private byte converteTipoDBF(Class classe) {

        if (classe == String.class) {
            return DBFField.FIELD_TYPE_C;
        }

        if (classe == java.util.Date.class) {
            return DBFField.FIELD_TYPE_D;
        }

        if (classe == Boolean.class || classe == boolean.class) {
            return DBFField.FIELD_TYPE_L;
        }

        if (classe == int.class || classe == Integer.class) {
            return DBFField.FIELD_TYPE_N;
        }
        if (classe == long.class || classe == Long.class) {
            return DBFField.FIELD_TYPE_N;
        }
        if (classe == float.class || classe == Float.class) {
            return DBFField.FIELD_TYPE_F;
        }
        if (classe == double.class || classe == Double.class) {
            return DBFField.FIELD_TYPE_F;
        }



        
        return 0;
    }

    private FieldDescriptor converteCampo(Field field) {
        FieldDescriptor campo = new FieldDescriptor();
        campo.setNome(field.getName());
        campo.setTipoDBF(converteTipoDBF(field.getType()));
        return campo;
    }
}

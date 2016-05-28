package ifpb.dbf.loader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFWriter;
import ifpb.dbf.Exportacao;
import ifpb.dbf.field.MapearExportacao;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo Job
 */
public class ExportClasseExportar {

    public static List<ClasseExportar> listar() {
        List<ClasseExportar> lista = new ArrayList<>();
        ClasseExportar admin = new ClasseExportar(1.0);
        admin.setLogin("a");
        admin.setSenha("b");
        
        lista.add(admin);
        admin = new ClasseExportar(2.0);
        admin.setLogin("2");
        admin.setSenha("2");
        lista.add(admin);
        admin = new ClasseExportar(3.0);
        admin.setLogin("3");
        admin.setSenha("3");
        lista.add(admin);
        return lista;

    }

    public static void main(String args[]) {
        try {
            Exportacao exportar = new Exportacao(ClasseExportar.class);
            MapearExportacao mapa = new MapearExportacao();
            exportar.setCampos(mapa.mapear(ClasseExportar.class));
//            List<FieldDescriptor> f = mapa.mapear(ClasseExportar.class);
//            for (FieldDescriptor fieldDescriptor : f) {
//                System.out.println(fieldDescriptor.getNome());
//            }
            exportar.setValores(listar());
            exportar.exportar("ClasseExportar.dbf");
//            Class<ClasseExportar> classe = ClasseExportar.class;
//            Field[] campos = classe.getDeclaredFields();
//            for (Field field : campos) {
//                System.out.println("Nome: " + field.getName());
////                System.out.println("Tipo: " + field.getType().isPrimitive());
//                System.out.println("Retorno: " + converteTipoDBF(field.getType()));
//            }

//            exportar();
//            ler();
        } catch (Exception ex) {
            Logger.getLogger(ExportClasseExportar.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private static void exportar() throws IOException {

        // let us create field definitions first
        // we will go for 3 fields
        //
        DBFField fields[] = new DBFField[3];

        fields[0] = new DBFField();
        fields[0].setName("emp_code");
        fields[0].setDataType(DBFField.FIELD_TYPE_C);
        fields[0].setFieldLength(10);

        fields[1] = new DBFField();
        fields[1].setName("emp_name");
        fields[1].setDataType(DBFField.FIELD_TYPE_C);
        fields[1].setFieldLength(20);

        fields[2] = new DBFField();
        fields[2].setName("salary");
        fields[2].setDataType(DBFField.FIELD_TYPE_F);
        fields[2].setFieldLength(12);
//        fields[2].setDecimalCount(1);

        DBFWriter writer = new DBFWriter();
        writer.setFields(fields);

        // now populate DBFWriter
        //

        Object rowData[] = new Object[3];
        rowData[0] = "1000";
        rowData[1] = "John";
        rowData[2] = new Double(5000.0);

        writer.addRecord(rowData);

        rowData = new Object[3];
        rowData[0] = "1001";
        rowData[1] = "Lalit";
        rowData[2] = new Double(3400.0);
//
        writer.addRecord(rowData);

        rowData = new Object[3];
        rowData[0] = "1002";
        rowData[1] = "Rohit";
        rowData[2] = new Double(7350.0);

        writer.addRecord(rowData);

        FileOutputStream fos = new FileOutputStream("E:/teste.dbf");
        writer.write(fos);
        fos.close();
    }

    private static void ler() throws Exception {
        // create a DBFReader object
        //
        InputStream inputStream = new FileInputStream("E:/teste.dbf"); // take dbf file as program argument
        DBFReader reader = new DBFReader(inputStream);

        // get the field count if you want for some reasons like the following
        //
        int numberOfFields = reader.getFieldCount();

        // use this count to fetch all field information
        // if required
        //
        for (int i = 0; i < numberOfFields; i++) {

            DBFField field = reader.getField(i);

            // do something with it if you want
            // refer the JavaDoc API reference for more details
            //
            //System.out.println(field.getName());
        }

        // Now, lets us start reading the rows
        //
        Object[] rowObjects;

//        reader.

        while ((rowObjects = reader.nextRecord()) != null) {

            for (int i = 0; i < rowObjects.length; i++) {
                DBFField campo = reader.getField(i);
                System.out.println(campo.getName() + " -> " + campo.getDataType() + "->" + rowObjects[i]);
            }
        }

        // By now, we have itereated through all of the rows

        inputStream.close();
    }

    private static byte converteTipoDBF(Class classe) {

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



        /*
         XBase Type	XBase Symbol	Java Type used in JavaDBF 
         Character	C java.lang.String 
         Numeric	N	java.lang.Double 
         Double	F	lava.lang.Double
         Logical	L	java.lang.Boolean 
         Date	D	java.util.Date
         */
        return 0;
    }
}

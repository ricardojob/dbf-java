/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dbf.loader;

import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;
import com.linuxense.javadbf.DBFWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo Job
 */
public class TesteExportAdmin {

    public static void main(String args[]) {
        try {
            exportar();
            ler();
        } catch (Exception ex) {
            Logger.getLogger(TesteExportAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private static void exportar() throws IOException {

        // let us create field definitions first
        // we will go for 3 fields
        //
        DBFField fields[] = new DBFField[3];

        fields[0] = new DBFField();
        fields[0].setFieldName("emp_code");
        fields[0].setDataType(DBFField.FIELD_TYPE_C);
        fields[0].setFieldLength(10);

        fields[1] = new DBFField();
        fields[1].setFieldName("emp_name");
        fields[1].setDataType(DBFField.FIELD_TYPE_C);
        fields[1].setFieldLength(20);

        fields[2] = new DBFField();
        fields[2].setFieldName("salary");
        fields[2].setDataType(DBFField.FIELD_TYPE_N);
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
        InputStream inputStream = new FileInputStream("/teste.dbf"); // take dbf file as program argument
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
                System.out.println(campo.getName() + "->" + rowObjects[i]);
            }
        }

        // By now, we have itereated through all of the rows

        inputStream.close();
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dbf;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;
import ifpb.dbf.field.FieldDescriptor;
import ifpb.dbf.field.FieldResolver;
import ifpb.dbf.field.MapearExportacao;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ricardo Job
 */
public class Exportacao {

    private List<FieldResolver> fieldResolvers = new ArrayList<>();
    private DBFWriter writer = new DBFWriter();
    private List<FieldDescriptor> campos;
    private Class classe;
    private MapearExportacao mapa = new MapearExportacao();

    public Exportacao() {
    }

    public Exportacao(Class classe) {
        this.classe = classe;
        campos = mapa.mapear(classe);

    }

    public void setCampos(List<FieldDescriptor> campos) throws DBFException {
        this.campos = campos;
        configurarCampos();
    }

    public void exportar(String arquivo) {
        try {
            FileOutputStream fos = new FileOutputStream(arquivo);
            writer.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setValores(List lista) throws Exception {
        for (Object admin : lista) {
            for (FieldDescriptor fieldDescriptor : campos) {
                configurarFieldResolver(fieldDescriptor, admin);
            }
            capturaValores();
        }
    }

    private void configurarFieldResolver(FieldDescriptor field, Object object) throws Exception {
        Field campo = classe.getDeclaredField(field.getNome());
        campo.setAccessible(true);
        Object valor = campo.get(object);
        fieldResolvers.add(configurarField(field, valor));
    }
//

    private FieldResolver configurarField(FieldDescriptor field, Object valor) {
        FieldResolver resolver = new FieldResolver();
        resolver.setNome(field.getNome());
        resolver.setIndex(field.getIndex());
        resolver.setValor(valor);
        return resolver;
    }

    private void configurarCampos() throws DBFException {
        DBFField[] fields = new DBFField[campos.size()];
        for (int i = 0; i < campos.size(); i++) {
            FieldDescriptor fieldDescriptor = campos.get(i);
            fieldDescriptor.setIndex(i);

            System.out.println(i + "->" + fieldDescriptor.getNome());
            fields[i] = fieldDescriptor.createDBF();
        }
        writer.setFields(fields);
        // return fields;
    }

    private void capturaValores() throws DBFException {
        Object[] valores = new Object[fieldResolvers.size()];
        for (FieldResolver fieldResolver : fieldResolvers) {
            valores[fieldResolver.getIndex()] = fieldResolver.getValor();
            System.out.println(fieldResolver.getIndex() + "->" + fieldResolver.getValor());
        }
        writer.addRecord(valores);
        fieldResolvers = new ArrayList<>();
    }
}

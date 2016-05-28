/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dbf.field;


import com.linuxense.javadbf.DBFField;

/**
 *
 * @author Ricardo Job
 */
public class FieldDescriptor {

    private String nome;
    private byte tipoDBF;
    private int index;

    public FieldDescriptor() {
    }

    public FieldDescriptor(String nome, byte tipoDBF) {
        this.nome = nome;
        this.tipoDBF = tipoDBF;
    }

    public FieldDescriptor(String nome, byte tipoDBF, int index) {
        this.nome = nome;
        this.tipoDBF = tipoDBF;
        this.index = index;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte getTipoDBF() {
        return tipoDBF;
    }

    public void setTipoDBF(byte tipoDBF) {
        this.tipoDBF = tipoDBF;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public DBFField createDBF() {
        DBFField campo = new DBFField();
        campo.setName(nome);
        campo.setDataType(tipoDBF);
        //Ver tamanho correto
        if (tipoDBF == DBFField.FIELD_TYPE_C) {
            campo.setFieldLength(100);
        }
        if (tipoDBF == DBFField.FIELD_TYPE_F || tipoDBF == DBFField.FIELD_TYPE_N) {
            campo.setFieldLength(20);
        }
        return campo;
    }
}

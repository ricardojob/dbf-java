/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dbf.field;

/**
 *
 * @author Ricardo Job
 */
public class FieldResolver {

    private String nome;
    private Object valor;
    private int index;

    public FieldResolver() {
    }

    public FieldResolver(String nome, Object valor, int index) {
        this.nome = nome;
        this.valor = valor;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    public Object[] toArray(){
        Object rowData[] = new Object[3];
        rowData[0] = "1000";
        rowData[1] = "John";
        rowData[2] = new Double(
                5000.0);
        return null;

    }
    
}

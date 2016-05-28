/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.dbf.loader;

import java.io.Serializable;

/**
 *
 * @author Ricardo Job
 */
public class ClasseExportar implements Serializable {

    private Double keyAdmin;
    private String senha;
    private String login;

    public ClasseExportar() {
    }

    public ClasseExportar(Double keyAdmin) {
        this.keyAdmin = keyAdmin;
    }

    public ClasseExportar(Double keyAdmin, String senha, String login) {
        this.keyAdmin = keyAdmin;
        this.senha = senha;
        this.login = login;
    }

    public Double getKeyAdmin() {
        return keyAdmin;
    }

    public void setKeyAdmin(Double keyAdmin) {
        this.keyAdmin = keyAdmin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (keyAdmin != null ? keyAdmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClasseExportar)) {
            return false;
        }
        ClasseExportar other = (ClasseExportar) object;
        if ((this.keyAdmin == null && other.keyAdmin != null) || (this.keyAdmin != null && !this.keyAdmin.equals(other.keyAdmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gaara.dominio.Admin[ keyAdmin=" + keyAdmin + " ]";
    }
}

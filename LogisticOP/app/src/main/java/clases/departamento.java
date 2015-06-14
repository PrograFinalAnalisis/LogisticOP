package clases;

import java.io.Serializable;

/**
 * Created by Programador on 05/03/2015.
 */
public class departamento implements Serializable {


    private int id_departamento;
    private String Class_FullName;


    public departamento(int id_departamento, String class_FullName) {
        super();
        this.id_departamento = id_departamento;
        this.Class_FullName = class_FullName;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return Class_FullName;
    }


    public String getClass_FullName() {
        return Class_FullName;
    }

    public void setClass_FullName(String class_FullName) {
        Class_FullName = class_FullName;
    }

    public int getId_departamento() {
        return id_departamento;
    }
    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }


}

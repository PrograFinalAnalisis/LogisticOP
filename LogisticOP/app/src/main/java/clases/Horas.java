package clases;

import java.io.Serializable;

/**
 * Created by Programador on 05/03/2015.
 */
public class Horas implements Serializable {
    private String horas;



    public Horas(String horas) {
        super();
        this.horas = horas;
    }




    @Override
    public String toString() {
        return horas                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ;
    }




    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }


}
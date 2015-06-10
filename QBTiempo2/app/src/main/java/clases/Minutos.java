package clases;

import java.io.Serializable;

/**
 * Created by Programador on 05/03/2015.
 */
public class Minutos  implements Serializable {
    private String minutos;



    public Minutos(String minutos) {
        super();
        this.minutos = minutos;
    }

    @Override
    public String toString() {
        return minutos;
    }

    public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }


}

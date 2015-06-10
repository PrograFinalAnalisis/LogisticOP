package clases;

import java.io.Serializable;

/**
 * Created by Programador on 05/03/2015.
 */
public class actividad  implements Serializable {

    private int id_actividad;
    private String ItemService_FullName;

    public actividad(int id_actividad,
                     String itemService_FullName) {
        super();
        this.id_actividad = id_actividad;
        this.ItemService_FullName = itemService_FullName;

    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ItemService_FullName;
    }



    public String getItemService_FullName() {
        return ItemService_FullName;
    }

    public void setItemService_FullName(String itemService_FullName) {
        ItemService_FullName = itemService_FullName;
    }

    public int getId_actividad() {
        return id_actividad;
    }
    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }


}
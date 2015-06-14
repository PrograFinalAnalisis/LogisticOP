package clases;

/**
 * Esta clase se usa para saber el id que se selcciono y una vez enviado a la base de mysql por borrarlo
 */
public class AEliminar {
    int pk;

    public AEliminar(int pk) {
        this.pk = pk;
    }

    public int getPk() {
        return pk;
    }

    @Override
    public String toString() {
        return Integer.toString(pk);
    }
}

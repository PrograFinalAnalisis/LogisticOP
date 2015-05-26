/**
 * Created by nelson on 26/05/15.
 */
public class Cliente {
    public int tiempoGastado;
    public int horaInicio;
    public int horaFinal;

    public Cliente(int tiempoGastado, int horaInicio, int horaFinal) {
        this.tiempoGastado = tiempoGastado;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public int getTiempoGastado() {
        return tiempoGastado;
    }

    public void setTiempoGastado(int tiempoGastado) {
        this.tiempoGastado = tiempoGastado;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }




}

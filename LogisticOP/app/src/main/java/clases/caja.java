package clases;

/**
 * Created by Soto on 14/06/2015.
 */
public class caja
{

    public int base;
    public int altura;
    public int profundidad;
    public Cliente cliente;
    public int volumen;
    public boolean seleccionada = false;
    public boolean guardada = false;


    public caja(int pProfundidad, int pAltura, int pBase, Cliente pCliente)
    {
        this.base = pBase;
        this.altura = pAltura;
        this.profundidad = pProfundidad;
        this.cliente = pCliente;
        this.volumen = base * altura * profundidad;
    }

    public void rotarCaja()
    {
        int baseTem = this.base;
        int alturaTem = this.altura;
        int profondudidadTem = this.profundidad;
        /////////////////////////////////////////
        this.base = profondudidadTem;
        this.altura = baseTem;
        this.profundidad = alturaTem;
    }



}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalanalisis;

/**
 *
 * @author Soto
 */
public class caja 
{
    
    public int base;
    public int altura;
    public int profundidad;
    public cliente cliente;
    public int volumen;
    public boolean seleccionada = false;
    public boolean guardada = false;
    
    
    public caja(int pProfundidad, int pAltura, int pBase, cliente pCliente)
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

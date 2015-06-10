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
    public int base = 0;
    public int altura = 0;
    public int profundidad = 0;
    
    public caja(int pBase, int pAltura, int pProfundidad)
    {
        this.base = pBase;
        this.altura = pAltura;
        this.profundidad = pProfundidad;
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

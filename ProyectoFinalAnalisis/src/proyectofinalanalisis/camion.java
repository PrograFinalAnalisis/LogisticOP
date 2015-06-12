/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalanalisis;

import java.util.ArrayList;

/**
 *
 * @author Soto
 */
public class camion 
{
    ///Variables Camion/////
    public int base;
    public int altura;
    public int profundidad;
    public int volumenTotal;
    public int volumenGastado;
    ////////////////////////////////////////////
    //Variables externas////
    public caja listaCajas[];
    public ArrayList <Integer> listaClientesAVisitar;
    
    
    public camion(int pProfundidad, int pAltura, int pBase)
    {
        this.base = pBase;
        this.altura = pAltura;
        this.profundidad = pProfundidad;
        
        this.volumenTotal = pBase * pAltura* pProfundidad;
        this.volumenGastado = 0;
        
        this.listaClientesAVisitar =  new ArrayList();
    }
    
    public void copiarListaCajas()
    {
        
    }
    
   
    public int getVolumenRestante()
    {
        return volumenTotal-volumenGastado;
    }
    
    public void meterCaja(caja pCaja)
    {
        boolean entro = false;
        for(int i = 0;i<4;i++)
        {
            if(pCaja.altura <= altura && pCaja.base <= base && pCaja.profundidad <= profundidad)
            {
                entro = true;
                break;
            }
            pCaja.rotarCaja();
        }
        if(entro)
        {
            volumenGastado += pCaja.volumen;
            pCaja.guardada = true;
            if(!listaClientesAVisitar.contains(pCaja.idCliente))
            {
                listaClientesAVisitar.add(pCaja.idCliente);
            }
        }
        else
        {
            System.out.println("La caja NO entra en el camion por sus dimensiones");
        }
    }
    
    public void imprimirClientesAVisitar()
    {
        for(int i=0;i<listaClientesAVisitar.size();i++)
        {
            System.out.println("Visita Cliente " + listaClientesAVisitar.get(i));
        }
    }
    
    
    
    
}

package clases;

import java.io.Serializable;

/**
 * Created by Programador on 05/03/2015.
 */
public class nomina implements Serializable {


    private int id_nomina;
    private String PayrollItemWage_FullName;


    public nomina(int id_nomina, String payrollItemWage_FullName) {
        super();
        this.id_nomina = id_nomina;
        this.PayrollItemWage_FullName = payrollItemWage_FullName;
    }


    @Override
    public String toString() {
        return  PayrollItemWage_FullName;
    }


    public int getId_nomina() {
        return id_nomina;
    }
    public void setId_nomina(int id_nomina) {
        this.id_nomina = id_nomina;
    }

    public String getPayrollItemWage_FullName() {
        return PayrollItemWage_FullName;
    }
    public void setPayrollItemWage_FullName(String payrollItemWage_FullName) {
        PayrollItemWage_FullName = payrollItemWage_FullName;
    }


}

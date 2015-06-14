package clases;
/**
 * para cargar todas las actividades registradas por el usuario
 */

public class CargarActividadesRegistradas {
    int BillableStatus;
    String Class_FullName;
    String CodigoAprovador;
    int CodigoCierre;
    int CodigoClase;
    int CodigoCliente;
    int CodigoDia;
    int CodigoEmpleado;
    int CodigoEstado;
    int CodigoEstadoRevision;
    int CodigoNomina;
    int CodigoRegistrador;
    int CodigoRevision;
    int CodigoServicio;
    int Completa;
    String Customer_FullName;
    String Descripcion;
    float Duracion;
    String EditSequence;
    String Fecha;
    String FechaAprobacion;
    String FechaCreacion;
    int Grupo;
    String Horas;
    String Id;
    String Linea;
    String ListId;
    String NombreActividad;
    String NombreEmpleado;
    int Paquete;
    String PayrollItemWage_FullName;
    String TxnID;
    int Idprim;

    public CargarActividadesRegistradas() {
    }

    public CargarActividadesRegistradas(int idprim, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, float paramFloat, String paramString15, String paramString16) {
        this.NombreEmpleado = paramString1;
        this.NombreActividad = paramString2;
        this.Horas = paramString3;
        this.Fecha = paramString4;
        this.Id = paramString5;
        this.Descripcion = paramString6;
        this.EditSequence = paramString7;
        this.ListId = paramString8;
        this.CodigoAprovador = paramString9;
        this.TxnID = paramString10;
        this.Customer_FullName = paramString11;
        this.Class_FullName = paramString12;
        this.PayrollItemWage_FullName = paramString13;
        this.Linea = paramString14;
        this.CodigoEmpleado = paramInt1;
        this.CodigoCliente = paramInt2;
        this.CodigoServicio = paramInt3;
        this.CodigoNomina = paramInt4;
        this.CodigoClase = paramInt5;
        this.CodigoEstado = paramInt6;
        this.CodigoDia = paramInt7;
        this.BillableStatus = paramInt8;
        this.Paquete = paramInt9;
        this.Grupo = paramInt10;
        this.CodigoCierre = paramInt11;
        this.CodigoEstadoRevision = paramInt12;
        this.Completa = paramInt13;
        this.CodigoRevision = paramInt14;
        this.CodigoRegistrador = paramInt15;
        this.Duracion = paramFloat;
        this.FechaCreacion = paramString15;
        this.FechaAprobacion = paramString16;
        Idprim = idprim;
    }

    public CargarActividadesRegistradas(String paramString1, String paramString2, String paramString3, String paramString4, String id, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, float paramFloat, String paramString15, String paramString16) {
        this.NombreEmpleado = paramString1;
        this.NombreActividad = paramString2;
        this.Horas = paramString3;
        this.Fecha = paramString4;
        this.Id = id;
        this.Descripcion = paramString6;
        this.EditSequence = paramString7;
        this.ListId = paramString8;
        this.CodigoAprovador = paramString9;
        this.TxnID = paramString10;
        this.Customer_FullName = paramString11;
        this.Class_FullName = paramString12;
        this.PayrollItemWage_FullName = paramString13;
        this.Linea = paramString14;
        this.CodigoEmpleado = paramInt1;
        this.CodigoCliente = paramInt2;
        this.CodigoServicio = paramInt3;
        this.CodigoNomina = paramInt4;
        this.CodigoClase = paramInt5;
        this.CodigoEstado = paramInt6;
        this.CodigoDia = paramInt7;
        this.BillableStatus = paramInt8;
        this.Paquete = paramInt9;
        this.Grupo = paramInt10;
        this.CodigoCierre = paramInt11;
        this.CodigoEstadoRevision = paramInt12;
        this.Completa = paramInt13;
        this.CodigoRevision = paramInt14;
        this.CodigoRegistrador = paramInt15;
        this.Duracion = paramFloat;
        this.FechaCreacion = paramString15;
        this.FechaAprobacion = paramString16;
    }

    public int getIdprim() {
        return Idprim;
    }

    public int getBillableStatus() {
        return BillableStatus;
    }

    public String getClass_FullName() {
        return Class_FullName;
    }

    public String getCodigoAprovador() {
        return CodigoAprovador;
    }

    public int getCodigoCierre() {
        return CodigoCierre;
    }

    public int getCodigoClase() {
        return CodigoClase;
    }

    public int getCodigoCliente() {
        return CodigoCliente;
    }

    public int getCodigoDia() {
        return CodigoDia;
    }

    public int getCodigoEmpleado() {
        return CodigoEmpleado;
    }

    public int getCodigoEstado() {
        return CodigoEstado;
    }

    public int getCodigoEstadoRevision() {
        return CodigoEstadoRevision;
    }

    public int getCodigoNomina() {
        return CodigoNomina;
    }

    public int getCodigoRegistrador() {
        return CodigoRegistrador;
    }

    public int getCodigoRevision() {
        return CodigoRevision;
    }

    public int getCodigoServicio() {
        return CodigoServicio;
    }

    public int getCompleta() {
        return Completa;
    }

    public String getCustomer_FullName() {
        return Customer_FullName;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public float getDuracion() {
        return Duracion;
    }

    public String getEditSequence() {
        return EditSequence;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getFechaAprobacion() {
        return FechaAprobacion;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public int getGrupo() {
        return Grupo;
    }

    public String getHoras() {
        return Horas;
    }

    public String getId() {
        return Id;
    }

    public String getLinea() {
        return Linea;
    }

    public String getListId() {
        return ListId;
    }

    public String getNombreActividad() {
        return NombreActividad;
    }

    public String getNombreEmpleado() {
        return NombreEmpleado;
    }

    public int getPaquete() {
        return Paquete;
    }

    public String getPayrollItemWage_FullName() {
        return PayrollItemWage_FullName;
    }

    public String getTxnID() {
        return TxnID;
    }

    public String toString() {

        return this.NombreEmpleado + '\'' + this.NombreActividad + '\'' + this.Horas + '\'' + this.Fecha;
    }
}
package clases;


public class ActividadesSeleccionadas
{
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
    public ActividadesSeleccionadas()
    {
    }

    public ActividadesSeleccionadas(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, float paramFloat, String paramString15, String paramString16)
    {
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
    }

    public ActividadesSeleccionadas(int idprim,String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, float paramFloat, String paramString15, String paramString16)
    {
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

    public int getIdprim() {
        return Idprim;
    }

    public int getBillableStatus()
    {
        return this.BillableStatus;
    }

    public String getClass_FullName()
    {
        return this.Class_FullName;
    }

    public String getCodigoAprovador()
    {
        return this.CodigoAprovador;
    }

    public int getCodigoCierre()
    {
        return this.CodigoCierre;
    }

    public int getCodigoClase()
    {
        return this.CodigoClase;
    }

    public int getCodigoCliente()
    {
        return this.CodigoCliente;
    }

    public int getCodigoDia()
    {
        return this.CodigoDia;
    }

    public int getCodigoEmpleado()
    {
        return this.CodigoEmpleado;
    }

    public int getCodigoEstado()
    {
        return this.CodigoEstado;
    }

    public int getCodigoEstadoRevision()
    {
        return this.CodigoEstadoRevision;
    }

    public int getCodigoNomina()
    {
        return this.CodigoNomina;
    }

    public int getCodigoRegistrador()
    {
        return this.CodigoRegistrador;
    }

    public int getCodigoRevision()
    {
        return this.CodigoRevision;
    }

    public int getCodigoServicio()
    {
        return this.CodigoServicio;
    }

    public int getCompleta()
    {
        return this.Completa;
    }

    public String getCustomer_FullName()
    {
        return this.Customer_FullName;
    }

    public String getDescripcion()
    {
        return this.Descripcion;
    }

    public float getDuracion()
    {
        return this.Duracion;
    }

    public String getEditSequence()
    {
        return this.EditSequence;
    }

    public String getFecha()
    {
        return this.Fecha;
    }

    public String getFechaAprobacion()
    {
        return this.FechaAprobacion;
    }

    public String getFechaCreacion()
    {
        return this.FechaCreacion;
    }

    public int getGrupo()
    {
        return this.Grupo;
    }

    public String getHora()
    {
        return this.Horas;
    }

    public String getHoras()
    {
        return this.Horas;
    }

    public String getId()
    {
        return this.Id;
    }

    public String getLinea()
    {
        return this.Linea;
    }

    public String getListId()
    {
        return this.ListId;
    }

    public String getNombreActividad()
    {
        return this.NombreActividad;
    }

    public String getNombreEmpleado()
    {
        return this.NombreEmpleado;
    }

    public int getPaquete()
    {
        return this.Paquete;
    }

    public String getPayrollItemWage_FullName()
    {
        return this.PayrollItemWage_FullName;
    }

    public String getTxnID()
    {
        return this.TxnID;
    }

    public void setFecha(String paramString)
    {
        this.Fecha = paramString;
    }

    public void setHora(String paramString)
    {
        this.Horas = paramString;
    }

    public void setId(String paramString)
    {
        this.Id = paramString;
    }

    public void setNombreActividad(String paramString)
    {
        this.NombreActividad = paramString;
    }

    public void setNombreEmpleado(String paramString)
    {
        this.NombreEmpleado = paramString;
    }

    public String toString()
    {
        StringBuilder localStringBuilder = new StringBuilder();
        return "ActividadesSeleccionadas{NombreEmpleado='" + this.NombreEmpleado + '\'' + ", NombreActividad='" + this.NombreActividad + '\'' + ", Fecha='" + this.Fecha + '\'' + ", Hora='" + this.Horas + '\'' + ", Id='" + this.Id + '\'' + '}';
    }
}
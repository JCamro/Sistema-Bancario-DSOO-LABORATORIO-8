package Gestores;

import java.util.ArrayList;

import ClasesBase.Cliente;
import ClasesBase.Cuenta;
import ClasesBase.Titularidad;


public class GestorTitularidades {

	private ArrayList<Titularidad> listaTitularidades;

    private GestorCuentas gCuentas;
    private GestorClientes gClientes;
    private int contadorIds = 1;

	public GestorTitularidades(GestorCuentas gCuentas, GestorClientes gClientes) {
        this.gCuentas = gCuentas;
        this.gClientes = gClientes;
		this.listaTitularidades = new ArrayList<>();
	}


    
    // -- AGREGAR TITULAR A CUENTA --

	public void agregarTitularidad(String dni, String numeroCuenta) {
		int dniCliente = validarStringNumericoInt(dni);
		int numeroCuentaCl = validarStringNumericoInt(numeroCuenta);
        
        //Validaciones
        if (dniCliente==-1) {
            System.out.println("\nERROR ASIGNACION TITULARIDAD: Dni solo debe contener numeros");
            return;
        }
        
        if (numeroCuentaCl==-1) {
            System.out.println("\nERROR ASIGNACION TITULARIDAD: Numero de Cuenta solo debe contener numeros");
            return;
        }

        Cliente clienteP = gClientes.buscarCliente(dniCliente);
        Cuenta cuentaP = gCuentas.buscarCuenta(numeroCuentaCl);

        if (clienteP==null) {
            System.out.println("\nERROR ASIGNACION TITULARIDAD: Cliente no registrado en el sistema");
            return;
        }
        if (cuentaP==null) {
            System.out.println("\nERROR ASIGNACION TITULARIDAD: Cuenta no registrada en el sistema");
            return;
        }

        Titularidad titularidad = new Titularidad("Secundario", clienteP, cuentaP, contadorIds);
        contadorIds++;

        System.out.println(titularidad);
        listaTitularidades.add(titularidad);
	}
    

    // -- METODO PARA CREAR TITULARIDAD CUANDO SE ABRE UNA CUENTA --
    public void agregarPrimeraTitularidad(Titularidad nTitularidad) {
        listaTitularidades.add(nTitularidad);
        contadorIds++;
    }

    // Devuelve un objeto titularidad si lo encuentra
	public Titularidad buscarTitularidad(int id) {
		for (Titularidad titularidad : listaTitularidades) {
			if (titularidad.getIdTitularidad() == id) {
				return titularidad;
			}
		}
		return null;
	}


    //Elimina una titularidad del registro
	public boolean eliminarTitularidadPorId(int id) {
		Titularidad titularidad = buscarTitularidad(id);
		if (titularidad != null) {
			listaTitularidades.remove(titularidad);
			return true;
		}
		return false;
	}

    public void eliminarTitularidad(Titularidad titularidad) {listaTitularidades.remove(titularidad);}


    public Titularidad buscarTitularidad(Cliente cliente, Cuenta cuenta) {
        for (Titularidad titular : listaTitularidades) {
            if (titular.getCliente()==cliente && titular.getCuenta()==cuenta) {
                return titular;
            }
        }
        return null;
    }

    // Enlista las titularidades de un Cliente
	public ArrayList<Titularidad> listarTitularidadesCliente(Cliente cliente) {
        ArrayList<Titularidad> titularidadesCliente = new ArrayList<>();
        for (Titularidad titularidad : listaTitularidades) {
            if (titularidad.getCliente()==cliente) {
                titularidadesCliente.add(titularidad);
            }
        }
        return titularidadesCliente;
	}

    // Enlista las titularidades de una cuenta
    public ArrayList<Titularidad> listarTitularidadesCuenta(Cuenta cuenta) {

        ArrayList<Titularidad> titularidadesCuenta = new ArrayList<>();
        for (Titularidad titularidad : titularidadesCuenta) {
            if (titularidad.getCuenta()==cuenta) {
                titularidadesCuenta.add(titularidad);
            }
        }
        return titularidadesCuenta;
    }


    // Metodos complementarios
    public int getContadorIds() {
        return contadorIds;
    }

    public int validarStringNumericoInt(String numero) {
        int numeroParseado;
        try {
            numeroParseado = Integer.parseInt(numero);
            return numeroParseado;
        } catch (Exception e) {
            return -1;
        }
    }

    
}

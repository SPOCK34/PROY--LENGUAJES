
package prueba;
/**
*La clase Persona definida por atributos que toda persona posee tales como nombre apellidos, etc.
*esta clase contiene metodos que trabajan los datos de una persona y con ellos construye las claves
*CURP , RFC y Clave de Elector.
*Se importa java.util.Random para hacer uso de  usmetodos para generar  un numero aleatorio
*/
import java.util.Random;

public class Persona
{

/**
 *Declaracion de  atributos necesario para definir los datos de una persona 
 * y atributos necesarios para el calculo de las claves
 *El atributo jahr hace referencia al año
 *El atributo d_verif esta ascosiado al digito correspondiente segun la entidad federativa
 */
	private  String nombre, primer_apellido, segundo_apellido;
	private  String CURP, RFC, CLVEE;
	private  String dia,mes,jahr,sexo, estado_nac, homonimia;
	private  int    entidadf;
	private  String vocales="AEIOU";
	private  String parteeinsclave;
	// se crea un objeto de la clase Random llamado r el cual utilizaremos para generar un numero aleatorio
	private  Random  r = new Random();

	
/** Métodos set y get que nos permitiran modificar y obtener  los atributos privados de esta clase
*/
	/*Estos metodos permiten modificar los atributos nombre y apellidos, elimina todos los espacios de la cadena
	 que recibe como atributo y despues convierte cada caracter a mayusculas*/
	public void SetNombre(String nom)
	{
		nom=nom.replace(" ","").toUpperCase();
		nombre=nom;
	}

	public void SetApellido_P(String ap)
	{
		ap=ap.replace(" ","").toUpperCase();
		primer_apellido=ap;
	}
	public void SetApellido_M(String apm)
	{
		apm=apm.replace("","").toUpperCase();
                segundo_apellido=apm;
	}
	/*El método SetGeburtsdatum recibe tres cadenas que indican el dia el mes y el año repectivamente
	  de nacimiento y lo asocia a las variables correpondientes.
	  El método  tambien remplaza todos los esacios y convierte a maysculas
	*/
	public void SetGeburtsdatum(String d, String m, String a)
	{
		d=d.replace(" ","").toUpperCase();
		m=m.replace(" ","").toUpperCase();
		a=a.replace(" ","").toUpperCase();
		dia=d;
		mes=m;
		jahr=a;//jahr = año

	}
	//Metodo que asocia el parametro s a la variable sexo
	public void SetSexo(String s)
	{
		s=s.replace(" ","").toUpperCase();
		sexo=s;
	}
	// Método SetGeburtsort se refiere al lugar de nacimiento
	//ort  parametro que representa el lugar de nacimiento
	public void SetGeburtsort(String ort)
	{
		ort=ort.replace(" ","").toUpperCase();
		estado_nac=ort;

	}
	/**
	*El m'etodo SetStaatsangehorigkeit hace referencia a la entidad federativa de nacimientto
	*Se eliminan todos los espacios y con un switch se regresa el numero correpondiente a la entidad seleccionada
	*
	*/
	public void SetStaatsangehorigkeit(String entf)
	{
		entf=entf.replace(" ","");
		switch(entf)
		{
			case "AGUASCALIENTES":     entidadf=1;  break;  case "BAJACALIFORNIA":   entidadf=2;  break;
			case "BAJACALIFORNIASUR":  entidadf=3;  break;  case "CAMPECHE":         entidadf=4;  break;
			case "COAHUILA":           entidadf=5;  break;  case "COLIMA":           entidadf=6;  break;
			case "CHIAPAS":            entidadf=7;  break;  case "CHIHUAHUA":        entidadf=8;  break;
			case "CIUDADDEMEXICO":     entidadf=9;  break;  case "DURANGO":          entidadf=10; break;
			case "GUANAJUATO":         entidadf=11; break;  case "GUERRERO":         entidadf=12; break;
			case "HIDALGO":            entidadf=13; break;  case "JALISCO":          entidadf=14; break;
			case "EDOMEXICO":          entidadf=15; break;  case "MICHOACAN":        entidadf=16; break;
			case "MORELOS":            entidadf=17; break;  case "NAYARIT":          entidadf=18; break;
			case "NUEVOLEON":          entidadf=19; break;  case "OAXACA":           entidadf=20; break;
			case "PUEBLA":             entidadf=21; break;  case "QUERERTARO":       entidadf=22; break;
			case "QUINTANAROO":        entidadf=23; break;  case "SANLUISPOTOSI":    entidadf=24; break;
			case "SINALOA":            entidadf=25; break;  case "SONORA":           entidadf=26; break;
			case "TABASCO":            entidadf=27; break;  case "TAMAULIPAS":       entidadf=28; break;
			case "TLAXCALA":           entidadf=29; break;  case "VERACRUZ":         entidadf=30; break;
			case "YUCATAN":            entidadf=31; break;  case "ZACATECAS":        entidadf=32; break;

		}
	}
	//´Los siguientes tres métodos regresan las cadenas de las claves para mostrarlas en los textfields correspondientes
    public String getCURP()
        {
            return CURP;
        }
    public String getRFC()
        {
            return RFC;
        }    
    public String getClaveE()    
    {
            return CLVEE;
    }        
    //El siguiente metodo calculaa el CURP mediante el uso de otros metodos que regresan  cadenas
    // y qu al final se concatenan para asignar el valor correspondiente se  hace lo mismo con 
    //el método de CalculaClaveElector   
	public void CalculaCURP()
	{	
		String aux;
		aux= CalculaPozitionEins() + CalculaPozitionZwei() + ""+sexo+CalculaPozitionVier();
		CURP=aux+""+CalculaPozitionfunf()+"0"+CalculaZufallszahl();
	}
	//Este método hace uso del CURP ya calculado y gurada en RFC los primeros 9 caracteres encontrados
	public void CalculaRFC()
	{
		RFC="";
		for (int i=0;i<10 ;i++ ) {
			
			RFC=RFC+CURP.charAt(i);
		}
	}

	public void CalculaClaveElector()
	{
		CLVEE=CalculaP_EinsClave()+CalculaPozitionZwei()+CalculaP_DreiClave()+sexo;
		CLVEE= CLVEE+CalculaP_VierClave();
	}
//Métodos para calcular el CURP
	//Este método calcula las primeras 4 letras del CURP sacadas del nombre y los apellidos de la persona
	public String CalculaPozitionEins()
	{		
		char letrainicial=primer_apellido.charAt(0);
		char primeravocal;
		String parteins;
		//Primer letra del nombre
		char plnom= nombre.charAt(0);
		//liapm = letra inicial del segundo apellido
		char liapm=segundo_apellido.charAt(0);
		int  indice=0;
		//Ciclo que encuentra las primer vocal del primer apellido y lo guada en la variable indice
		for (int i=1;i<primer_apellido.length();i++ ) {
			char aux= primer_apellido.charAt(i);
			if( aux== 'A'|| aux=='E'|| aux == 'I'|| aux== 'O'|| aux=='U'){
				indice=i;
                i=primer_apellido.length();
			}				
		}
		//Se extae la primer vocal del primer apellido
		primeravocal=primer_apellido.charAt(indice);
		//se concatenan las letras
		parteins= letrainicial +""+ primeravocal + liapm + plnom;		
		return parteins;
	}
	//Este método calcula los digitos de la fecha de nacimiento 
	public String CalculaPozitionZwei()
	{
		//se esxtraen los ultimos dos digitos del año de nacimiento
		String jahre=""+jahr.charAt(jahr.length()-2)+jahr.charAt(jahr.length()-1);
		return (jahre + mes + "" + dia);
	}
	//Método que obtine Letra inicial y última consonante, del nombre del estado de nacimiento.
	public String CalculaPozitionVier()
	{
		String aux;
		//se hace una copia del estado de nacimiento y se guarda en aux
		aux = estado_nac.replace(" ","").toUpperCase();
		//se extrae la primer letra del estado
		char erstevocale= aux.charAt(0);
		//se eliminan las vocales
		for (int i=0;i < vocales.length() ;i++ ) {
			aux= aux.replace(vocales.charAt(i)+"","");
		}		
		return(erstevocale+""+aux.charAt(aux.length()-1));
	}
	//métod auxiliar  del metodo CalculaPozitionfunf
	//en este metodo se verifica si la cadena recibida como parametro contiene vocal al principio
	public boolean Validacadenas(String cadena)
	{		
		for (int i=0;i<vocales.length() ;i++ ) {
			if(cadena.charAt(0)== vocales.charAt(i))
				return true;
		}
		return false;	
	}
	//Método que calcula las primeras consonantes internas del primer apellido, segundo apellido y nombre.
	public String CalculaPozitionfunf()
	{
		//se utilizan variables auxiliares para no modificar losdatos originales
		String aux=nombre;
		String aux2 = primer_apellido;	
		String aux3= segundo_apellido;
			
			//ciclo que elimina las vocales	
		for (int i=0;i < vocales.length() ;i++ ) {
			aux= aux.replace(vocales.charAt(i)+"","");
			aux2= aux2.replace(vocales.charAt(i)+"","");
			aux3= aux3.replace(vocales.charAt(i)+"","");	
		}
		//se valida si l acadena  tien vocla al principio
		if(Validacadenas(nombre))
			aux=""+aux.charAt(0);//si es cierto se extrae la primer consonante
		else
			aux=""+aux.charAt(1);//en caso contrario se extrae la segunda consonante
		if (Validacadenas(primer_apellido)) 
			aux2=""+aux2.charAt(0);
		else
			aux2=""+aux2.charAt(1);
		
		if (Validacadenas(segundo_apellido)) 
			aux3=""+aux3.charAt(0);
		else
			aux3=""+aux3.charAt(1);
		//se asigna primera letra y primera consonante de los apellidos y el nombre del elector
		parteeinsclave=""+primer_apellido.charAt(0)+aux2+segundo_apellido.charAt(0);
		parteeinsclave=parteeinsclave+""+aux3+nombre.charAt(0)+aux;
		//se retorna las letras que ocuparan de la posicion 14 a 16 del CURP
		return(""+aux2+aux3+aux);
	}
	//se genera el numero aleatorio para usarlo en las claves
	public String CalculaZufallszahl()
	{
		//int zufallszahl = r.nextInt(10);
		return (""+r.nextInt(10));
		//return""+zufallszahl;

	}
// Métodos para calcular Clave de Elector
    // este método retorna las letras de la posicion 1 a la 6 de la cave de elector
	public String CalculaP_EinsClave()
	{
		return parteeinsclave;
		
	}
	//en este metodo se asigna un 0 si el numero de la entidad es menor a 10
	public String CalculaP_DreiClave()
	{
		int opcion;
			opcion=entidadf;
			if (opcion<10)
				return "0"+opcion;	
		return ""+opcion;
	}
	//El sigguiente metodo multiplica el valor de la entidad por 10 si sexo es H y por 10 si es M
	//para generar los 3 digitos se hacen las validaciones ya que en unos caso la multiplicacion es de dos digitos
	//se agrega un 0 
	public String CalculaP_VierClave()
	{
		if(entidadf <5){
			if(sexo == "M")
				return "0"+(entidadf*20);

	 		return "0"+(entidadf*10);
		}
		else if(entidadf <10){
				if(sexo == "M")
					return ""+(entidadf*20);

	 			return "0"+(entidadf*10);
		}
		if(sexo == "M")
			return ""+(entidadf*20);

	 	return ""+(entidadf*10);	
		
	}

}
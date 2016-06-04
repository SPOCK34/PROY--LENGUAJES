import java.util.Random;
public class Persona
{
	private  String nombre, primer_apellido, segundo_apellido;
	private  String nombrem, primer_apellidom, segundo_apellidom;
	private  String CURP, RFC, CLVEE;
	private  String fecha_nac,sexo, estado_nac, homonimia;
	private  int    d_verif,entidadf;
	private  String vocales="AEIOU";
	private  Random  r = new Random();

	public void CapturaDatos()
	{
		String aux;
		do
		{
			nombre=Teclado.LeeCadena("Ingrese su nombre(s): ");
			nombre=nombre.replace(" ","").toUpperCase();
			
		}while(ValidaCadena(nombre));
		do
		{
			primer_apellido =Teclado.LeeCadena("Ingrese su primer apellido: ");
			primer_apellido=primer_apellido.replace(" ","").toUpperCase();
			
		}while(ValidaCadena(primer_apellido));
		do
		{
			segundo_apellido =Teclado.LeeCadena("Ingrese su segundo apellido: ");
			segundo_apellido=segundo_apellido.replace(" ","").toUpperCase();
			
		}while(ValidaCadena(segundo_apellido));
		do
		{
			fecha_nac=Teclado.LeeCadena("Ingrese su fecha de naciemiento  ejemplo 13/08/1999 : ");
			fecha_nac=fecha_nac.replace(" ","");
		}while(ValidaFecha(fecha_nac));
		do
		{
			sexo =Teclado.LeeCadena("Ingrese su sexo  M / H: ");
			sexo=sexo.replace(" ","").toUpperCase();
			
		}while(ValidaSexo(sexo));
		do
		{
			estado_nac=Teclado.LeeCadena("Ingrese el estado de procedencia: ");
			estado_nac=estado_nac.replace(" ","").toUpperCase();
			
		}while(ValidaCadena(estado_nac));
		
	}

	public void CalculaCURP()
	{	
		String aux;
		aux= CalculaPozitionEins() + CalculaPozitionZwei() + ""+ CalculaPozitionDrei()+CalculaPozitionVier();
		CURP=aux+""+CalculaPozitionfunf()+"0"+CalculaZufallszahl();
		System.out.println("\n El CURP es: ");
		System.out.print(CURP);
	}

	public void CalculaRFC()
	{
		RFC="";
		for (int i=0;i<10 ;i++ ) {
			
			RFC=RFC+CURP.charAt(i);
		}
		System.out.println("\n El RFC es: ");
		System.out.print(RFC);
	}
	public void CalculaClaveElector()
	{
		CLVEE=CalculaP_EinsClave()+CalculaPozitionZwei()+CalculaP_DreiClave()+CalculaPozitionDrei();
		CLVEE= CLVEE+CalculaP_VierClave();
	}
//Métodos de validaciones para  el texto ingresado
	public boolean ValidaSexo(String texto)
	{
		for(int i=0;i<texto.length();i++)
        if(!(texto.charAt(i) =='M' || texto.charAt(i) =='H') )
         {
            System.out.println("EL TEXTO NO ES VALIDO  =/ ingresa H o M segun sea el caso");
            return true;
         }
        return false;
	}

	public boolean ValidaCadena(String texto)
	{
		boolean aux=false;
		for(int i=0;i<texto.length();i++)
        if(!(texto.charAt(i) >='A' && texto.charAt(i) <='Z'|| texto.charAt(i)==165) )
         {
            System.out.println("EL TEXTO NO ES VALIDO  =/ ");
            aux=true;
         }  

         return aux;    		
	}
	public boolean ValidaFecha(String texto)
	{
		for (int i=0;i<texto.length();i++ ) 
			if(!(texto.charAt(i) >= '0' && texto.charAt(i) <= '9'|| texto.charAt(i)==47) )
         		{
            		System.out.println("EL TEXTO NO ES VALIDO  =/ ");
            		i=texto.length();
            		return true;
         		}  
		

		return false;
	}
	
//Métodos para calcular el CURP

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

		for (int i=1;i<primer_apellido.length();i++ ) {
			char aux= primer_apellido.charAt(i);
			if( aux== 'A'|| aux=='E'|| aux == 'I'|| aux== 'O'|| aux=='U'){
				indice=i;
                i=primer_apellido.length();
			}				
		}
		primeravocal=primer_apellido.charAt(indice);

		parteins= letrainicial +""+ primeravocal + liapm + plnom;
		
		return parteins;

	}

	public String CalculaPozitionZwei()
	{
		String dia, mes, jahre;
		String fecha;
		String []aux =fecha_nac.split("/");
		dia = aux[0];
		mes = aux[1];
		jahre = aux[2].charAt(2)+""+aux[2].charAt(3);

		return (jahre + mes + "" + dia);
	}

	public String CalculaPozitionDrei()
	{
		sexo= sexo.replace(" ","").toUpperCase();
		return sexo;
	}

	public String CalculaPozitionVier()
	{
		String aux;
		aux = estado_nac.replace(" ","").toUpperCase();
		char erstevocale= aux.charAt(0);
		for (int i=0;i < vocales.length() ;i++ ) {
			aux= aux.replace(vocales.charAt(i)+"","");
		}
		
		return(erstevocale+""+aux.charAt(aux.length()-1));
	}

	public String CalculaPozitionfunf()
	{
		String aux=nombre;
		String aux2 = primer_apellido;	
		String aux3= segundo_apellido;
		
		for (int i=0;i < vocales.length() ;i++ ) {
			aux= aux.replace(vocales.charAt(i)+"","");
			aux2= aux2.replace(vocales.charAt(i)+"","");
			aux3= aux3.replace(vocales.charAt(i)+"","");	
		}
		return(""+aux2.charAt(1)+aux3.charAt(1)+aux.charAt(1));

	}

	public String CalculaZufallszahl()
	{
		//int zufallszahl = r.nextInt(10);
		return (""+r.nextInt(10));
		//return""+zufallszahl;

	}
// Métodos para calcular Clave de Elector

	public String CalculaP_EinsClave()
	{
		String cadena= ""+primer_apellido.charAt(0)+primer_apellido.charAt(1);
		cadena= cadena+segundo_apellido.charAt(0)+segundo_apellido.charAt(1);
		cadena=cadena+nombre.charAt(0)+nombre.charAt(1);

		return(cadena);
	}
	public String CalculaP_DreiClave()
	{
		Menu menu = new Menu();
		do
		{

		}while(opcion != 32);
	}

	public String CalculaP_VierClave()
	{
		if(sexo == "M")
			return entidadf*20;

	 	return entidadf*10;
	}

}
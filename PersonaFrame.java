import java.util.Random;
public class PersonaFrame
{
	private  String nombre, primer_apellido, segundo_apellido;
	private  String CURP, RFC, CLVEE;
	private  String dia,mes,jahr,sexo, estado_nac, homonimia;
	private  int    d_verif,entidadf;
	private  String vocales="AEIOU";
	private  Random  r = new Random();

	
// Métodos set
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
	}
	public void SetGeburtsdatum(String d, String m, String a)
	{
		d=d.replace(" ","").toUpperCase();
		m=m.replace(" ","").toUpperCase();
		a=a.replace(" ","").toUpperCase();
		dia=d;
		mes=m;
		jahr=a;

	}
	public void SetSexo(String s)
	{
		s=s.replace(" ","").toUpperCase();
		sexo=s;
	}

	public void SetGeburtsort(String ort)
	{
		ort=ort.replace(" ","").toUpperCase();
		estado_nac=ort;

	}
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
			case "MEXICO(EDO)":        entidadf=15; break;  case "MICHOACAN":        entidadf=16; break;
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

	public void CalculaCURP()
	{	
		String aux;
		aux= CalculaPozitionEins() + CalculaPozitionZwei() + ""+ CalculaPozitionDrei()+CalculaPozitionVier();
		CURP=aux+""+CalculaPozitionfunf()+"0"+CalculaZufallszahl();
	}

	public void CalculaRFC()
	{
		RFC="";
		for (int i=0;i<10 ;i++ ) {
			
			RFC=RFC+CURP.charAt(i);
		}
	}
	public void CalculaClaveElector()
	{
		CLVEE=CalculaP_EinsClave()+CalculaPozitionZwei()+CalculaP_DreiClave()+CalculaPozitionDrei();
		CLVEE= CLVEE+CalculaP_VierClave();
	}

	public void ImprimeClaves()
	{
		CalculaCURP();
		CalculaRFC();
		CalculaClaveElector();
	}
//Métodos de validaciones para  el texto ingresado
	
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
		String jahre=""+jahr.charAt(jahr.length()-1)+jahr.charAt(jahr.length()-2);


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
		int opcion;
			opcion=entidadf;
			if (opcion<10)
				return "0"+opcion;	
		return ""+opcion;
	}

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
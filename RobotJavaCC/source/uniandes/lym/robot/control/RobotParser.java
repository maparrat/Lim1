/*
Basic Robot Grammar
Author: Miguel Parra - Daniel  Londoño
//danielslondonob
 **/

options 
{
	LOOKAHEAD=1; 
	IGNORE_CASE=true;
	STATIC = false;
	
}
   
   
PARSER_BEGIN(RobotParser)

package uniandes.lym.robot.control;

import uniandes.lym.robot.kernel.*;
import java.io.*;


@SuppressWarnings("serial")
public class RobotParser 
{
   class Variable {

    private ArrayList variables = new ArrayList();
	private ArrayList valores = new ArrayList();
	
	
	private String nombre; 
	
	private Integer numero;
	
	
	public Variable( String pNombre)
	{
		nombre = pNombre;
		numero = 0;	
	
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public Integer getNumero() {
		return numero;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}

	private RobotWorldDec robotw;
	 private Variable[] store;
	 private int eax;
	
	void setWorld(RobotWorld w) {
		robotw = (RobotWorldDec) w;
		 store = new Variable[100];
	}

    void delay(int d) {
				try {
	    			        Thread.sleep(d);
	    			    } catch (InterruptedException e) {
	    			        System.err.format("IOException: %s%n", e);
	    			    }

    }  
	
}
PARSER_END(RobotParser)

SKIP:
{
	  
 " "
| "\r"
| "\t"
| "\n"
}

TOKEN: /* Command  names */
{

			<T_MOVE:  "Move">
		| 	<T_TURNRIGHT: "TURNRIGHT">
		| 	<T_PUTB: "PutB">
		| 	<T_PICKB: "PickB">
		| 	<T_PUTC: "PutC">
		| 	<T_PICKC: "PickC">
		

}

TOKEN: /*Palabras clave*/
{
 	< ROBOT_R: "ROBOT_R">
	|< VARS: "VARS" > 
	| < BEGIN: "BEGIN">
  	| < END: "END">
  	| < Balloons :"Balloons" >
  	| < Chips :"Chips" >
  	| < skip:"skip" >
  	
}
TOKEN: /*Control Estructure*/
{
  	 
	< THEN:"then" >
	
	|<DO:"do" >
	|< REPEAT :"repeat" >
	|< TIMES: "times" >
	|< IF:"if" >
	|<WHILE:"while" >
	|< ELSE:"else" >
	   
}
 TOKEN: /*comandos*/
{
 	< assign: "assign">
	| < move: "move">
  	| < turn: "turn">
  	| < face:"face">
  	| < put: "put" >
  	| < pick:"pick" >
  	| < to:"to" >
  	| < of:"of" >
  	| < toThe:"toThe">
  	| < inDir:"inDir" >
}
TOKEN: /*direcciones*/

{
  <rigth:"right">
  |< left:"left" >
  |< around: "around" >
  | < north:"north" >
  | < south:"south" >
  | < east: "east" >
  | < west: "west" >
  | < frontBack: "front" | "back"  >
}

TOKEN: /*Condiciones*/
{
  	< facing:"facing" >
	|< canPut:"canPut" >
	|<canPick: "canPick" >
	|< canMove:"canMove" >
	|< not:"not" >    
  	
}

TOKEN: /*nombres*/
{
 < name:["A"-"Z"] (["a"-"z"]["0"-"9"])* >
| < numero:["0"-"9"] (["0"-"9"])* >
|  < objeto: "Balloons" | "Chips" >
}
 

	boolean instructions(StringBuffer system) :
	{	
				String output=new String();
			
		
	}
	{
	  secuencia()
	  {
	 
	    return true;
	  }
	}
	
void secuencia():
{
     
}
{
  < ROBOT_R>(<VARS >var())? instrucion()
}
void var():
{

}
{
  
  varF()(","varF())*




}
void varF():
{
Token variableToken =  new Token();
}
{
 // < name >
  (variableToken =   < name >)
 {
	  String cadena  = variableToken.image;
   boolean encontro = false;
   int contador = 0;
   while (encontro == false)
   {
     if(store[contador] ==   null )
     {
       Variable variablex= new Variable(cadena);
       store[contador] = variablex;
     }
     contador++;
   }
  
 }
}
void instrucion():
{
}
{
 <BEGIN>intru()(";"intru())*  
}
void intru():
{
}
{
  (command() |controlStructure())
}
void command():
{

}
{
 assign()
 |move()
 |turn()
 |face()
 |put()
 |pick()
 |moveTD()

 |< skip >

 { }   
}

void block(boolean ejecutar):
{
  
}
{
 	 {
  		if(!ejecutar) {
			Token t = getNextToken();
			while(t.kind!= T_END) {
				t = getNextToken();
			}
			return;
		} 
	}
  	(
		<BEGIN >(secuencia(ejecutar) semicolon(ejecutar)
	  {
	    delay(500);
	  })+ < END >
	)
}

void semicolon(boolean ejecutar):
{
  
}
{
  
	(
	  {
	 	if(!ejecutar) {
			return;
  		}
  	  } (";")?
	)

}

void assign():
{
 
  Integer numero = null;
  Token tokenNombre; 
  Token tokenNumero;
  String nombre="";

}
{
 < assign >":"(tokenNombre=<numero>) < to>":" (tokenNumero=<name>)
 //(tokenNombre=<numero>)
 //(tokenNumero=<name>)
   
 
 {
    
	try
	{
	  int  pNumero = Integer.parseInt(tokenNumero.image);
	  boolean encontro = false;
	  int contador = 0; 
	 while(encontro = false ||contador < store.length )
	 {
	   if(store[contador].getNombre() == tokenNombre.image)
	   {
	     store[contador].setNumero(pNumero);
	     encontro = true;
	     
	   }
		contador++;
	 }
	  
	}
	catch(Exception e)
	{
		  
	}

	
	
	
}

 
 
}
void move():
{
  Token nmovimientos = new Token() ;
  
}
{
 //(< numero>|< name>)
 (nmovimientos = < numero>|< name>)

 {
   try
   {
     int movimiento = Integer.parseInt(nmovimientos.image);
   
      robotw.moveForward(movimiento); 
   
   }
   catch (NumberFormatException excepcion)
   {
    int contador = 0;
    boolean encontro = true;
    int veces = 0; 
    while( contador < store.length|| encontro == false)
    {
      if(store[contador].getNombre().equals(nmovimientos.image))
      {
        veces = store[contador].getNumero();
        if (veces != 0)
        {
           robotw.moveForward(veces); 
        }
        encontro = true;
      }
      contador ++;
      
    }
   }
 }
}
void turn():
{
  Token direccionT = new Token();  
}
{
 < turn >":" (direccionT = < rigth>| < left>| < around >)
 //(direccionT = < rigth>| < left>| < around >)

 {
   String direccion= direccionT.image;
   if(direccion.equals("right"))
   {
	robotw.turnRight();
   }
   else if (direccion.equals("left"))
   {
     robotw.turnRight();
     robotw.turnRight();
     robotw.turnRight();
   }
      else if (direccion.equals("around"))
   {
     robotw.turnRight();
     robotw.turnRight();
 
   }
 }
}
void face():
{
  Token direccionT = new Token();  
  
}
{
 < face>":"(direccionT = < north>| < south>| < east >| < west>)


 {
   String direccion= direccionT.image;
   if(direccion.equals("north"))
   {
     robotw.facingNorth();
   }
    else  if(direccion.equals("south"))
   {
     robotw.facingSouth();
   }
    else if(direccion.equals("west"))
   {
     robotw.facingWest();
   }
    else if(direccion.equals("east"))
   {
     robotw.facingEast();
   }
   
 }
}
void put():
{
  Token cantidadT = new Token();
  Token objetoT = new Token();
}
{
 < put >":" (cantidadT =< numero>|< name> )< of >":" (objetoT = <Balloons >| < Chips>)



 {
   try
   {
      int  pNumero = Integer.parseInt(cantidadT.image);
     
      
        if(objetoT.image.equals("Balloons"))
        {
          robotw.putBalloons(pNumero);
        }
        else  if(objetoT.image.equals("Chips"))
        {
          robotw.putChips(pNumero);
        }
     
   }
  catch (NumberFormatException excepcion)
   {
     int numero =0;
     boolean encontrado = false; 
     for(int i = 0; i < store.length|| encontrado == false; i++)
     {
       if(store[i].getNombre().equals(cantidadT.image))
       {
         
          if(objetoT.image.equals("Balloons"))
        {
          robotw.putBalloons(store[i].getNumero());
        }
        else  if(objetoT.image.equals("Chips"))
        {
          robotw.putChips(store[i].getNumero());
        }
        
         encontrado = true;
       }
     }
   }
   
 }
}
void pick():
{
  Token cantidadT = new Token();
  Token objetoT = new Token();

}
{
 < pick >":"  (cantidadT =< numero>|< name> )< of >":" (objetoT = <Balloons >| < Chips>)


 {
   try
   {
      int  pNumero = Integer.parseInt(cantidadT.image);
     
      
        if(objetoT.image.equals("Balloons"))
        {
          robotw.grabBalloons(pNumero);
        }
        else  if(objetoT.image.equals("Chips"))
        {
          robotw.pickChips(pNumero);
        }
     
   }
  catch (NumberFormatException excepcion)
   {
     int numero =0;
     boolean encontrado = false; 
     for(int i = 0; i < store.length|| encontrado == false; i++)
     {
       if(store[i].getNombre().equals(cantidadT.image))
       {
         
          if(objetoT.image.equals("Balloons"))
        {
          robotw.grabBalloons(store[i].getNumero());
        }
        else  if(objetoT.image.equals("Chips"))
        {
          robotw.pickChips(store[i].getNumero());
        }
        
         encontrado = true;
       }
     }
   }
 }
}
void moveT():
{
  Token direccionT = new Token();
}
{
 <toThe>":"(direccionT = < rigth>| < left>| < around >)
 

 {

   if(robotw.getOrientation() == 0)
   {
     robotw.facingNorth();
     robotw.moveForward(eax);
     
   }
      if(robotw.getOrientation() == 1)
   {
     robotw.facingSouth();
     robotw.moveForward(eax);
     
   }
      if(robotw.getOrientation() == 2)
   {
     robotw.facingEast();
     robotw.moveForward(eax);
     
   }
      if(robotw.getOrientation() == 3)
   {
     robotw.facingWest();
     robotw.moveForward(eax);
     
   }
 }
}
void moveD():
{
   Token direccionT = new Token();
   int valor = 0;
   String direccion = "";
}
{
 <inDir>":"(direccionT =< north>| < south>| < east >| < west>)

(
    <move>"("( direcccionT=< numero >
    {
      valor=Integer.parseInt(direccionT.toString());
    } | token=< name >
    {
      int pos = variables.indexOf(direccionT.toString());
  	  if(pos!=-1) {
  	    valor=(int) valores.get(pos);
  	  }
  	 } ) "," direccionT=<inDir>
  	 {
  	    direccionF = direccionT.toString();
  	 }")"
  )

 
 {
   if(direccionT.image.equals("west"))
   {
     robotw.moveHorizontally(- eax);
   }
   else  if(direccionT.image.equals("east"))
   {
     robotw.moveHorizontally(eax);
   }
    else if(direccionT.image.equals("north"))
   {
     robotw.moveVertically(eax);
   }
     else if(direccionT.image.equals("south"))
   {
     robotw.moveVertically(-eax);
   }
 }
}
//Move general
void moveTD():
{
  Token num = new Token();
}
{
   < move>":"(num = < numero >(moveT()|moveD()|move()))
  
}
//----------------------------------------------------------------------------------------------------------------------
void controlStructure():
{
 	
}
{
 	conditional()
	|loop()
	| RepeatTimes()
}
void conditional():
{
	boolean x; 
}
{
  < IF>":" condicion()< THEN >":"intru() <ELSE > intru()

  (
	  < IF > "(" x=condicion() ","  block(x) "," block(!x) ")" 
  )
  
}
void loop():
{

}
{
	< WHILE >"(" condicion() ","block(true)")"
	{
	}
}
void RepeatTimes():
{
	int i = 0;
	Token t = new Token();
}
{
  < REPEAT >":" intru()< TIMES>":"(< numero >|< name >)

 (
	  < REPEAT >"("
	  ( t=< numero >
		  {
		    i = Integer.parseInt(t.toString());
		  }
	  | token=< name >
		  {
		    int posicion = variables.indexOf(t.toString());
		    if(posicion != -1)
		    {
		      i = (int)valores.get(posicion);
		    }
		  }
	  ) ","
	  
	  {
	    int beginline,begincol,endline,endcol;
		t = getNextToken();
		while(t.kind != END) {
		  	
			break;
		}
		for(int j = 0; j < i-1; j++) {
			
			block(true);
			
		}
	    
	  }
	  ")"
	  
	)

}

void condicion():
{
}
{
  facing()
  |canPut()
  |canPick()
  |canMove()
  |not() 
}	

boolean facing():
{
  String s = "";
  Token t = new Token();
  boolean x;
}
{
  < facing >":"(< north>| < south>| < east >| < west>)

	(
	  < face > "(" t=< facing > ")" 
	  {
	    s=t.toString();
	    
	    if(s.equals("north")&&robotw.facingNorth())
	    {
			x = true;
	    }
	    else if(s.equals("south")&&robotw.facingSouth())
	    {
			x =true;
	    }
	    else if(s.equals("west")&&robotw.facingWest())
	    {
			x = true;
	    }
	    else if(s.equals("east")&&robotw.facingEast())
	    {
			x = true;
	    }
	    else
	    {
 			x = false;
	    }
	  }
	)
	{
	  return b;
	}

  
}
void canPut():
{

  Token t = new Token();
  String s = "";
  int i = 0;
  boolean b;
}
{
  < canPut >":"(< numero >|< name >) < of >":"(objetoT = <Balloons >| < Chips>)
    	< canPut > "(" t= < objeto >
  	{
  	  s = token.toString();
  	}
  	  "," 
      ( token=< numero >
		  {
		    i=Integer.parseInt(t.toString());
		  }
	  | token=< name >
		  {
		    int posicion = variables.indexOf(t.toString());
		    if(posicion!=-1) {
		      i=(int)valores.get(posicion);
		    }
		  }
	  ) ")"
	  {
	    if(s.equals("Baloons"))
	    {
	     if(robotw.getMyBalloons() < i )
	     {
	        b= false;
	     }
	     else
	     {
			b= true;
	     }
			 	      
	    }
	    else if
	    (s.equals("Chips"))
	    {
			if(robotw.getMyChips()< valor)
			{
	        	b= false;
	     	}
	     	else
	     	{
				b= true;
	     	}
	     }
	     b= false;

	     return b;
	  }
}
void canPick():
{
  Token t = new Token();
  int i = 0;
  String s = "";
}
{
  < canPick >":"(< numero >|< name >) < of >":"(<Balloons >| < Chips>)
  (
    <canPick>"("t=< objeto >
    {
      s = t.toString();
    }"," ( t = < numero >
    {
      i = Integer.parseInt(t.toString());
    } | t = < name >
    {
      int posicion = variables.indexOf(t.toString());
  	  if(posicion!=-1)
  	  {
  	    i = (int)valores.get(posicion);
  	  }
  	} )")"
  )
  {
	if(s.equals("Balloons"))
	{
		for(int i = 0; i < valor; i++)
		{
			robotw.pickBalloon();
		}
	}
	else if (object.equals("Chips"))
	{
		for(int i = 0; i <valor; i++)
		{ 
			robotw.pickChip();
		}
	}	
  }
}
void canMove():
{
  Token t = new Token();
  int numero = 0;
  String dir = "";
}
{
  < canMove >":"(< north>| < south>| < east >| < west>)

  (
    <move >"("( token=< numero >
    {
      numero=Integer.parseInt(t.toString());
    } | t=< name >
    {
      int posicion = variables.indexOf(t.toString());
  	  if(posicion!=-1) {
  	    numero=(int) valores.get(pos);
  	  }
  	 } ) "," t=< frontBack >
  	 {
  	    dir = t.toString();
  	 }")"
  )
  {
    if(dir.equals("left"))
    {
      for(int i = 0; i < numero; i++)
      {
			robotw.moveWest();
      }
    }
    else if(dir.equals("right"))
    {
      for(int i = 0; i < numero; i++) {
			robotw.moveEast();
      }
    }
    else if(direccion.equals("front")) {
      for(int i = 0; i < numero; i++) {
			robotw.moveNorth();

      }
    }else if(direccion.equals("back")) {
      for(int i = 0; i < numero; i++) {
			robotw.moveSouth();
      }
    }
  }
}
boolean not():
{
  boolean b;
}
{
  (
	<not> "(" b=condicion() ")"
  )
  {
		return !b;
  }
}

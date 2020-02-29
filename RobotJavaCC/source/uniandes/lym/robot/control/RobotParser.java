/* Generated By:JavaCC: Do not edit this line. RobotParser.java */
package uniandes.lym.robot.control;

import uniandes.lym.robot.kernel.*;
import java.io.*;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class RobotParser implements RobotParserConstants {
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

  final public boolean instructions(StringBuffer system) throws ParseException {
                                String output=new String();
    secuencia();
            {if (true) return true;}
    throw new Error("Missing return statement in function");
  }

  final public void secuencia() throws ParseException {
    jj_consume_token(ROBOT_R);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VARS:
      jj_consume_token(VARS);
      var();
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    instrucion();
  }

  final public void var() throws ParseException {
    varF();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 51:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      jj_consume_token(51);
      varF();
    }
  }

  final public void varF() throws ParseException {
Token variableToken =  new Token();
    variableToken = jj_consume_token(name);
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

  final public void instrucion() throws ParseException {
    jj_consume_token(BEGIN);
    intru();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 52:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      jj_consume_token(52);
      intru();
    }
  }

  final public void intru() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case skip:
    case assign:
    case move:
    case turn:
    case face:
    case put:
    case pick:
    case name:
    case numero:
      command();
      break;
    case REPEAT:
    case IF:
    case WHILE:
      controlStructure();
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void command() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case assign:
      assign();
      break;
    case name:
    case numero:
      move();
      break;
    case turn:
      turn();
      break;
    case face:
      face();
      break;
    case put:
      put();
      break;
    case pick:
      pick();
      break;
    case move:
      moveTD();
      break;
    case skip:
      jj_consume_token(skip);

      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void block(boolean ejecutar) throws ParseException {
                if(!ejecutar) {
                        Token t = getNextToken();
                        while(t.kind!= T_END) {
                                t = getNextToken();
                        }
                        {if (true) return;}
                }
    jj_consume_token(BEGIN);
    label_3:
    while (true) {
      secuencia(ejecutar);
      semicolon(ejecutar);
            delay(500);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ROBOT_R:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
    }
    jj_consume_token(END);
  }

  final public void semicolon(boolean ejecutar) throws ParseException {
                if(!ejecutar) {
                        {if (true) return;}
                }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 52:
      jj_consume_token(52);
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
  }

  final public void assign() throws ParseException {
  Integer numero = null;
  Token tokenNombre;
  Token tokenNumero;
  String nombre="";
    jj_consume_token(assign);
    jj_consume_token(53);
    tokenNombre = jj_consume_token(numero);
    jj_consume_token(to);
    jj_consume_token(53);
    tokenNumero = jj_consume_token(name);
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

  final public void move() throws ParseException {
  Token nmovimientos = new Token() ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      nmovimientos = jj_consume_token(numero);
      break;
    case name:
      jj_consume_token(name);
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
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

  final public void turn() throws ParseException {
  Token direccionT = new Token();
    jj_consume_token(turn);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case rigth:
      direccionT = jj_consume_token(rigth);
      break;
    case left:
      jj_consume_token(left);
      break;
    case around:
      jj_consume_token(around);
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
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

  final public void face() throws ParseException {
  Token direccionT = new Token();
    jj_consume_token(face);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case north:
      direccionT = jj_consume_token(north);
      break;
    case south:
      jj_consume_token(south);
      break;
    case east:
      jj_consume_token(east);
      break;
    case west:
      jj_consume_token(west);
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
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

  final public void put() throws ParseException {
  Token cantidadT = new Token();
  Token objetoT = new Token();
    jj_consume_token(put);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      cantidadT = jj_consume_token(numero);
      break;
    case name:
      jj_consume_token(name);
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(of);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Balloons:
      objetoT = jj_consume_token(Balloons);
      break;
    case Chips:
      jj_consume_token(Chips);
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
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

  final public void pick() throws ParseException {
  Token cantidadT = new Token();
  Token objetoT = new Token();
    jj_consume_token(pick);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      cantidadT = jj_consume_token(numero);
      break;
    case name:
      jj_consume_token(name);
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(of);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Balloons:
      objetoT = jj_consume_token(Balloons);
      break;
    case Chips:
      jj_consume_token(Chips);
      break;
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
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

  final public void moveT() throws ParseException {
  Token direccionT = new Token();
    jj_consume_token(toThe);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case rigth:
      direccionT = jj_consume_token(rigth);
      break;
    case left:
      jj_consume_token(left);
      break;
    case around:
      jj_consume_token(around);
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
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

  final public void moveD() throws ParseException {
   Token direccionT = new Token();
   int valor = 0;
   String direccion = "";
    jj_consume_token(inDir);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case north:
      direccionT = jj_consume_token(north);
      break;
    case south:
      jj_consume_token(south);
      break;
    case east:
      jj_consume_token(east);
      break;
    case west:
      jj_consume_token(west);
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(move);
    jj_consume_token(54);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      direccionT = jj_consume_token(numero);
      valor=Integer.parseInt(direccionT.toString());
      break;
    case name:
      token = jj_consume_token(name);
      int pos = variables.indexOf(direccionT.toString());
          if(pos!=-1) {
            valor=(int) valores.get(pos);
          }
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(51);
    direccionT = jj_consume_token(inDir);
            direccionF = direccionT.toString();
    jj_consume_token(55);
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

//Move general
  final public void moveTD() throws ParseException {
  Token num = new Token();
    jj_consume_token(move);
    jj_consume_token(53);
    num = jj_consume_token(numero);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case toThe:
      moveT();
      break;
    case inDir:
      moveD();
      break;
    case name:
    case numero:
      move();
      break;
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

//----------------------------------------------------------------------------------------------------------------------
  final public void controlStructure() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IF:
      conditional();
      break;
    case WHILE:
      loop();
      break;
    case REPEAT:
      RepeatTimes();
      break;
    default:
      jj_la1[18] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public void conditional() throws ParseException {
        boolean x;
    jj_consume_token(IF);
    jj_consume_token(53);
    condicion();
    jj_consume_token(THEN);
    jj_consume_token(53);
    intru();
    jj_consume_token(ELSE);
    intru();
    jj_consume_token(IF);
    jj_consume_token(54);
    x = condicion();
    jj_consume_token(51);
    block(x);
    jj_consume_token(51);
    block(!x);
    jj_consume_token(55);
  }

  final public void loop() throws ParseException {
    jj_consume_token(WHILE);
    jj_consume_token(54);
    condicion();
    jj_consume_token(51);
    block(true);
    jj_consume_token(55);

  }

  final public void RepeatTimes() throws ParseException {
        int i = 0;
        Token t = new Token();
    jj_consume_token(REPEAT);
    jj_consume_token(53);
    intru();
    jj_consume_token(TIMES);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      jj_consume_token(numero);
      break;
    case name:
      jj_consume_token(name);
      break;
    default:
      jj_la1[19] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(REPEAT);
    jj_consume_token(54);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      t = jj_consume_token(numero);
                    i = Integer.parseInt(t.toString());
      break;
    case name:
      token = jj_consume_token(name);
                    int posicion = variables.indexOf(t.toString());
                    if(posicion != -1)
                    {
                      i = (int)valores.get(posicion);
                    }
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(51);
            int beginline,begincol,endline,endcol;
                t = getNextToken();
                while(t.kind != END) {

                        break;
                }
                for(int j = 0; j < i-1; j++) {

                        block(true);

                }
    jj_consume_token(55);
  }

  final public void condicion() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case facing:
      facing();
      break;
    case canPut:
      canPut();
      break;
    case canPick:
      canPick();
      break;
    case canMove:
      canMove();
      break;
    case not:
      not();
      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  final public boolean facing() throws ParseException {
  String s = "";
  Token t = new Token();
  boolean x;
    jj_consume_token(facing);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case north:
      jj_consume_token(north);
      break;
    case south:
      jj_consume_token(south);
      break;
    case east:
      jj_consume_token(east);
      break;
    case west:
      jj_consume_token(west);
      break;
    default:
      jj_la1[22] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(face);
    jj_consume_token(54);
    t = jj_consume_token(facing);
    jj_consume_token(55);
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
          {if (true) return b;}
    throw new Error("Missing return statement in function");
  }

  final public void canPut() throws ParseException {
  Token t = new Token();
  String s = "";
  int i = 0;
  boolean b;
    jj_consume_token(canPut);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      jj_consume_token(numero);
      break;
    case name:
      jj_consume_token(name);
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(of);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Balloons:
      objetoT = jj_consume_token(Balloons);
      break;
    case Chips:
      jj_consume_token(Chips);
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(canPut);
    jj_consume_token(54);
    t = jj_consume_token(objeto);
          s = token.toString();
    jj_consume_token(51);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      token = jj_consume_token(numero);
                    i=Integer.parseInt(t.toString());
      break;
    case name:
      token = jj_consume_token(name);
                    int posicion = variables.indexOf(t.toString());
                    if(posicion!=-1) {
                      i=(int)valores.get(posicion);
                    }
      break;
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(55);
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

             {if (true) return b;}
  }

  final public void canPick() throws ParseException {
  Token t = new Token();
  int i = 0;
  String s = "";
    jj_consume_token(canPick);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      jj_consume_token(numero);
      break;
    case name:
      jj_consume_token(name);
      break;
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(of);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Balloons:
      jj_consume_token(Balloons);
      break;
    case Chips:
      jj_consume_token(Chips);
      break;
    default:
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(canPick);
    jj_consume_token(54);
    t = jj_consume_token(objeto);
      s = t.toString();
    jj_consume_token(51);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      t = jj_consume_token(numero);
      i = Integer.parseInt(t.toString());
      break;
    case name:
      t = jj_consume_token(name);
      int posicion = variables.indexOf(t.toString());
          if(posicion!=-1)
          {
            i = (int)valores.get(posicion);
          }
      break;
    default:
      jj_la1[28] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(55);
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

  final public void canMove() throws ParseException {
  Token t = new Token();
  int numero = 0;
  String dir = "";
    jj_consume_token(canMove);
    jj_consume_token(53);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case north:
      jj_consume_token(north);
      break;
    case south:
      jj_consume_token(south);
      break;
    case east:
      jj_consume_token(east);
      break;
    case west:
      jj_consume_token(west);
      break;
    default:
      jj_la1[29] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(move);
    jj_consume_token(54);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case numero:
      token = jj_consume_token(numero);
      numero=Integer.parseInt(t.toString());
      break;
    case name:
      t = jj_consume_token(name);
      int posicion = variables.indexOf(t.toString());
          if(posicion!=-1) {
            numero=(int) valores.get(pos);
          }
      break;
    default:
      jj_la1[30] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(51);
    t = jj_consume_token(frontBack);
            dir = t.toString();
    jj_consume_token(55);
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

  final public boolean not() throws ParseException {
  boolean b;
    jj_consume_token(not);
    jj_consume_token(54);
    b = condicion();
    jj_consume_token(55);
                {if (true) return !b;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public RobotParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[31];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1000,0x0,0x0,0x7ed20000,0x7e020000,0x800,0x0,0x0,0x0,0x0,0x0,0x18000,0x0,0x18000,0x0,0x0,0x0,0x0,0xd00000,0x0,0x0,0x0,0x0,0x0,0x18000,0x0,0x0,0x18000,0x0,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x80000,0x100000,0x30000,0x30000,0x0,0x100000,0x30000,0x38,0x3c0,0x30000,0x0,0x30000,0x0,0x38,0x3c0,0x30000,0x30006,0x0,0x30000,0x30000,0xf800,0x3c0,0x30000,0x0,0x30000,0x30000,0x0,0x30000,0x3c0,0x30000,};
   }

  /** Constructor with InputStream. */
  public RobotParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public RobotParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new RobotParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public RobotParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new RobotParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public RobotParser(RobotParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(RobotParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 31; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[56];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 31; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 56; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}

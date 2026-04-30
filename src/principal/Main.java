package principal;

import javax.swing.*;
import entidades.ConfiguracionInicial;
import entidades.LogicaPalabra;
import entidades.Wungsdle;

public class Main extends JFrame
{
	public static void main(String[] args) 
	{
		ConfiguracionInicial configuracion = new ConfiguracionInicial();
		Wungsdle wungsdle = new Wungsdle();
		LogicaPalabra logicaP = wungsdle.comenzarLogicaPalabra();
		configuracion.crearConfiguracionInicial(wungsdle,logicaP);
	}
}

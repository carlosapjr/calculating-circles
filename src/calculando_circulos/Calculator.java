package calculando_circulos;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author Carlos
 */
public class Calculator extends MIDlet implements CommandListener{
    
    //display
    Display display;
    Form tela;
    
    //textField
    TextField valor = null;
    
    
    //choiceGroup
    ChoiceGroup tipo;
    
    //comandos
    Command cmdConverter;
    Command cmdAjuda;
    Command cmdASobre;
    Command cmdSair;
    Command cmdVoltar = new Command("Voltar", Command.BACK, 1);
    
    public Calculator(){
        display = Display.getDisplay(this);
    }
    
    public void startApp() {
        //titulo da tela
        tela = new Form("Calculando Círculos");

        valor = new TextField("Digite o raio a ser calculado :", "", 5, TextField.DECIMAL);
        tipo = new ChoiceGroup("", List.EXCLUSIVE);
        tipo.append("Area", null);
        tipo.append("Circunferência", null);
        
        cmdConverter = new Command("Converter", Command.OK, 1);
        cmdAjuda = new Command("Ajuda", Command.HELP, 1);
        cmdASobre = new Command("Sobre", Command.HELP, 2);
        cmdSair =  new Command("Sair", Command.EXIT, 1);
        
        tela.append(valor);
        tela.append(tipo);
        tela.addCommand(cmdConverter);
        tela.addCommand(cmdAjuda);
        tela.addCommand(cmdASobre);
        tela.addCommand(cmdSair);
        
        tela.setCommandListener(this);
        
        display.setCurrent(tela);

    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }

    public void commandAction(Command c, Displayable d) {
        if(c == cmdSair){
            
            destroyApp(true);
            
        } else if (c == cmdConverter) {
            
            converter();
            
        } else if (c == cmdASobre) {
            
            sobre();
            
        } else if (c == cmdAjuda) {
            
            ajuda();
            
        } else if (c == cmdVoltar) {
            display.setCurrent(tela);
        }
            
    }

    public void run() {    }

    private void sobre() {
        Form f = new Form("Calculando Círculos");
        f.append("Calculando Círculos \n"
                + "Versão 1.0 \n"
                + "Desenvolvido por \n"
                + "Carlos Alberto \n"
                + "carlos.cj.alberto@gmail.com");
        f.addCommand(cmdVoltar);
        f.setCommandListener(this);
        display.setCurrent(f);
    }

    private void ajuda() {
        Form f = new Form("Calculando Círculos");
        f.append("Este aplicativo calcula a area de círculos e circunferência."
                + "\nTrazendo maior facilidade e praticidade para o usuário.");
        f.addCommand(cmdVoltar);
        f.setCommandListener(this);
        display.setCurrent(f);
    }

    private void converter() {
        
        Alert alert = null;
        String valorDigitado = valor.getString();
        if (!valorDigitado.trim().equals("")) {
                    
            double valorDigitado2 = 0;
            valorDigitado2 = Double.parseDouble(valorDigitado);
            double valorFinal;

            if (tipo.getSelectedIndex() == 0){

                valorFinal = (Math.PI*(valorDigitado2 *valorDigitado2));
                alert = new Alert("Area = ",valorFinal +" ",null,AlertType.INFO);
            } else if (tipo.getSelectedIndex() == 1){
                valorFinal = (valorDigitado2 * (Math.PI*2));
                alert = new Alert("Circunferência = ",valorFinal +" ",null,AlertType.INFO);
            }
            
            alert.setTimeout(Alert.FOREVER);
            display.setCurrent(alert);
        }else{
            Alert alert2 = new Alert ("Calculando Círculos");
            alert2.setString("Valor não informado!");
            alert2.setTimeout(Alert.FOREVER);
            alert2.setType(AlertType.INFO);
            alert2.addCommand(cmdVoltar);
            display.setCurrent(alert2);
        }
    }
}

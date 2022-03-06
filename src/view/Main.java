package view;

import controller.KillController;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		KillController killCont = new KillController();

		killCont.listaProcessos();

		String processo = JOptionPane.showInputDialog("Digite o nome ou PID do processo:");

		if (processo.contains(".")) {
			killCont.mataNome(processo);
		} else {
			killCont.mataPid(processo);
		}
	}
}

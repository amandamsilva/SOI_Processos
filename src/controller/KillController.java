package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KillController {

	public KillController() {
		super();
	}

	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}

	public String getOs() {
		return os();
	}

	public void listaProcessos() {
		String os = getOs();
		String processo = "";
		if (os.contains("Windows")) {
			processo = "TASKLIST /FO TABLE";
		} else if (os == "Linux") {
			processo = "ps -ef";
		}
		try {
			Process p = Runtime.getRuntime().exec(processo);
			InputStream fluxo = p.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while (linha != null) {
				System.out.println(linha);
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mataPid(String param) {
		String os = getOs();
		String cmdPid = "";
		if (os.contains("Windows")) {
			cmdPid = "TASKKILL /PID";
		} else if (os == "Linux") {
			cmdPid = "kill -9";
		}

		int pid = 0;
		StringBuffer buffer = new StringBuffer();

		try {
			pid = Integer.parseInt(param);
			buffer.append(cmdPid);
			buffer.append(" ");
			buffer.append(pid);
		} catch (NumberFormatException e) {
			String msgErro = e.getMessage();
			System.err.println(msgErro);
		}
		try {
			Runtime.getRuntime().exec(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mataNome(String param) {
		String os = getOs();
		String cmdNome = "";
		if (os.contains("Windows")) {
			cmdNome = "TASKKILL /IM";
		} else if (os == "Linux") {
			cmdNome = "pkill -f";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(cmdNome);
		buffer.append(" ");
		buffer.append(param);
		try {
			Runtime.getRuntime().exec(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

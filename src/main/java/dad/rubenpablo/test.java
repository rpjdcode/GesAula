package dad.rubenpablo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class test {

	public static void main(String[] args) {
		try {
			Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse("31/05/2025");
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			String formateada = formateador.format(fecha);
			System.out.println(formateada);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

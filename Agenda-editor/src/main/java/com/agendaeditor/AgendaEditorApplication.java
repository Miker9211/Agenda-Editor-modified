package com.agendaeditor;

import com.agendaeditor.infrastructure.adapters.in.agenda.AgendaController;
import com.agendaeditor.infrastructure.adapters.in.agendaitem.AgendaItemController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
		basePackages = {
				"com.agendaeditor.infrastructure.config"
		},
		basePackageClasses = {
				//output adapters
				AgendaController.class,
				AgendaItemController.class
		}

)
public class AgendaEditorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaEditorApplication.class, args);
	}

}

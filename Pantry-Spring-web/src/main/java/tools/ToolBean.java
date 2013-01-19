package tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import services.Service1;

@Component
public class ToolBean {

	@Autowired
	public Service1 service1;

}

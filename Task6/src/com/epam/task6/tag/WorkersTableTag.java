package com.epam.task6.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.entity.Worker;

public class WorkersTableTag extends TagSupport{

	private static final long serialVersionUID = 1L;
	private List<Worker> workers;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write( "<table border='1'>" );
			for(Worker worker: workers){
				out.write( "<tr>" );
				printWorkerRow( worker, out );
				out.write( "</tr>" );
			}
			out.write( "</table>" );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	private void printWorkerRow( Worker worker, JspWriter out ) throws IOException{
		out.write( "<td>" );
		out.write( worker.getName() );
		out.write( "</td><td>" );
		out.write( worker.getSurname() );
		out.write( "</td><td>" );
		out.write( String.valueOf( worker.getProfessionId() ) );
		out.write( "</td><td>" );
		out.write( String.valueOf( worker.getAssignationId() ) );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='edit_claim_command'/>"
				+ "<input type='hidden' name='address' value='" + worker
				+ "'/>" + "<input type='submit' value='Change Status'/>" + "</form>" );
		out.write( "</td>" );
	}

	public List<Worker> getWorkers() {
		return workers;
	}
	
	public Worker getWorkers( int index ){
		return workers.get( index );
	}

	public void setWorkers( List<Worker> workers ) {
		this.workers = workers;
	}

	public void setWorkers( Worker worker ){
		workers.add( worker );
	}
}

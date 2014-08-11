package com.epam.task6.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.entity.Worker;
import com.epam.task6.tableentity.WorkerTableEntity;

public class WorkersTableTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private List<WorkerTableEntity> workers;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.write( "<table>" );
			out.write( "<th>Name</th><th>Surname</th><th>Profession</th>" );
			for ( WorkerTableEntity worker : workers ) {
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

	protected void printWorkerRow( WorkerTableEntity worker, JspWriter out )
			throws IOException {
		out.write( "<td>" );
		out.write( worker.getName() );
		out.write( "</td><td>" );
		out.write( worker.getSurname() );
		out.write( "</td><td>" );
		out.write( String.valueOf( worker.getProfession().getProfessionName() ) );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='edit_worker_command'/>"
				+ "<input type='hidden' name='address' value='" + worker
				+ "'/>" + "<input type='submit' value='Edit'/>"
				+ "</form>" );
		out.write( "</td>" );
	}

	public List<WorkerTableEntity> getWorkers() {
		return workers;
	}

	public WorkerTableEntity getWorkers( int index ) {
		return workers.get( index );
	}

	public void setWorkers( List<WorkerTableEntity> workers ) {
		this.workers = workers;
	}

	public void setWorkers( WorkerTableEntity worker ) {
		workers.add( worker );
	}
}

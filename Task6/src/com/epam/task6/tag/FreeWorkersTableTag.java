package com.epam.task6.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.tableentity.WorkerTableEntity;

public class FreeWorkersTableTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private List<WorkerTableEntity> workers;

	@Override
	public int doStartTag() throws JspException {

		JspWriter out = pageContext.getOut();
		try {
			out.write( "<table>" );
			out.write( "<thead>"
					+ "<tr><td width='10%'><td>Profession</td></td><td>Name</td><td>Surname</td></thead>" );
			for ( WorkerTableEntity worker : workers ) {
				if ( worker.getAssignationId() == 0 ) {
					out.write( "<tr>" );
					printFreeWorkerRow( worker, out );
					out.write( "</tr>" );
				}
			}
			out.write( "</table>" );
		} catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private void printFreeWorkerRow( WorkerTableEntity worker, JspWriter out )
			throws IOException {
		out.write( "<td><input type='checkbox' name='selected_worker' value='" + worker.getWorkerId() + "'/>" );
		out.write( "</td><td>" );
		out.write( String.valueOf( worker.getProfession().getProfessionName() ) );
		out.write( "</td><td>" );
		out.write( worker.getName() );
		out.write( "</td><td>" );
		out.write( worker.getSurname() );
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

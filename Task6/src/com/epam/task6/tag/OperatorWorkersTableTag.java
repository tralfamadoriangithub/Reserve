package com.epam.task6.tag;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.epam.task6.propertylink.ProjectBundle;
import com.epam.task6.propertylink.ProjectString;
import com.epam.task6.tableentity.WorkerTableEntity;

public class OperatorWorkersTableTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private List<WorkerTableEntity> workers;
	private ResourceBundle bundle;
	
	@Override
	public int doStartTag() throws JspException {
		
		bundle = ResourceBundle.getBundle( ProjectBundle.PROJECT_STRING, Locale.getDefault() );
		JspWriter out = pageContext.getOut();
		try {
			out.write( "<table>" );
			StringBuilder tableHead = new StringBuilder();
			tableHead.append( "<thead><tr><td width='10%'><td>" )
				.append( bundle.getString( ProjectString.PROFESSION ) )
				.append( "</td><td>" )
				.append( bundle.getString( ProjectString.NAME ) )
				.append( "</td><td>" )
				.append( bundle.getString( ProjectString.SURNAME ) )
				.append( "</td></thead>" );
			out.write( tableHead.toString() );
			for ( WorkerTableEntity worker : workers ) {
					out.write( "<tr>" );
					printWorkerRow( worker, out );
					out.write( "</tr>" );
			}
			out.write( "</table>" );
		} catch ( IOException e ) {
			throw new JspException();
		}
		return SKIP_BODY;
	}

	private void printWorkerRow( WorkerTableEntity worker, JspWriter out )
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

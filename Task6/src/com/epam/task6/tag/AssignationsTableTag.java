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
import com.epam.task6.tableentity.AssignationTableEntity;

public class AssignationsTableTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private List<AssignationTableEntity> assignations;
	private ResourceBundle bundle;
	
	@Override
	public int doStartTag() throws JspException {
		bundle = ResourceBundle.getBundle( ProjectBundle.PROJECT_STRING, Locale.getDefault() );
		JspWriter out = pageContext.getOut();
		try {
			out.write( "<table>" );
			
			StringBuilder tableHeader = new StringBuilder();
			tableHeader.append( "<th>" )
				.append( bundle.getString( ProjectString.PROBLEM_DESCRIPTION ) )
				.append( "</th><th>" )
				.append( bundle.getString( ProjectString.BEGIN_WORK ) )
				.append( "</th><th>" )
				.append( bundle.getString( ProjectString.END_WORK ) )
				.append( "</th><th>" )
				.append( bundle.getString( ProjectString.STATUS ) )
				.append( "</th>" );
			
			out.write( tableHeader.toString() );
			for(AssignationTableEntity assignation : assignations){
					printAssignationRow( assignation, out );
			}
			out.write( "</table>" );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	private void printAssignationRow( AssignationTableEntity assignation,
			JspWriter out ) throws IOException {
		out.write( "<tr><td>" );
		out.write( String.valueOf( assignation.getClaim()
				.getProblemDescription() ) );
		out.write( "</td><td>" );
		out.write( String.valueOf( assignation.getBeginWork() ) );
		out.write( "</td><td>" );
		out.write( String.valueOf( assignation.getEndWork() ) );
		out.write( "</td><td>" );
		out.write( String.valueOf( assignation.getClaim().getClaimStatus() ) );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='delete_assignation_command'/>"
				+ "<input type='hidden' name='assignation_id' value='"
				+ assignation.getAssignationId() + "'/>"
				+ "<input type='submit' value='"
				+ bundle.getString( ProjectString.DELETE )
				+ "'/>" + "</form>" );
		out.write( "</td><td>" );
		out.write( "<form action='controller' method='post'>"
				+ "<input type='hidden' name='command' value='edit_assignation_command'/>"
				+ "<input type='hidden' name='assignation_id' value='"
				+ assignation.getAssignationId() + "'/>"
				+ "<input type='submit' value='"
				+ bundle.getString( ProjectString.EDIT )
				+ "'/>" + "</form>" );
		out.write( "</td></tr>" );
	}

	public List<AssignationTableEntity> getAssignations() {
		return assignations;
	}

	public AssignationTableEntity getAssignations( int index ) {
		return assignations.get( index );
	}

	public void setAssignations( List<AssignationTableEntity> assignations ) {
		this.assignations = assignations;
	}

	public void setAssignations( AssignationTableEntity assignation ) {
		assignations.add( assignation );
	}
}
